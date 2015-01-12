/**
	Author: Bhagya
	Created on : October 27, 2014
	
	Javascript file to handle or perfom validations related to Employee

*/

/*Added by bhagya on October 27th,2014
		 * Function for Validating the Employee existence
		 */
		
		function validateEmpNo(){
			var empId=$('#employeeId').val();
			var validationResult=false;
			$.ajax({
				url : 'http://localhost:8080/timecard/employee/checkemployee.htm',
				type: 'GET',
				data:{empNo:empId},
				async: false,
				success:function(data){
					if(data==="success"){
						validationResult=true;
						$('#employeeId_error').text('');
					}
					else{
						validationResult=false;
						$('#employeeId_error').text('*Employee Id Already Exists');
					}
				}			
			});
			
			return validationResult;
		}
		/*Added by bhagya on October 27th,2014
		 * Function for Validating the Employee existence
		 */
		
		function validateEmpEmail(){
			var email=$('#email').val();
			var validationResult1=false;
			$.ajax({
				url : 'http://localhost:8080/timecard/employee/checkempemail.htm',
				type: 'GET',
				data:{email:email},
				async: false,
				success:function(data){
					if(data==="success"){
						validationResult1=true;
						$('#email_error').text('');
					}
					else{
						validationResult1=false;
						$('#email_error').text('*Email Already Exists');
					}
				}			
			});
			return validationResult1;
		}
		
		/*Added by bhagya on October 27th,2014
		 * Function for Validating the Add Employee Page
		 */
		
		
		function validateEmployee(){
			var empId=$('#employeeId').val();
			var fname=$('#firstName').val();
			var lname=$('#lastName').val();
			var uname=$('#username').val();
			var email=$('#email').val();
			var pwd=$('#password').val();
			var cpwd=$('#confirmPassword').val();
			var divisionName=$('#divisionName').val();
			var  orgName=$('#organizationName').val();
			var startDate=$('#startDate').val();
			var dateofBirth=$('#dateofBirth').val();
			var nameFilter3=/^[0-9a-zA-Z ]+$/;
			var nameFilter=/^[0-9a-zA-Z ]+$/;
			var nameFilter1=/^[0-9a-zA-Z ]+$/;
			var nameFilter2=/^[0-9a-zA-Z ]+$/;
			var emailFilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i ;
			var pwdFilter=/^[A-Za-z0-9!@#$%^&*()_]{8,20}$/;
			var a,b,c,d,e,f,g,h,i,j,k=false;
			var validationResult,validationResult1=false;
			if(empId==null || empId==''){
				$('#employeeId_error').html('*Employee Id Should Not Be Empty');
				a=false;
			}
			else if(nameFilter3.test(empId)==false){
				$('#employeeId_error').html('*Special Characters Should Not Allowed');
				a=false;
			}
			else{
				$('#employeeId_error').html('');
					a=true;
				}
				
		
			if(fname==null || fname==''){
				$('#firstName_error').html('*First Name Should Not Be Empty');
				b=false;
			}
			else if(nameFilter.test(fname)==false){
				$('#firstName_error').html('*Special Characters Should Not Allowed');
				b=false;
			}
			else{
				$('#firstName_error').html('');
				b=true;
			}
			if(lname==null || lname==''){
				$('#lastName_error').html('*Last Name Should Not Be Empty');
				c=false;
			}
			else if(nameFilter1.test(lname)==false){
				$('#lastName_error').html('*Special Characters Should Not Allowed');
				c=false;
			}
			else{
				$('#lastName_error').html('');
				c=true;
			}
			if(uname==null || uname==''){
				$('#username_error').html('*User Name Should Not Be Empty');
				d=false;
			}
			else if(nameFilter2.test(uname)==false){
				$('#username_error').html('*Special Characters Should Not Allowed');
				d=false;
			}
			else{
				$('#username_error').html('');
				d=true;
			}
			if(email==""|| email==null){
				$('#email_error').html('*Email Cannot Be Empty');
				e=false;
			}
			else if(emailFilter.test(email)==false){
				$('#email_error').html('*You Should Enter Valid Email');
				e=false;
			}
			else{
				$('#email_error').html('');
				e=true;
			}
			if(pwd=="" || pwd==null ){
				$('#password_error').html('*Password Cannot Be Empty');
				f=false;
			}
			else if(pwdFilter.test(pwd)==false){
				$('#password_error').html('*Password Length Should Be 8 to 20 Charactes');
				f=false;
				}
			else{
				$('#password_error').html('');
				f=true;
				}
			if(cpwd==null|| cpwd=='' ){
				$('#confirmPassword_error').html('*Confirm Password Cannot Be Empty');
				g=false;
			}
			else if(cpwd!=pwd){
				$('#confirmPassword_error').html('*Password And Confirm Password Should Match');
				g=false;
				}
			else{
				$('#confirmPassword_error').html('');
				g=true;
				}
			if(divisionName=='--Select--'||divisionName==''|| divisionName==null){
				$('#divisionName_error').html('*Division Name Should be select');
				h=false;
			}
			else{
				$('#divisionName_error').html('');
				h=true;
			}
			if(orgName=='--Select--'||orgName==''|| orgName==null){
				$('#organizationName_error').html('*Organization Name Should be select');
				i=false;
			}
			else{
				$('#organizationName_error').html('');
				i=true;
			}
			if(startDate==null|| startDate==''){
				$('#startDate_error').html('*Employee Start Date Cannot Be Empty');
				j=false;
			}
			else{
				$('#startDate_error').html('');
				j=true;
			}
			if(dateofBirth==null|| dateofBirth==''){
				$('#dateofBirth_error').html('*Employee Date Of Birth Cannot Be Empty');
				k=false;
			}
			else{
				$('#dateofBirth_error').html('');
				k=true;
			}
			validationResult=validateEmpNo();
			validationResult1=validateEmpEmail();
			if(a==true && b==true && c==true && d==true && e==true && f==true && g==true && h==true && i==true && j==true && k==true && validationResult==true && validationResult1==true){
				
						return true;
					
			}
			else{
				
				return false;
			}
			
		}
		
		/*Added by bhagya on October 27th,2014
		 * Function for Validating the Manager existence
		 */
		
		function validateEmpNoForManager(){
			var empId=$('#employeeId').val();
			var validationResult=false;
			$.ajax({
				url : 'http://localhost:8080/timecard/employee/checkmanager.htm',
				type: 'GET',
				data:{empNo:empId},
				async: false,
				success:function(data){
					
					if(data==="success"){
						
						validationResult=true;
						$('#employeeId_error').text('');
					}
					else{
						
						validationResult=false;
						$('#employeeId_error').text('*Employee Id Already Exists');
					}
				}			
			});
			
			return validationResult;
		}
		
		
		/*Added by bhagya on October 27th,2014
		 * Function for Validating the Manager Existence
		 */
		
		function validateMngEmail(){
			var email=$('#email').val();
			var validationResult1=false;
			$.ajax({
				url : 'http://localhost:8080/timecard/employee/checkmanageremail.htm',
				type: 'GET',
				data:{email:email},
				async: false,
				success:function(data){
					if(data==="success"){
						validationResult1=true;
						$('#email_error').text('');
					}
					else{
						validationResult1=false;
						$('#email_error').text('*Email Already Exists');
					}
				}			
			});
			return validationResult1;
		}
		
		
		/*Added by bhagya on October 27th,2014
		 * Function for Validate The Edit Employee 
		 */
		
		function validateEditEmployee(){
			var empId=$('#employeeId').val();
			var fname=$('#firstName').val();
			var lname=$('#lastName').val();
			var uname=$('#username').val();
			var email=$('#email').val();
			var divisionName=$('#divisionName').val();
			var  orgName=$('#organizationName').val();
			var startDate=$('#startDate').val();
			var dateofBirth=$('#dateofBirth').val();
			var nameFilter3=/^[0-9a-zA-Z ]+$/;
			var nameFilter=/^[0-9a-zA-Z ]+$/;
			var nameFilter1=/^[0-9a-zA-Z ]+$/;
			var nameFilter2=/^[0-9a-zA-Z ]+$/;
			var emailFilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i ;
			
			var a,b,c,d,e,h,i,j,k=false;
			var validationResult,validationResult1=false;
			if(empId==null || empId==''){
				$('#employeeId_error').html('*Employee Id Should Not Be Empty');
				a=false;
			}
			else if(nameFilter3.test(empId)==false){
				$('#employeeId_error').html('*Special Characters Should Not Allowed');
				a=false;
			}
			else{
				$('#employeeId_error').html('');
					a=true;
				}
				
		
			if(fname==null || fname==''){
				$('#firstName_error').html('*First Name Should Not Be Empty');
				b=false;
			}
			else if(nameFilter.test(fname)==false){
				$('#firstName_error').html('*Special Characters Should Not Allowed');
				b=false;
			}
			else{
				$('#firstName_error').html('');
				b=true;
			}
			if(lname==null || lname==''){
				$('#lastName_error').html('*Last Name Should Not Be Empty');
				c=false;
			}
			else if(nameFilter1.test(lname)==false){
				$('#lastName_error').html('*Special Characters Should Not Allowed');
				c=false;
			}
			else{
				$('#lastName_error').html('');
				c=true;
			}
			if(uname==null || uname==''){
				$('#username_error').html('*User Name Should Not Be Empty');
				d=false;
			}
			else if(nameFilter2.test(uname)==false){
				$('#username_error').html('*Special Characters Should Not Allowed');
				d=false;
			}
			else{
				$('#username_error').html('');
				d=true;
			}
			if(email==""|| email==null){
				$('#email_error').html('*Email Cannot Be Empty');
				e=false;
			}
			else if(emailFilter.test(email)==false){
				$('#email_error').html('*You Should Enter Valid Email');
				e=false;
			}
			else{
				$('#email_error').html('');
				e=true;
			}
			
			if(divisionName=='--Select--'||divisionName==''|| divisionName==null){
				$('#divisionName_error').html('*Division Name Should be select');
				h=false;
			}
			else{
				$('#divisionName_error').html('');
				h=true;
			}
			if(orgName=='--Select--'||orgName==''|| orgName==null){
				$('#organizationName_error').html('*Organization Name Should be select');
				i=false;
			}
			else{
				$('#organizationName_error').html('');
				i=true;
			}
			if(startDate==null|| startDate==''){
				$('#startDate_error').html('*Employee Start Date Cannot Be Empty');
				j=false;
			}
			else{
				$('#startDate_error').html('');
				j=true;
			}
			if(dateofBirth==null|| dateofBirth==''){
				$('#dateofBirth_error').html('*Employee Date Of Birth Cannot Be Empty');
				k=false;
			}
			else{
				$('#dateofBirth_error').html('');
				k=true;
			}
			
			if(a==true && b==true && c==true && d==true && e==true && h==true && i==true && j==true && k==true ){
				
						return true;
					
			}
			else{
				
				return false;
			}
			
		}
		
		
		/*Added by bhagya on October 28th,2014
		 * Function for Validating the AddOrganizationManger Page
		 */
		
		
		function validateOrganizationManager(){
			var empId=$('#employeeId').val();
			var fname=$('#firstName').val();
			var lname=$('#lastName').val();
			var uname=$('#username').val();
			var email=$('#email').val();
			var pwd=$('#password').val();
			var cpwd=$('#confirmPassword').val();
			var divisionName=$('#divisionName').val();
			var  orgName=$('#organizationName').val();
			var nameFilter3=/^[0-9a-zA-Z ]+$/;
			var nameFilter=/^[0-9a-zA-Z ]+$/;
			var nameFilter1=/^[0-9a-zA-Z ]+$/;
			var nameFilter2=/^[0-9a-zA-Z ]+$/;
			var emailFilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i ;
			var pwdFilter=/^[A-Za-z0-9!@#$%^&*()_]{8,20}$/;
			var a,b,c,d,e,f,g,h,i=false;
			var validationResult,validationResult1=false;
			if(empId==null || empId==''){
				$('#employeeId_error').html('*Employee Id Should Not Be Empty');
				a=false;
			}
			else if(nameFilter3.test(empId)==false){
				$('#employeeId_error').html('*Special Characters Should Not Allowed');
				a=false;
			}
			else{
				$('#employeeId_error').html('');
				a=true;
			}
			if(fname==null || fname==''){
				$('#firstName_error').html('*First Name Should Not Be Empty');
				b=false;
			}
			else if(nameFilter.test(fname)==false){
				$('#firstName_error').html('*Special Characters Should Not Allowed');
				b=false;
			}
			else{
				$('#firstName_error').html('');
				b=true;
			}
			if(lname==null || lname==''){
				$('#lastName_error').html('*Last Name Should Not Be Empty');
				c=false;
			}
			else if(nameFilter1.test(lname)==false){
				$('#lastName_error').html('*Special Characters Should Not Allowed');
				c=false;
			}
			else{
				$('#lastName_error').html('');
				c=true;
			}
			if(uname==null || uname==''){
				$('#username_error').html('*User Name Should Not Be Empty');
				d=false;
			}
			else if(nameFilter2.test(uname)==false){
				$('#username_error').html('*Special Characters Should Not Allowed');
				d=false;
			}
			else{
				$('#username_error').html('');
				d=true;
			}
			if(email==""|| email==null){
				$('#email_error').html('*Email Cannot Be Empty');
				e=false;
			}
			else if(emailFilter.test(email)==false){
				$('#email_error').html('*You Should Enter Valid Email');
				e=false;
			}
			else{
				$('#email_error').html('');
				e=true;
			}
			if(pwd=="" || pwd==null ){
				$('#password_error').html('*Password Cannot Be Empty');
				f=false;
			}
			else if(pwdFilter.test(pwd)==false){
				$('#password_error').html('*Password Length Should Be 8 to 20 Charactes');
				f=false;
				}
			else{
				$('#password_error').html('');
				f=true;
				}
			if(cpwd=="" || cpwd==null ){
				$('#confirmPassword_error').html('*Confirm Password Cannot Be Empty');
				g=false;
			}
			else if(cpwd!=pwd){
				$('#confirmPassword_error').html('*Password And Confirm Password Should Match');
				g=false;
				}
			else{
				$('#password_error').html('');
				g=true;
				}
			if(divisionName=='--Select--'||divisionName==''|| divisionName==null){
				$('#divisionName_error').html('*Division Name Should be select');
				h=false;
			}
			else{
				$('#divisionName_error').html('');
				h=true;
			}
			if(orgName=='--Select--'||orgName==''|| orgName==null){
				$('#organizationName_error').html('*Organization Name Should be select');
				i=false;
			}
			else{
				$('#organizationName_error').html('');
				i=true;
			}
			validationResult=validateEmpNoForManager();
			validationResult1=validateMngEmail();
			if(a==true && b==true && c==true && d==true && e==true && f==true && g==true && h==true && i==true && validationResult==true && validationResult1==true){
				return true;
			}
			else{
				return false
			}
		}
		
		
		