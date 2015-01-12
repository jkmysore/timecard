/**
 * User.js
 * 
 * Created by Jeevan on June 25, 2014
 */

$(document).ready(function(){
	
	
});


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


