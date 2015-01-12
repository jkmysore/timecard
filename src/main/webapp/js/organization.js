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
		var usrFilter=/^[A-Za-z0-9 ]{8,20}$/;
		var emailFilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i ;
		var logoExtension = logo.split('.').pop().toUpperCase();
		var a,b,c,d,e,f=true;
		
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
		if(logo==""||logo==null){
			$('#logo_error').html('*You Should Upload  Logo');
			d=false;
		}
		else if (logoExtension!="PNG" && logoExtension!="JPG" && logoExtension!="GIF" && logoExtension!="JPEG"){
			$('#logo_error').html('*Only .png, .jpg, .gif, .jpeg are allowed');
			d=false;
        }
		else{
			$('#logo_error').html('');
			d=true;
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
			f=true
		}
		if(a==true && b==true && c==true && d==true && e==true && f==true){
			return true;
		}
		else{
			return false
		}
		
		
	}
	
	/*Added by bhagya on august 06th,2014
	 * Method to Validate Configuration Settings form
	 */
	
		function validateConfiguration(){
			var timecardPeriod=$('#timecardPeriod').val();
			var weekEndingDay=$('#weekEndingDay').val();
			var minHours=$('#minHoursPerWeek').val();
			var maxHours=$('#maxHoursPerWeek').val();
			var whineList=$('#whineList').val();
			var numFilter=/^[0-9.]+$/;
			var whineFilter=/^[0-9a-zA-Z ]+$/;
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
			if(whineList==null|| whineList==''){
				$('#whineList_error').html('*WhineList  Should be entered');
				e=false;
			}
			else if(whineFilter.test(whineList)==false){
				$('#whineList_error').html('*Special Characters Should Not Allowed');
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
				return false
			}
		}
		
		