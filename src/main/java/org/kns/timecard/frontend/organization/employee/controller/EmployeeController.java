package org.kns.timecard.frontend.organization.employee.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.organization.division.exception.DivisionNotFoundException;
import org.kns.timecard.backend.organization.employee.exception.EmployeeNotFoundException;
import org.kns.timecard.backend.organization.organization.exception.OrganizationNotFoundException;
import org.kns.timecard.backend.timecarduser.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
import org.kns.timecard.frontend.organization.division.service.DivisionService;
import org.kns.timecard.frontend.organization.employee.dto.EmployeeDto;
import org.kns.timecard.frontend.organization.employee.service.EmployeeService;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.service.OrganizationService;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *
 * Created on October 22st, 2014
 * 
 * Controller for Employee
 * only Site Admin Will have access to these methods ..
 */
@RequestMapping(value="/employee/*")
@Controller("employeeController")
public class EmployeeController{
	private static Logger log=Logger.getLogger(EmployeeController.class);
	
	/*
	 * 
	 *  for Handling conditions when Date may be null, useful while editing a employee, where date may be entered for certain Employees..
	 *  
	 *  */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    dateFormat.setLenient(false);
	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	
	@Resource(name="organizationService")
	private OrganizationService organizationService;
	
	@Resource(name="divisionService")
	private DivisionService divisionService;
	
	@Resource(name="userService")
	private UserService userService;
	@Autowired
	@Qualifier("employeeDtoValidator")
	private Validator validator;

	@InitBinder("employeeDto")
	protected void initUserBinder(WebDataBinder binder) {
	    binder.setValidator(validator);
	}
	@Autowired
	@Qualifier("editEmployeeValidator")
	private Validator editEmployeeValidator;


	@InitBinder("editEmployeeDto")
	protected void initEditEmployeeBinder(WebDataBinder binder) {
	    binder.setValidator(editEmployeeValidator);
	}
	/**
	 * Created By Bhagya On October 27th,2014
	 * @param authentication,map,employeeDto
	 * @return
	 * 
	 * GET method For Adding The Employee
	 */
	
