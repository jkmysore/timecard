package org.kns.timecard.frontend.organization.division.controller;


import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;



import org.apache.log4j.Logger;
import org.kns.timecard.exception.DivisionNotFoundException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
import org.kns.timecard.frontend.organization.division.service.DivisionService;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.service.OrganizationService;
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


@RequestMapping(value="/division/*")
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
	@RequestMapping(value="adddivision.htm",method=RequestMethod.GET)
	public String initiateAddDivision(Authentication authentication,Map<String, Object> map, @ModelAttribute("divisionDto") DivisionDto divisionDto){
		log.info("inside initiateAddDivision()");
		try{
			
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			
			map.put("organizations",organizationDtos);
			
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
	@RequestMapping(value="adddivision.htm",method=RequestMethod.POST)
	public String addDivision(Map<String,Object> map,@Valid @ModelAttribute("divisionDto") DivisionDto divisionDto,BindingResult validResult,
			RedirectAttributes redAttributes,
			@RequestParam("organization.organizationName") Integer organizationId){
		log.info("inside addDivision()");
		try{
			
			if (validResult.hasErrors()) {
				Authentication auth=SecurityContextHolder.getContext().getAuthentication();
				String useremail=auth.getName();
				 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
				
				map.put("organizations",organizationDtos);
				return "timecard/organization/division/addDivision";
			}
			Integer result=this.divisionService.savingTheAddedorUpdatedDivisionDetails(divisionDto,organizationId);
			if(result>0){
				String message="Division Details Added Successfully";
				/*redAttributes.addFlashAttribute("message",
						"Division Succesfully Added");*/
				return "redirect:/division/divisions.htm?organizationId="+organizationId+"&addDivisionMessage="+message;
			
			}
			else{
				throw new Exception();
			}
			
		}
		catch(Exception e){
			String message="Error While Adding Division";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
	}
	/**
	 * Created By Bhagya on October 22nd,2014
	 * @param totalResults
	 * @param pageSize
	 * @return total pages needed
	 * @throws Exception
	 * 
	 * Method for getting the total pages needed for results
	 */
	
	private Integer getTotalPagesNeededByRangeandResults(Integer totalResults,
			Integer pageSize) throws Exception {
		log.info("inside getTotalPagesNeededByRangeandResults()");
		int pagesNeeded;
		int result = totalResults / pageSize;
		if (totalResults % pageSize > 0) {
			pagesNeeded = result + 1;
		} else {
			pagesNeeded = result;
		}
		return pagesNeeded;
	}
	
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param range
	 * @param totalResults
	 * @param firstResult
	 * @return
	 * 
	 * Method For Getting The Results Of Page Based On Range
	 * 
	 */
	
	private int getResultsOfLastPageFromRangeandTotalOrders(int range,
			int totalResults, int firstResult) {
		log.info("inside getOrdersOfLastPageFromRangeandTotalOrders()	");
		int lastResult = firstResult + range - 1;
		if (lastResult > totalResults) {
			lastResult = (totalResults % range) + firstResult - 1;
		}
		return lastResult;
	}
	
	
	
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param authentication
	 * @param map
	 * @return
	 * 
	 * Method For Intiating The View Divisions
	 */
	@RequestMapping(value="viewdivisions.htm",method=RequestMethod.GET)
	public String initiateViewDivisions(Authentication authentication,Map<String, Object> map){
		log.info("inside initiateViewDivisions()");
		
		try{
			
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			
			map.put("organizations",organizationDtos);
			
			return "timecard/organization/division/viewDivisions";
		}
		
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again to Continue..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Initiate View Divisions";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}	
	}
	
	/**
	 * Created By Bhagya on October 22nd,2014
	 * 
	 * @param map,page,pageSize,organizationId,divisionDto,authentication,divisionMessage,addDivisionMessage
	 * @return
	 * 
	 * Method for Process Of view Divisions
	 */
	
	@RequestMapping(value="divisions.htm",method=RequestMethod.GET)
	public String viewDivisions(Map<String, Object> map ,@RequestParam(value="page",required=false,defaultValue="0")Integer page,@RequestParam(value="range",required=false,defaultValue="10") Integer pageSize,
			@RequestParam("organizationId") Integer organizationId,@ModelAttribute("divisionDto") DivisionDto divisionDto,Authentication authentication,@RequestParam(value="divisionMessage",required=false) String divisionMessage,
			@RequestParam(value="addDivisionMessage",required=false) String addDivisionMessage){
		log.info("inside viewDivisions()");
		
		 ArrayList<OrganizationDto> organizationDtos=null;
		
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			ArrayList<DivisionDto> departmentDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(page, pageSize, organizationId);
			Integer totalResults=this.divisionService.getDivisionTotalResults();
			Integer pagesNeeded=this.getTotalPagesNeededByRangeandResults(totalResults, pageSize);
			int firstResult=pageSize*page+1;
			int lastResult=this.getResultsOfLastPageFromRangeandTotalOrders(pageSize, totalResults, firstResult);
			int i=pageSize*page+1;
			map.put("organizations",organizationDtos);
			map.put("organizationId",organizationId);
			map.put("title", "Divisions");		
			map.put("page", page);
			map.put("range", pageSize);
			map.put("end", pagesNeeded);
			map.put("first", firstResult);
			map.put("last", lastResult);
			map.put("total", totalResults);	
			map.put("departments", departmentDtos);
			map.put("divisionMessage", divisionMessage);
			map.put("addDivisionMessage", addDivisionMessage);
			map.put("i", i);
			return "timecard/organization/division/viewDivisions";
			
		}
		catch(TimecardUserNotFoundException e){
			e.printStackTrace();
			String message="User Not Found,Please Login Again to Continue..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(DivisionNotFoundException e){
			e.printStackTrace();
			map.put("organizations",organizationDtos);
			map.put("organizationId",organizationId);
			String message = "Divisions Not Found,You Can Add Divisions";
			map.put("message", message);
			map.put("title", message);
			return "timecard/organization/division/viewDivisions";
		}
		
		catch(Exception e){
			e.printStackTrace();
			String message = "Error While Displaying Divisions";
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
	@RequestMapping(value="editdivision.htm",method=RequestMethod.GET)
	public String editDivision(Map<String, Object> map,@RequestParam("divisionId") Integer divisionId,@ModelAttribute("divisionDto") DivisionDto divisionDto,Authentication authentication){
		log.info("inside editDivision()");
		 ArrayList<OrganizationDto> organizationDtos=null;
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			DivisionDto divisiondto=this.divisionService.getDivisionDetailsByDivisionId(divisionId);
			map.put("organizations",organizationDtos);
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
	
	@RequestMapping(value="editdivision.htm",method=RequestMethod.POST)
	public String editingTheDivisionPage(@Valid @ModelAttribute("divisionDto") DivisionDto divisionDto,BindingResult validResult,Map<String, Object> map,@RequestParam("organization.organizationName") Integer organizationId){
		log.info("editingTheDivisionPage()");
		try{
			
				if (validResult.hasErrors()) {
					Authentication auth=SecurityContextHolder.getContext().getAuthentication();
					String useremail=auth.getName();
					 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
					
					map.put("organizations",organizationDtos);
					return "timecard/organization/division/editDivision";
				}
			Integer result=this.divisionService.savingTheAddedorUpdatedDivisionDetails(divisionDto, organizationId);
			if(result>0){
			String message="Division Details Updated Successfully";
		return "redirect:/division/divisions.htm?organizationId="+organizationId+"&divisionMessage="+message;
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