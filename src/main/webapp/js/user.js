/**
 * User.js
 * 
 * Created by Jeevan on June 25, 2014
 */

/*$(document).ready(function(){
	
	
});
*/

/*
 * Method to validate if Email Exists
 */
var result=true; 
	function checkEmail(){
		var email=$('#email').val();
		
		$.ajax({
			url:'validatemail.htm',
			method:'get',
			data:{email:email},
			success:function(data){
				if(data=="false"){
					$('#email_error').html('* Email Id not Exists');
					result=false;
					return result;
				}
				else{
					$('#email_error').html('');
					result=true;;
					return result;
				}
			},
			error:function(){
				
			}
			
		});		
		return result
	}
	
	
	
	/*
	 * Method to validate Forget Password Form
	 */
	function validateForgetPasswordForm(){
		var emailFilter=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var email=$('#email').val();
		var validationResult=true;
		if(email.trim().length<1){
			validationResult=false;
			$('#email_error').html('* Please Enter Email Address');
		}
		else if(emailFilter.test(email)==false){
			validationResult=false;
			$('#email_error').html('* Please Enter Valid Email Address');
		}
		else{
			var a=checkEmail();				
			if( validationResult==true && a==true ){
				$('#email_error').html('');			
				return true;
			}
			else{
				return false;
			}			
		}	
		return false;
	}
	
	
	/*
	 * Method to validate Reset Password Form
	 */
	
	var pwd=true; 
	var cpwd=true;
	function validateResetPasswordForm(){
		var password=$('#password').val();
		var cpassword=$('#cpassword').val();
		
		
		if(password.trim().length<1){
			
			$('#password_error').html('* Please Enter Password');
			pwd=false;
		}
		else if(password.trim().length<8 || password.trim().lenght>20){
			$('#password_error').html('* Password Should have atleast 8 characters and maximum 20 characters');
			pwd=false;
		}
		else{
			$('#password_error').html('');
			pwd=true;
		}
		
		if(password!=cpassword){
			$('#cpassword_error').html('* Password and Confirm Password Should Match');
			cpwd=false;
		}
		else{			
			$('#cpassword_error').html('');			
			cpwd=true;
		}		
		if(pwd && cpwd){
			return true;
		}
		else{
			return false;
		}		
	}
	
	/* added by bhagya on july 07th,2014
	 * Validation the login.jsp form using jquery
	 */
	
	function validateLogin(){
		var uname=$('#uname').val();
		var pass=$('#pwd').val();
		var l=true;
		var m=true;
			if(uname===""||uname===null){
				$('#userId').text("* Username Cannot Be Empty");
				l=false;
			}
			
			else{
				$('#userId').text("");
				l=true;
			}	
		
			if(pass==="" || pass===null){
				$('#passwd').text("* Password Cannot Be Empty");
				m=false;
			}
			
			else{
				$('#passwd').text("");
				m=true;
			}
	
		if(l===true && m===true){
			return true;
		}
		else{
			return false;
		}
	}
		
		/*
		 * Added By Bhagya on October 21st,2014
		 * Method for Validate the Change Password Page
		 */
		
	function validateChangePassword(){
		var pwd=$('#oldPassword').val();
		var newpwd=$('#newPassword').val();
		var cpwd=$('#confirmPassword').val();
		var a,b,c=true;
		if(pwd.trim().length<1){
			
			$('#oldPassword_error').html('* Please Enter Old Password');
			a=false;
		}
		else if(pwd.trim().length<8 || pwd.trim().lenght>20){
			$('#oldPassword_error').html('* Password Should have atleast 8 characters and maximum 20 characters');
			a=false;
		}
		else{
			$('#oldPassword_error').html('');
			a=true;
		}
		if(newpwd.trim().length<1){
			
			$('#newPassword_error').html('* Please Enter New Password');
			b=false;
		}
		else if(newpwd.trim().length<8 || newpwd.trim().lenght>20){
			$('#newPassword_error').html('* Password Should have atleast 8 characters and maximum 20 characters');
			b=false;
		}
		else{
			$('#newPassword_error').html('');
			b=true;
		}
		if(newpwd!=cpwd){
			$('#confirmPassword_error').html('* New Password and Confirm Password Should Match');
			c=false;
		}
		else{			
			$('#confirmPassword_error').html('');			
			c=true;
		}	
		if(a==true && b==true && c==true){
			return true;
			
		}
		else{
			return false;
		}
	}
			 
		