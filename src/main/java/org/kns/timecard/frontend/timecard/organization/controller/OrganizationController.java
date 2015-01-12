package org.kns.timecard.frontend.timecard.organization.controller;

import java.util.ArrayList;
import java.util.Map;











import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.timecard.organization.exception.OrganizationNotFoundException;
import org.kns.timecard.backend.user.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationConfigDto;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.timecard.organization.dto.TimecardPeriodDto;
import org.kns.timecard.frontend.timecard.organization.service.OrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 * Created on June 26, 2014
 * 
 * Controller for Organization
 * only Site Admin Will have access to these methods ..
 */

@RequestMapping(value="/organization/*")
@Controller("organizationController")
public class OrganizationController {
	
		
	private static Logger log=Logger.getLogger(OrganizationController.class);
	
	@Resource(name="organizationService")
	private OrganizationService organizationService;

	
	/*
	 * Created by Jeevan on June 26, 2014
	 * Method to initiate adding Organization Process.
	 */
	@RequestMapping(value="add.htm",method=RequestMethod.GET)
	public String initiateAddOrganization(Map<String, Object> map,@ModelAttribute("organization") OrganizationDto organization){
		log.info("inside inititateAddOrganization()");
		try{
			return "timecard/organization/addOrganization";
		}
		catch(Exception e){
			String message="Error While Initiating Organization Creation";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}	
	}
	
	
	
	/*
	 * Created by Jeevan on June 27, 2014
	 * Method to save Organization to DB.
	 * Includes only Basic details
	 */
	@RequestMapping(value="add.htm",method=RequestMethod.POST)
	public String saveOrganization(@ModelAttribute("organization") @Validated OrganizationDto organization,BindingResult result,Map<String, Object> map){
		log.info("inside saveOrganization()");
		try{
			if(result.hasErrors()){
				log.error("Validation Error returning to addOrganization.jsp");
				return "timecard/organization/addOrganization";
			}			
			Integer saveResult=this.organizationService.createOrganizationfromSuperAdmin(organization);
			map.put("message", "Created Successfully");
			return "redirect:/organization/add.htm";
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
	
	
	
	/*
	 * Created by Jeevan on July 03, 2014
	 * Method to initiate Configuration set up process
	 */
	@RequestMapping(value="configuration.htm",method=RequestMethod.GET)
	public String initiateOrganizationConfiguration(Map<String, Object> map,@RequestParam("userId")Integer userId, @ModelAttribute("configuration") OrganizationConfigDto configuration,
			@RequestParam(value="firstVisit",required=false)Boolean firstVisit){
		log.info("inside iniitateOrganizationConfoguration()");
		try{
			ArrayList<TimecardPeriodDto> timecardPeriods=this.organizationService.getTimecardPeriodsFromDB();
			map.put("timecardPeriods", timecardPeriods);
			map.put("userId", userId);
			map.put("firstVisit", firstVisit);
			return "timecard/organization/configuration";
		}
		catch(Exception e){
			String message="Error While Initiating Organization Configuration Setup";
			e.printStackTrace();
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
	
	
	/*
	 * Added by Jeevan on July 04, 2014
	 * Method to processSavingConfiguration information
	 * 
	 */
	@RequestMapping(value="configuration.htm",method=RequestMethod.POST)
	public String processSavingOrganizationConfiguration(@ModelAttribute("configuration") OrganizationConfigDto configuration,@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="firstVisit",required=false)Boolean firstVistit,Map<String, Object> map){
		log.info("inside processSavingOrganizationConfiguration()");
		try{
			Integer updateResult=this.organizationService.processSavingConfigurationOfOrganization(configuration, userId);
			if(updateResult>1){
				map.put("firstVisit", firstVistit);
				map.put("userId", userId);
				return "redirect:/firstlogin.htm";
			}
			else{
				throw new Exception();
			}		
		}
		catch(TimecardUserNotFoundException e){
			log.error("TimeCard USer Not Found "+e.toString());
			String message="You may have Logged out, Please Login Again to Continue";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		catch(OrganizationNotFoundException e){
			log.error("Organization Not Found for the Current User "+e.toString());
			String message="No Organization Details Found with Admin Access";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		catch(Exception e){
			log.error("Error while saving Organization Configuration Information "+e.toString());
			String message="Error While Saving Organization Configuration";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	

	
	/*
	 * Method to perform Configuration Changes to Organization
	 */
	
	
	
	
	
	
}
