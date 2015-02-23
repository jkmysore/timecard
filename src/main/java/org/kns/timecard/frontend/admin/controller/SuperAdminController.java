package org.kns.timecard.frontend.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kns.timecard.common.logging.LoggingFactory;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.frontend.admin.service.SuperAdminService;
import org.kns.timecard.frontend.common.utility.LogDto;
import org.kns.timecard.frontend.common.utility.LoggerUtility;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.utility.dto.DisplayListBeanDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




/**
 * Created by Jeevan on Jan 12, 2014
 * @author KNS-ACCONTS
 *
 * Class for Super Admin Controller
 * 
 */
@RequestMapping("/admin/*")
@Controller("superAdminController")
public class SuperAdminController {

	
	@Resource(name="superAdminService")
	private SuperAdminService superAdminService;
	
	@Resource(name="loggerUtility")
	private LoggerUtility loggerUtility;
	
	private static Logger log=Logger.getLogger(SuperAdminController.class);
	
	
	
	
	
	
	/**
	 * Created by Jeevan on Jan 12, 2014
	 * Method to add Organization..
	 * @param map
	 * @param organization
	 * @return
	 */
	@RequestMapping(value="addorganization.htm",method=RequestMethod.GET)
	public String initiateAddOrganization(Map<String, Object> map,@ModelAttribute("organization") OrganizationDto organization){
		log.info("inside inititateAddOrganization()");
		try{
			return "admin/addOrganization";
		}
		catch(Exception e){
			String message="Error While Initiating Organization Creation";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}	
	}
	
	
	
	
	/**
	 * To Save Organization Details
	 * @param organization
	 * @param result
	 * @param map
	 * @return
	 */
	
	@RequestMapping(value="addorganization.htm",method=RequestMethod.POST)
	public String saveOrganization(@ModelAttribute("organization") @Valid OrganizationDto organization,BindingResult result,Map<String, Object> map){
		log.info("inside saveOrganization()");
		try{
			if(result.hasErrors()){
				log.error("Validation Error returning to addOrganization.jsp");
				return "admin/addOrganization";
			}			
			Integer saveResult=this.superAdminService.createOrganization(organization);
			map.put("status", "Created Successfully");
			return "redirect:/admin/addorganization.htm";
		}
		catch(Exception e){
			log.error("Error While Adding Organization "+e.toString());
			e.printStackTrace();
			String message="Error While Adding Organization";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	
	/**
	 * 
	 * @param listBeanDto
	 * @param map
	 * @return
	 * 
	 *  //USED bean.get() to enable reusability of Service and Dao
	 */
	@RequestMapping(value="organizations.htm")
	public String viewOrganizations(@ModelAttribute("displayListBean") DisplayListBeanDto listBeanDto,Map<String, Object> map){
		log.info("inside viewOrganizations()");
		try{
			if(null==listBeanDto.getSortBy() || listBeanDto.getSearchBy()=="" ){
				listBeanDto.setSortBy("organizationId");
			}
			ArrayList<OrganizationDto> organizationDtos=this.superAdminService.getOrganizations(listBeanDto.getPagerDto().getPageNo(), listBeanDto.getPagerDto().getRange(), listBeanDto.getSearchBy(),
					listBeanDto.getSortBy(),listBeanDto.getSortDirection());		
		
			listBeanDto.getPagerDto().setTotalItems(organizationDtos.get(0).getTotalOrganizations());
			map.put("organizations", organizationDtos);
			map.put("title", "View Organizations");
			return "admin/organizations";
			
		}
		catch(OrganizationNotFoundException e){
			log.error("No Organization Found");
			String message="No Organizations Found";
			map.put("message", message);
			map.put("title", message);
			return "admin/organizations";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error Occured While listing Organizations "+e.toString());
			String message="Error Occured While listing Organizations";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	/**
	 * 
	 * @param organizationDto
	 * @param organizationId
	 * @param map
	 */
	@RequestMapping(value="editorganization.htm",method=RequestMethod.GET)
	public String initEditOrganization(@ModelAttribute("organization") OrganizationDto organizationDto,@RequestParam("organizationId") Integer organizationId, Map<String, Object> map){
		log.info("insided initEditOrganization()");
		try{			
			
			organizationDto=this.superAdminService.getOrganizationByOrganizationId(organizationId);
			map.put("organizationDto", organizationDto);
			return "admin/editOrganization";
		}
		catch(OrganizationNotFoundException e){
			e.printStackTrace();
			String message="No Organization Found";
			map.put("message", message);
			map.put("title", message);
			return "redirect:/admin/organizations.jsp";
		}
		catch(Exception e){
			String message="Error While Editing Organization";
			map.put("message", message);
			map.put("title",message);
			return "error";
		}
	}
	


	
	/**
	 * Created by Jeevan on Jan 23, 2015
	 * Method to edit Organozation
	 * @param organizationDto
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editorganization.htm",method=RequestMethod.POST)
	public String processEditOrganization(@ModelAttribute("organization") @Valid OrganizationDto organizationDto,BindingResult bindingResult, Map<String, Object> map){
		log.info("inside processEditOrganization( )");
		LogDto logDto=null;
		try{
			logDto=loggerUtility.saveOrganizationLogs(organizationDto.getOrganizationId());
			if(bindingResult.hasErrors()){
				log.error("Validation Error returning to addOrganization.jsp");
				return "admin/editOrganization";
			}	
			Integer editResult=this.superAdminService.editOrganizationByAdmin(organizationDto);
			String status="Organization Edited Successfully";
			if(logDto.getSaveOrganizationLog()){
				final Logger logs=LoggingFactory.getCustomLogger(organizationDto.getOrganizationName(), null, true);
				logs.info("Organization Edited By Super Admin on "+new Date());
			}
			map.put("status", status);
			return "redirect:/admin/organizations.htm";			
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logs;
			try {
				if(logDto.getSaveOrganizationLog()){
					logs = LoggingFactory.getCustomLogger(organizationDto.getOrganizationName(), null, true);
					logs.error("Error While Editing Organization By Admin  "+e.toString());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			String message="Error While Editing Organization";
			map.put("message", message);
			map.put("title", message);
			return "error";			
			
		}
	}
	
	
	
	
	/**
	 * 
	 * @param organizationId
	 * @param activate
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/admin/changeorganization.htm")
	public String activeorInactivateOrganization(@RequestParam("organizationId") Integer organizationId,@RequestParam("activate")Boolean activate,Map<String,Object> map){
		log.info("inside activateorInactivateOrganization()");		
		try{			
			Integer changeResult=this.superAdminService.changeOrganizationStatus(organizationId, activate);
			String status="Organization Status Changed Successfully";			
			map.put("status", status);
			return "redirect:/admin/organizations.htm";		
			
		}		
		catch(Exception e){
			String message="Error While Changing Organization Status";
			map.put("message", message);
			map.put("title", message);
			return "error";			
		}
	}
	
	
	
	
	
	
}
