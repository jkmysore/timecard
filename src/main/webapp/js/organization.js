/**
	Author: Jeevan
	Created on : June 30, 2014
	
	Javascript file to handle or perfom validations related to Organization

*/

/*
 * 
 * Method to Validate Organization
 */
	/*Added by bhagya on july 09th,2014
	 * Method to Validate Organization form
	 */

	function validateOrganization(){
		var username=$('#siteAdminUserName').val();
		
		var password=$('#siteAdminPassword').val();
		
		var email=$('#siteAdminEmail').val();
		var logo=$('#logo').val();
		var orgName=$('#organizationName').val();
		var orgShortName=$('#organizationShortName').val();
		var pwdFilter=/^[A-Za-z0-9!@#$%^&*()_]{8,20}$/;
		var usrFilter=/^[A-Za-z0-9 ]{6,20}$/;
		var emailFilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i ;
		var logoExtension="";
		if(logo.length>0){
			 logoExtension = logo.split('.').pop().toUpperCase();
		}
		
		var pwdStyle=$('.pwdDiv').css('display');
		
		var a,c,e,f=true;
		var d=true;
		var b=true;
		
		if(username=="" || username==null){
			$('#siteAdminUserName_error').html('*Username Cannot Be Empty');
			a=false;
		}
		else if(usrFilter.test(username)==false){
			$('#siteAdminUserName_error').html('*UserName Length Should Be 8 to 20 Charactes And Not Allowed Special Characters');
			a=false;
			}
		else{
			$('#siteAdminUserName_error').html('');
			a=true;
		}
		
		if(pwdStyle!=="none"){
			if(password=="" || password==null ){
				$('#siteAdminPassword_error').html('*Password Cannot Be Empty');
				b=false;
			}
			else if(pwdFilter.test(password)==false){
				$('#siteAdminPassword_error').html('*Password Length Should Be 8 to 20 Charactes');
				b=false;
				}
			else{
				$('#siteAdminPassword_error').html('');
				b=true;
			}	
		}
		
		
		if(email==""|| email==null){
			$('#siteAdminEmail_error').html('*Email Cannot Be Empty');
			c=false;
		}
		else if(emailFilter.test(email)==false){
			$('#siteAdminEmail_error').html('*You Should Enter Valid Email');
			c=false;
		}
		else{
			$('#siteAdminEmail_error').html('');
			c=true;
		}
		
		
		if(logo!="" && logo!=null){
			if (logoExtension!="PNG" && logoExtension!="JPG" && logoExtension!="GIF" && logoExtension!="JPEG"){
				$('#logo_error').html('*Only .png, .jpg, .gif, .jpeg are allowed');
				d=false;
	        }
			else{
				$('#logo_error').html('');
				d=true;
			}
		}
		 
		if(orgName=="" || orgName==null){
			$('#organizationName_error').html('*Organization Name Cannot Be Empty');
			e=false;
		}
		else{
			$('#organizationName_error').html('');
			e=true;
		}
		if(orgShortName==""|| orgShortName==null){
			$('#organizationShortName_error').html('*Organization ShortName Cannot Be Empty');
			f=false;
		}
		else if(orgShortName.length >'4'){
			$('#organizationShortName_error').html('*Organization ShortName Length Should Be LessthanOrEqual to 4');
			f=false;
		}
		else{
			$('#organizationShortName_error').html('');
			f=true;
		}
		
		
		if(a==true && b==true && c==true && d==true && e==true && f==true){
			if(pwdStyle=="none"){
				$('.pwdDiv').remove();
			}
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	/*Added by bhagya on august 06th,2014
	 * Method to Validate Configuration Settings form
	 */
	
		function validateConfiguration(){
			alert("inside validation");
			var timecardPeriod=$('#timecardPeriod').val();
			var weekEndingDay=$('#weekEndingDay').val();
			var minHours=$('#minHoursPerWeek').val();
			var maxHours=$('#maxHoursPerWeek').val();
			var whineList=$('#whineList').val();
			var numFilter=/^[0-9.]+$/;
			var a,b,c,d,e=true;
			if(timecardPeriod=='--Select--'||timecardPeriod==''|| timecardPeriod==null){
				$('#timecardperiod_error').html('*Timecard Period Should be select');
				a=false;
			}
			else{
				$('#timecardperiod_error').html('');
				a=true;
			}
			if(weekEndingDay==null||weekEndingDay==''|| weekEndingDay=='--Select--'){
				$('#weekEndingDay_error').html('*Timecard Period Should be select');
				b=false;
			}
			else{
				$('#weekEndingDay_error').html('');
				b=true;
			}
			if(minHours==null|| minHours==''){
				$('#minHoursPerWeek_error').html('*Min Hours Per Week Should be entered');
				c=false;
			}
			else if(numFilter.test(minHours)==false){
				$('#minHoursPerWeek_error').html('*You Should Enter Only Digits');
				c=false;
			}
			else{
				$('#minHoursPerWeek_error').html('');
				c=true;
			}
			if(maxHours==null|| maxHours==''){
				$('#maxHoursPerWeek_error').html('*Max Hours Per Week Should be entered');
				d=false;
			}
			else if(numFilter.test(maxHours)==false){
				$('#maxHoursPerWeek_error').html('*You Should Enter Only Digits');
				d=false;
			}
			else{
				$('#maxHoursPerWeek_error').html('');
				d=true;
			}
			if(whineList=='--Select--'||whineList==null|| whineList==''){
				$('#whineList_error').html('*WhineList Should be select');
				e=false;
			}
			else{
				$('#whineList_error').html('');
				e=true;
			}
			if(a==true && b==true && c==true && d==true && e==true){
				return true;
			}
			else{
				return false;
			}
		}
		
		/**
		 * Created By Bhagya On Feb 24th,2015
		 * Function For Validating the Organization Settings page
		 *  */
		function validateOrgSettings(){
			var isLogsSaved=$('#isLogsSaved').val();
			var isUserLogsSaved=$('#isUserLogsSaved').val();
			var status=$('#orgStatus').val();
			var statusDate=$('#statusDate').val();
			var a,b,c,d=true;
			if(isLogsSaved=='--Select--'||isLogsSaved==''|| isLogsSaved==null){
				$('#isLogsSaved_error').html('*Logs Savings Should be select');
				a=false;
			}
			else{
				$('#isLogsSaved_error').html('');
				a=true;
			}
			if(isUserLogsSaved=='--Select--'||isUserLogsSaved==''|| isUserLogsSaved==null){
				$('#isUserLogsSaved_error').html('*User Logs Savings Should be select');
				b=false;
			}
			else{
				$('#isUserLogsSaved_error').html('');
				b=true;
			}
			if(status=='--Select--'||status==''|| status==null){
				$('#status_error').html('*User Logs Savings Should be select');
				c=false;
			}
			else{
				$('#status_error').html('');
				c=true;
			}
			if(statusDate==''|| statusDate==null){
				$('#statusDate_error').html('*Status Should be Entered');
				d=false;
			}
			else{
				$('#statusDate_error').html('');
				d=true;
			}
			
			if(a==true&& b==true&& c==true && d==true){
				return true;
			}
			else{
				return false;
			}
		}
		