	@RequestMapping(value="addemployee.htm",method=RequestMethod.GET)
	public String initiateAddEmployee(Authentication authentication,Map<String, Object> map, @ModelAttribute("employeeDto") EmployeeDto employeeDto)
	{
		log.info("inside initiateAddEmployee()");
		Integer organizationId=null;
		 ArrayList<OrganizationDto> organizationDtos=null;
		try{

			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			  organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			 for(int i=0;i<organizationDtos.size();i++){
				  organizationId=organizationDtos.get(i).getOrganizationId();
			 }
			 ArrayList<DivisionDto> divisionDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(null, null, organizationId);
			 map.put("organizations", organizationDtos);
			 map.put("divisions",divisionDtos);
			map.put("organizations",organizationDtos);
			return "timecard/organization/employee/addEmployee";
		}
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(DivisionNotFoundException e){
			map.put("organizations",organizationDtos);
			map.put("organizationId",organizationId);
			String message="Divisions Not Found,Please Add Division to Continue..";
			map.put("message", message);
			map.put("title", message);
			return "timecard/organization/division/divisionError";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Initiating Employee Creation";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}	
		
	}
	
	/**
	 * Created By Bhagya On October 27th,2014
	 * @param map,employeeDto,validResult,redAttributes,employeeType
	 * @return
	 * 
	 * Method For saving the Added Employee Details
	 */
	@RequestMapping(value="addemployee.htm",method=RequestMethod.POST)
	public String addEmployee(Map<String,Object> map,@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto,BindingResult validResult,
			RedirectAttributes redAttributes,@RequestParam("employeeType") String employeeType){
		log.info("inside addEmployee()");
		try{
			Integer organizationId=null;
			if (validResult.hasErrors()) {
				Authentication auth=SecurityContextHolder.getContext().getAuthentication();
				String useremail=auth.getName();
				 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
				 for(int i=0;i<organizationDtos.size();i++){
					  organizationId=organizationDtos.get(i).getOrganizationId();
				 }
				
				 ArrayList<DivisionDto> divisionDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(null, null,organizationId);
				map.put("divisions", divisionDtos);
				map.put("organizations",organizationDtos);
				return "timecard/organization/employee/addEmployee";
			}
			
			Integer result=this.employeeService.savingAddedEmployeeDetails(employeeDto, employeeDto.getDivision().getDivisionId(), employeeDto.getOrganization().getOrganizationId());
			if(result>0){
				String employeeMessage="Employee Added Successfully";
				redAttributes.addFlashAttribute("employeeMessage",
				employeeMessage);
				
				return "redirect:/employee/viewemployees.htm?organizationId="+employeeDto.getOrganization().getOrganizationId()+"&employeeMessage="+employeeMessage+"&employeeType="+employeeType;
			}
			else{
				throw new Exception();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Adding Employee";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
	}

	/**
	 * Created By Bhagya On October 27th,2014
	 * @param employeeNo
	 * @return Its returns the employee exists or not i.e.. returns the success or fail
	 * 
	 * Method For checking The Existence Of Employee
	 */
	@RequestMapping(value="/checkemployee.htm",method=RequestMethod.GET)
	@ResponseBody
	public String checkExistanceOfEmployee(@RequestParam("empNo")String employeeNo){
		log.info("inside checkExistanceofEmployee()");
		try{
			EmployeeDto employeeDto=this.employeeService.getTheEmployeeByEmployeeNo(employeeNo);
			
			if(null!=employeeDto){
				return "fail";
			}
			else{
				throw new EmployeeNotFoundException();
			}	
		}
		catch(EmployeeNotFoundException e){
			log.info("No Employee Exists,You Can Add Employee");
			return "success";
		}
		catch(Exception e){
			log.error("Error While Validating Employee Existence");
			return "fail";
		}
	
	}
	/**
	 * Created By Bhagya On October 27th,2014
	 * @param email
	 * @return timecard user exists or not i.e.. its returns success or fail
	 * 
	 * Method For Checking The Existence Of Employee Email
	 */
	
	@RequestMapping(value="/checkempemail.htm",method=RequestMethod.GET)
	@ResponseBody
	public String checkExistanceOfEmployeeEmail(@RequestParam("email")String email){
		log.info("inside checkExistanceofEmployeeEmail()");
		try{
			TimecardUserDto timeCardUserDto=this.userService.getTimecardUserByEmail(email);
			
			if(null!=timeCardUserDto){
				
				return "fail";
			}
			else{
				throw new TimecardUserNotFoundException();
			}	
		}
		catch(TimecardUserNotFoundException e){
			log.info("No Email Exists,You Can Add Employee");
			return "success";
		}
		catch(Exception e){
			log.error("Error While Validating Employee Existence");
			return "fail";
		}
	}
	/**
	 * Craeted By Bhagya On October 28th,2014
	 * @param employeeNo
	 * @return
	 * 
	 * 
	 * Method FOr Checking the Existence Of Manager
	 */
	@RequestMapping(value="/checkmanager.htm",method=RequestMethod.GET)
	@ResponseBody
	public String checkExistanceOfManager(@RequestParam("empNo")String employeeNo){
		log.info("inside checkExistanceofManager()");
		try{
			EmployeeDto employeeDto=this.employeeService.getTheEmployeeByEmployeeNo(employeeNo);
			if(null==employeeDto){
				throw new EmployeeNotFoundException();
			}
			else if(employeeDto.getIsManager()){
				return "fail";
				}
					
			else{
				throw new EmployeeNotFoundException();
			}	
		}
		catch(EmployeeNotFoundException e){
			log.info("No Manager Exists,You Can Add Manager");
			return "success";
		}
		catch(Exception e){
			log.error("Error While Validating Manager Existence");
			return "fail";
		}
	
	}
	
	/**
	 * Created By Bhagya On October 28th ,2014
	 * @param email
	 * @return
	 * 
	 * Method For Checking the Existence Of Manager emailid
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/checkmanageremail.htm",method=RequestMethod.GET)
	@ResponseBody
	public String checkExistanceOfManagerEmail(@RequestParam("email")String email){
		log.info("inside checkExistanceofManagerEmail()");
		try{
			
			TimecardUserDto timeCardUserDto=this.userService.getTimecardUserByEmail(email);
			Integer userId=timeCardUserDto.getUserId();
			EmployeeDto employeeDto=this.employeeService.getTheEmployeeByTimecardUserId(userId);
			Boolean isManager=employeeDto.getIsManager();
			if(null==timeCardUserDto ){
				throw new EmployeeNotFoundException();
				
			}
			else if(null!=employeeDto && isManager==true){
				return "fail";
				
			}
			else{
				throw new EmployeeNotFoundException();
			}	
		}
		catch(TimecardUserNotFoundException e){
			log.info("No Email Exists,You Can Add Employee");
			return "success";
		}
		catch(EmployeeNotFoundException e){
			log.info("No Email Exists,You Can Add Employee");
			return "success";
		}
		catch(Exception e){
			log.error("Error While Validating Manager Existence");
			return "fail";
		}
	}
	
	
	/**
	 * Created By Bhagya on October 28th,2014
	 * @param totalResults
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * 
	 * Method for to get the total pages needed
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
	 * Created By Bhagya On October 28th,2014
	 * @param range,totalResults,firstREsult
	 * @return
	 * @throws
	 * Method For to  get total results based on ranges
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
	 * Created By Bhagya On October 28th,2014
	 * @param map,page,pageSize,organizationId,authentication,employeeType,totalResults,employeeMessage,managerMessage
	 * @return
	 * 
	 * Method for viewing the all employees ie..both managers and employees
	 */
	
	@RequestMapping(value="/viewemployees.htm",method=RequestMethod.GET)
	public String viewEmployeesAndManagers(Map<String, Object> map ,@RequestParam(value="page",required=false,defaultValue="0")Integer page,@RequestParam(value="range",required=false,defaultValue="5") Integer pageSize,
			@RequestParam(value="organizationId",required=false) Integer organizationId,Authentication authentication,@RequestParam(value="employeeType",required=false) String employeeType,Integer totalResults,
			@RequestParam(value="employeeMessage",required=false) String employeeMessage,@RequestParam(value="managerMessage",required=false) String managerMessage){
		ArrayList<OrganizationDto> organizationDtos=null;
		Boolean manager = null;
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			 for(int i=0;i<organizationDtos.size();i++){
				  organizationId=organizationDtos.get(i).getOrganizationId();
				 
			 }
			ArrayList<EmployeeDto> employeeDtos= this.employeeService.getTheEmployeesAndManagers(organizationId, page,pageSize,employeeType);
			if(null==employeeType || employeeType.trim().length()<=0||employeeType.contentEquals("allemployees") ){
				totalResults=this.employeeService.getTotalResultsOfEmployeesAndManagers(organizationId);
				}
			
			else if(employeeType.contentEquals("employees")){
				totalResults=this.employeeService.getTotalResultsOfEmployees(organizationId);
				}
			else{
				totalResults=this.employeeService.getTotalResultsOfManagers(organizationId);
				}
			for(EmployeeDto emp:employeeDtos){
			 manager=emp.getIsManager();
			}
			Integer pagesNeeded=this.getTotalPagesNeededByRangeandResults(totalResults, pageSize);
			int firstResult=pageSize*page+1;
			int lastResult=this.getResultsOfLastPageFromRangeandTotalOrders(pageSize, totalResults, firstResult);
			int i=pageSize*page+1;
			map.put("title", "Employees");		
			map.put("page", page);
			map.put("range", pageSize);
			map.put("end", pagesNeeded);
			map.put("first", firstResult);
			map.put("last", lastResult);
			map.put("total", totalResults);	
			map.put("i", i);
			map.put("manager", manager);
			map.put("employeeType", employeeType);
			map.put("organizationId",organizationId);
			map.put("organizations",organizationDtos);
			map.put("employees", employeeDtos);
			map.put("employeeMessage", employeeMessage);
			map.put("managerMessage", managerMessage);
			return "timecard/organization/employee/viewEmployees";
		}
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again to Continue..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		
		catch(EmployeeNotFoundException e){
			e.printStackTrace();
			map.put("organizations",organizationDtos);
			map.put("organizationId",organizationId);
			String message = "Employees And Managers Are Not Found,You Can Add Employee or Manager";
			map.put("message", message);
			map.put("title", message);
			return "timecard/organization/employee/viewEmployees";
		}
		
		catch(Exception e){
			e.printStackTrace();
			String message = "Error While Displaying Employees";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
		
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param map,employeeDto,authentication,employeeType,employeeNo,redAttributes
	 * @return
	 * @throws TimecardUserNotFoundException
	 * @throws OrganizationNotFoundException
	 * @throws DivisionNotFoundException
	 * 
	 * Method For initiating the Add Organization Manager
	 * Steps:
	 * 		1.Based On Authentication Details we are Getting organizationId
	 * 		2.From organizationId we are getting both organizationDto and DivisionDto
	 * 		3.Based On Employee Number we are checking employee already exists or not
	 * 			a.if employee exists means ,saved employee as a manager
	 * 			b.if employee not exists means,throws EmployeeNotFoundexception ,in that returns to jsp page
	 * 						there we are creating new manager
	 * 		4.It redirects the successfully message to viewEmployees page
	 */
	
	@RequestMapping(value="manager/add.htm",method=RequestMethod.GET)
	public String initiateAddOrganizationManager(Map<String, Object> map,@ModelAttribute("employeeDto") EmployeeDto employeeDto,Authentication authentication,
			@RequestParam("employeeType") String employeeType,@RequestParam(value="employeeNo",required=false) String employeeNo,RedirectAttributes redAttributes) throws TimecardUserNotFoundException, OrganizationNotFoundException, DivisionNotFoundException{
		
		log.info("inside inititateAddOrganizationManager()");
		
		Integer organizationId = null;
		 EmployeeDto employee=null;
		 ArrayList<OrganizationDto> organizationDtos=null;
		 ArrayList<DivisionDto> divisionDtos=null;
		try{
			Integer result=null;
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			 for(int i=0;i<organizationDtos.size();i++){
				  organizationId=organizationDtos.get(i).getOrganizationId();
				 
			 }
			 divisionDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(null, null, organizationId);
			 map.put("organizations", organizationDtos);
			 map.put("divisions",divisionDtos);
			 employee=this.employeeService.getTheEmployeeByEmployeeNo(employeeNo);
			 if(employee.getIsManager()==false){
		 		 result=this.employeeService.saveEmployeeAsManager(employee);
		 	}
			 
			 if(result>0){
					String mngMsg="Organization Manager Added Successfully";
					redAttributes.addFlashAttribute("mngMsg",
							mngMsg);
					return "redirect:/employee/viewemployees.htm?organizationId="+organizationId+"&managerMessage="+mngMsg+"&employeeType="+employeeType;
		 	}
			 else{
					throw new Exception();
				}
			
			
		}
		
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(EmployeeNotFoundException e){
			 map.put("organizations", organizationDtos);
			 map.put("divisions",divisionDtos);
			return "timecard/organization/employee/manager/addOrganizationManager";
		}
		
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Initiating Organization Manager Creation";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}		
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param map,employeeDto,validResult,redAttributes,employeeType
	 * @return
	 * 
	 * Method For saving the Manager
	 */
	
	
	@RequestMapping(value="manager/add.htm",method=RequestMethod.POST)
	public String addOrganizationManager(Map<String,Object> map,@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto,BindingResult validResult,
			RedirectAttributes redAttributes,
			@RequestParam("employeeType") String employeeType){
		log.info("inside addOrganizationManager()");
		System.out.println("inside post method");
		try{
			if (validResult.hasErrors()) {
				Authentication auth=SecurityContextHolder.getContext().getAuthentication();
				String useremail=auth.getName();
				 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
				 ArrayList<DivisionDto> divisionDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(null, null, employeeDto.getOrganization().getOrganizationId());
				map.put("divisions", divisionDtos);
				map.put("organizations",organizationDtos);
				return "timecard/organization/employee/manager/addOrganizationManager";
			}
			
			Integer result=this.employeeService.savingAddedManagerDetails(employeeDto,employeeDto.getDivision().getDivisionId(),employeeDto.getOrganization().getOrganizationId());
			if(result>0){
				String mngMsg="Organization Manager Added Successfully";
				redAttributes.addFlashAttribute("mngMsg",
						mngMsg);
				return "redirect:/employee/viewemployees.htm?organizationId="+employeeDto.getOrganization().getOrganizationId()+"&managerMessage="+mngMsg+"&employeeType="+employeeType;
			}
			else{
				throw new Exception();
			}
		}
		
		catch(TimecardUserNotFoundException e){
			e.printStackTrace();
			String message="User Not Found,Please Login Again..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Adding Organization Manager";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
	}
	
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param employeeType,employeeNo,redAttributes,map
	 * @return
	 * 
	 * Method For removing the organization manager
	 */
	
	@RequestMapping(value="manager/remove.htm")
	public String removeOrganizationManager(@RequestParam("employeeType") String employeeType,@RequestParam("employeeNo") String employeeNo,RedirectAttributes redAttributes,Map<String, Object> map){
		log.info("inside removeOrganizationManager()");
		try{
		EmployeeDto employeeDto=this.employeeService.getTheEmployeeByEmployeeNo(employeeNo);
		Integer result=this.employeeService.removeOrganizationManager(employeeDto);
		if(result>0){
			String mngMsg="Manager Removed Successfully";
			redAttributes.addFlashAttribute("removeManagerMessage",
					mngMsg);
			return "redirect:/employee/viewemployees.htm?managerMessage="+mngMsg+"&employeeType="+employeeType;
			}
			else{
				throw new Exception();
			}
		
		}
		catch(Exception e){
			String message="Error while Removing Manager, Please Try Again";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param map,empNo,employeeDto,authentication
	 * @return
	 * 
	 * Method For Initiating The Edit Employee
	 */
	
	@RequestMapping(value="/editemployee.htm",method=RequestMethod.GET)
	public String editEmployee(Map<String, Object> map,@RequestParam("empNo") String empNo,@ModelAttribute("editEmployeeDto") EmployeeDto employeeDto,Authentication authentication){
		log.info("inside editEmployee()");
		 ArrayList<OrganizationDto> organizationDtos=null;
		 Integer organizationId=null;
		try{
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String useremail=auth.getName();
			 organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
			 for(int i=0;i<organizationDtos.size();i++){
				  organizationId=organizationDtos.get(i).getOrganizationId();
				 
			 }
			EmployeeDto employee=this.employeeService.getTheEmployeeByEmployeeNo(empNo);
			ArrayList<DivisionDto> divisionDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(null, null, organizationId);
			map.put("divisions", divisionDtos);
			map.put("organizations",organizationDtos);
			
			map.put("employee",employee);
			return "timecard/organization/employee/editEmployee";
		}
		catch(TimecardUserNotFoundException e){
			String message="User Not Found,Please Login Again to Continue..";
			map.put("message", message);
			map.put("title", message);
			return "response";
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error While Editing Employee Details ";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		
	}
	
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param employeeType,editEmployeeDto,validResult,map,redAttributes
	 * @return
	 * 
	 * Method For saving the updated or edited employee details
	 */
	
	@RequestMapping(value="/editemployee.htm",method=RequestMethod.POST)
	public String editingTheEmployee(@RequestParam("employeeType") String employeeType,@Valid @ModelAttribute("editEmployeeDto") EmployeeDto editEmployeeDto,BindingResult validResult,Map<String, Object> map,
			RedirectAttributes redAttributes){
		log.info("inside editingTheEmployee()");		
		try{
			if (validResult.hasErrors()) {
				Authentication auth=SecurityContextHolder.getContext().getAuthentication();
				String useremail=auth.getName();
				 ArrayList<OrganizationDto> organizationDtos=this.organizationService.getOrganizationsByAdminorManager(useremail);
				 ArrayList<DivisionDto> divisionDtos=this.divisionService.getAllDivisionsBasedOnOrganizationId(null, null, editEmployeeDto.getOrganization().getOrganizationId());
				map.put("divisions", divisionDtos);
				map.put("organizations",organizationDtos);
				return "timecard/organization/employee/editEmployee";
				}
			
			Integer result=this.employeeService.savingUpdatedEmployeeDetails(editEmployeeDto, editEmployeeDto.getDivision().getDivisionId(), editEmployeeDto.getOrganization().getOrganizationId());
			
			if(result>0){
			String editMsg="Employee Details Updated Successfully";
			redAttributes.addFlashAttribute("editMsg",
					editMsg);
			return "redirect:/employee/viewemployees.htm?organizationId="+editEmployeeDto.getOrganization().getOrganizationId()+"&employeeMessage="+editMsg+"&employeeType="+employeeType;
			}
			else{
				throw new Exception();
			}
			
		}
		catch(EmployeeNotFoundException e){
			e.printStackTrace();
			String message = "Employee Not Found";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Error While  Editing Employee");
			String message="Error while Employee editing, Please Try Again";
			map.put("message", message);
			map.put("title", message);
			return "error";
		}
	
	}
	
}