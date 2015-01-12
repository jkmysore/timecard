package org.kns.timecard.frontend.organization.organization.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;




import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.organization.organization.exception.OrganizationNotFoundException;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.dto.TimeCardPeriodDto;
import org.kns.timecard.frontend.organization.organization.service.OrganizationService;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationConfigDto;
import org.kns.timecard.frontend.organization.organization.dto.TimeCardPeriodDto;
import org.kns.timecard.frontend.organization.organization.dto.TimeCardPeriodDto;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.kns.timecard.frontend.timecarduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *
 * Created on October 15, 2014
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
	@Resource(name="userService")
	private UserService userService;

	/**
	 * 
	 *  for Handling conditions when Date may be null, useful while Creating Organization, where date may be entered for certain Organization..
	 *  
	 *  */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    dateFormat.setLenient(false);
	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@Autowired
	@Qualifier("organizationDtoValidator")
	private Validator validator;


	@InitBinder("organization")
	protected void initUserBinder(WebDataBinder binder) {
	    binder.setValidator(validator);
	}
	
	
	
	
	
	/**
	 * Created by Bhagya on October 15, 2014
	 * @param organization,map
	 * @return 
	 * 
	 * Method to initiate adding Organization Process.
	 */
	@RequestMapping(value="add.htm",method=RequestMethod.GET)
	public String initiateAddOrganization(Map<String, Object> map,@ModelAttribute("organization") OrganizationDto organization){
		log.info("inside inititateAddOrganization()");
		try{
			return "timecard/organization/organization/addOrganization";
		}
		catch(Exception e){
			String message="Error While Initiating Organization Creation";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}	
	}
	
	
	
	/**
	 * Created by Bhagya on October 15, 2014
	 * @param organization,map
	 * @return
	 * 
	 * Method to save Organization to DB.
	 * Includes only Basic details
	 */
	@RequestMapping(value="add.htm",method=RequestMethod.POST)
	public String saveOrganization(@ModelAttribute("organization") @Valid OrganizationDto organization,BindingResult result,Map<String, Object> map){
		log.info("inside saveOrganization()");
		try{
			if(result.hasErrors()){
				log.error("Validation Error returning to addOrganization.jsp");
				return "timecard/organization/addOrganization";
			}			
			Integer saveResult=this.organizationService.createOrganization(organization);
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
	
	/**
	 * Created By Bhagya on October 21st,2014
	 * @param userId,OrganizationConfig Dto,firstVisit
	 * @return configuration
	 * 
	 * Method to initiate Configuration set up process
	 */
	@RequestMapping(value="configuration.htm",method=RequestMethod.GET)
	public String initiateOrganizationConfiguration(Map<String, Object> map,@RequestParam("userId")Integer userId, @ModelAttribute("configuration") OrganizationConfigDto configuration,
			@RequestParam(value="firstVisit",required=false)Boolean firstVisit){
		log.info("inside iniitateOrganizationConfoguration()");
		try{
			ArrayList<TimeCardPeriodDto> timecardPeriods=this.organizationService.getTimecardPeriodsFromDB();
			map.put("timecardPeriods", timecardPeriods);
			map.put("userId", userId);
			map.put("firstVisit", firstVisit);
			return "timecard/organization/organization/configuration";
		}
		catch(Exception e){
			String message="Error While Initiating Organization Configuration Setup";
			e.printStackTrace();
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	/**
	 * Created By Bhagya on October 21st,2014
	 * @param userId,OrganizationConfig Dto,firstVisit
	 * @return configuration
	 * 
	 * Method to processSavingConfiguration information
	 */
	@RequestMapping(value="configuration.htm",method=RequestMethod.POST)
	public String processSavingOrganizationConfiguration(@RequestParam(value="userId",required=false)Integer userId,@Valid @ModelAttribute("configuration") OrganizationConfigDto configuration,BindingResult validResult,
			@RequestParam(value="firstVisit",required=false)Boolean firstVistit,Map<String, Object> map){
		log.info("inside processSavingOrganizationConfiguration()");
		try{
			if (validResult.hasErrors()) {
				ArrayList<TimeCardPeriodDto> timecardPeriods=this.organizationService.getTimecardPeriodsFromDB();
				map.put("timecardPeriods", timecardPeriods);
				map.put("userId", userId);
				map.put("firstVisit",firstVistit);
				return "timecard/organization/organization/configuration";
			}
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
			e.printStackTrace();
			log.error("TimeCard USer Not Found "+e.toString());
			String message="You may have Logged out, Please Login Again to Continue";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(OrganizationNotFoundException e){
			log.error("Organization Not Found for the Current User "+e.toString());
			String message="No Organization Details Found with Admin Access";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error while saving Organization Configuration Information "+e.toString());
			String message="Error While Saving Organization Configuration";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	
}