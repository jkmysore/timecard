<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
 

  <title>KNS TECHNOLOGIES Application - Login page</title>
  
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
<!-- <script src="http://code.jquery.com/jquery-latest.js"
        type="text/javascript"></script> -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/user.js"></script>
	
 <%@ include file="common/common.jsp" %>
</head>

<body class="signin">

	<!-- Preloader -->
	<div id="preloader">
	    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
	</div>

	<section>  
	    <div class="signinpanel">
			<div class="row">           
		            
		            <!-- col-sm-7 -->
		               <div class="col-md-4">
					   </div>
			            <div class="col-md-5">
			                <div class="logopanel">
			                        <h1 style="text-align:center;"><a href="home.htm"><img src="images/logo.png"></a></h1>
			                 </div>
			                <form method="post" id="loginForm" action="<c:url value='/j_spring_security_check' />" onsubmit="return validateLogin();">
			                    <h4 class="nomargin">Sign In</h4>
			                    <p class="mt5 mb20">Login to access your account.</p>
			                
			                    <input type="text" class="form-control uname" placeholder="Username" name="j_username" id="uname"/><span id="userId" style="color:red; "></span>
			                    
			                    <input type="password" class="form-control pword" placeholder="Password" name="j_password" id="pwd"/><span id="passwd" style="color:red; "></span>
			                    <input id="remember" name="_spring_security_remember_me" value="true" tabindex="7" type="checkbox"><small>Remember me</small>
			                    <br />
			                    <a href="forgetpassword.htm"><small>Forgot Your Password?</small></a>
								<!--  <small style="float:right;">Not a member? <a href="index.html">Sign Up</a></small>-->
			                    <button class="btn btn-success btn-block" >Sign In</button> 
			                </form>
			            </div>
						<div class="col-md-3">
						 </div>
		            <!-- col-sm-5 -->            
		        </div>        
	       	 <!-- row -->        
		        <div class="signup-footer">
		            <div class="pull-left">
		                CopyRight &copy; 2014. All Rights Reserved.
		            </div>
		            <div class="pull-right">
		                Created By: <a href="http://knstek.com/" target="_blank">KNS TECHNOLOGIES</a>
		            </div>
		        </div>        
	    	</div><!-- signin -->	  
	</section>



</body>
</html>
