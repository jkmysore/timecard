package org.kns.timecard.frontend.admin.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.frontend.admin.service.SuperAdminService;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.utility.dto.DisplayListBeanDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




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
				return "timecard/organization/addOrganization";
			}			
			Integer saveResult=this.superAdminService.createOrganization(organization);
			map.put("message", "Created Successfully");
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
	@RequestMapping(value="organizations.htm",method=RequestMethod.GET)
	public String viewOrganizations(@ModelAttribute("displayListBean") DisplayListBeanDto listBeanDto,Map<String, Object> map){
		log.info("inside viewOrganizations()");
		try{
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
			return "admin/organizations";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
