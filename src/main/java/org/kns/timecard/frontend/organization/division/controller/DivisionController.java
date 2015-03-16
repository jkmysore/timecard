package org.kns.timecard.frontend.organization.division.controller;


import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kns.timecard.exception.DivisionNotFoundException;
import org.kns.timecard.exception.DivisionNotFoundFilterException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
import org.kns.timecard.frontend.organization.division.service.DivisionService;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.service.OrganizationService;
import org.kns.timecard.frontend.utility.dto.DisplayListBeanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *
 * Created on October 21st, 2014
 * 
 * Controller for division
 * only Site Admin Will have access to these methods ..
 */



@Controller("divisionController")
public class DivisionController {
	
		
	private static Logger log=Logger.getLogger(DivisionController.class);
	
	@Resource(name="organizationService")
	private OrganizationService organizationService;
	
	@Resource(name="divisionService")
	private DivisionService divisionService;
	
	
	@Autowired
	@Qualifier("divisionDtoValidator")
	private Validator divisionValidator;


	@InitBinder("divisionDto")
	protected void initDivisionUserBinder(WebDataBinder binder) {
	    binder.setValidator(divisionValidator);
	}
	/**
	 * Created By Bhagya on October 21st,2014
	 * @param divisionDto,authenticatio,map
	 * @return
	 * 
	 * Method For to initiate Add Division
	 */
	@RequestMapping(value="org/adddivision.htm",method=RequestMethod.GET)
	public String initiateAddDivision(Authentication authentication,Map<String, Object> map, @ModelAttribute("divisionDto") DivisionDto divisionDto){
		log.info("inside initiateAddDivision()");
		try{
			
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			OrganizationDto organizationDto=this.organizationService.getOrganizationByUserEmail(useremail);
			map.put("organization",organizationDto);
			
			return "timecard/organization/division/addDivision";
		}
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Initiating Division Creation";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}	
	}
	
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param divisionDto,organizationId
	 * @return
	 * 
	 * Method to Save process of adding Division.
	 */
	
	@RequestMapping(value="org/adddivision.htm",method=RequestMethod.POST)
	public String addDivision(Map<String,Object> map,@Valid @ModelAttribute("divisionDto") DivisionDto divisionDto,BindingResult validResult,
			RedirectAttributes redAttributes){
		log.info("inside addDivision()");
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			OrganizationDto organizationDto=this.organizationService.getOrganizationByUserEmail(useremail);
			if (validResult.hasErrors()) {
				map.put("organizations",organizationDto);
				return "timecard/organization/division/addDivision";
			}
			Integer result=this.divisionService.savingTheAddedorUpdatedDivisionDetails(divisionDto,organizationDto.getOrganizationId());
			if(result>0){
				String status="Division Added Successfully";
				redAttributes.addFlashAttribute("status",status);
				return "redirect:/org/viewdivisions.htm?status="+status;
			
			}
			else{
				throw new Exception();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Adding Division";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
	}
	
	
	/**
	 * Created By Bhagya on Feb 27th,2015
	 * @param map,page,pagesize
	 * @return
	 * 
	 * Method For to Process the View Divisions
	 */
	
	@RequestMapping(value="org/viewdivisions.htm")
	public String viewDivisions(Map<String, Object> map,@ModelAttribute("displayListBean") DisplayListBeanDto listBeanDto,@RequestParam(value="status",required=false) String status)
	{
		log.info("inside viewDivisions()");
		Integer organizationId = null;
		
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			OrganizationDto organizationDto = this.organizationService.getOrganizationByUserEmail(useremail);
			organizationId=organizationDto.getOrganizationId();
			
			if(null==listBeanDto.getSortBy()){
				listBeanDto.setSortBy("divisionId");
			}
			ArrayList<DivisionDto> divisions=this.divisionService.getDivisionsBasedOnOrganizationId(organizationId, listBeanDto.getPagerDto().getPageNo(), listBeanDto.getPagerDto().getRange(),
														listBeanDto.getSortBy(),listBeanDto.getSearchBy(),listBeanDto.getSortDirection());
			
			
			Integer totalResults=this.divisionService.getDivisionTotalResults();
			listBeanDto.getPagerDto().setTotalItems(totalResults);
			int i=listBeanDto.getPagerDto().getFirstResult();
			map.put("i", i);
			map.put("status", status);
			map.put("divisions", divisions);
			return "timecard/organization/division/viewDivisions";
		}
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(DivisionNotFoundFilterException e){
			String message="No Divisions Found For Search Criteria";
			map.put("filterMsg", message);
			map.put("title", message);
			return "timecard/organization/division/viewDivisions";
		}
		catch(DivisionNotFoundException e){
			String message="Divisions Are Not Found,You Can Add Division";
			map.put("msg", message);
			map.put("title", message);
			return "timecard/organization/division/viewDivisions";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While viewing Divisions";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
		
	}
	
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param map,divisionId,divisionDto,authentication
	 * @return
	 * 
	 * Method For  Initiate Edit Division
	 */
	@RequestMapping(value="org/editdivision.htm",method=RequestMethod.GET)
	public String editDivision(Map<String, Object> map,@RequestParam("divisionId") Integer divisionId,@ModelAttribute("divisionDto") DivisionDto divisionDto,Authentication authentication){
		log.info("inside editDivision()");
		OrganizationDto organizationDto=null;
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			organizationDto=this.organizationService.getOrganizationByUserEmail(useremail);
			DivisionDto divisiondto=this.divisionService.getDivisionDetailsByDivisionId(divisionId);
			map.put("organization",organizationDto);
			map.put("division", divisiondto);
			return "timecard/organization/division/editDivision";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Editing Division Details ";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
	}
	/**
	 * Created By Bhagya oN october 22nd,2014
	 * @param divisionDto,validResult,map,organizationId
	 * @return
	 * 
	 * 
	 * Method To Process Edit Division
	 */
	
	@RequestMapping(value="org/editdivision.htm",method=RequestMethod.POST)
	public String editingTheDivisionPage(@Valid @ModelAttribute("divisionDto") DivisionDto divisionDto,BindingResult validResult,Map<String, Object> map,
			RedirectAttributes redAttributes){
		log.info("editingTheDivisionPage()");
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			OrganizationDto organizationDto=this.organizationService.getOrganizationByUserEmail(useremail);
				if (validResult.hasErrors()) {
					map.put("organizations",organizationDto);
					return "timecard/organization/division/editDivision";
				}
			
			Integer result=this.divisionService.savingTheAddedorUpdatedDivisionDetails(divisionDto, organizationDto.getOrganizationId());
			if(result>0){
				String status="Division Updated Successfully";
				redAttributes.addFlashAttribute("status",status);
				return "redirect:/org/viewdivisions.htm?status="+status;
			}
			else{
				throw new Exception();
			}
			
		}
		catch(Exception e){
			log.error("Error while Division editing");
			String message="Error while Division editing, Please Try Again";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	
	
}
}