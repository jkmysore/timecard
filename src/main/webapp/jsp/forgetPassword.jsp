<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>${title }</title>

	
	
 <%@ include file="common/common.jsp" %>
	
 <script src="js/user.js"></script>
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
			                        <h1 style="text-align:center;"><img src="images/logo.png"></h1>
			                 </div>
			                <form method="post" action="<c:url value='forgetpassword.htm' />" onsubmit="return validateForgetPasswordForm();">
			                    <h4 class="nomargin">Forget Password</h4>	
			                    <label style="margin-top: 10px;font-size: 12px;"> Email :</label> <span id="email_error"></span>	                   
			                    <input type="text" class="form-control" placeholder="Please Enter Your Email" name="email" id="email" style="margin-top: 0px;width: 95%;" onblur="checkEmail();"/>
			                 
			                    
			                 
			                    <button class="btn btn-success btn-block" type="submit">Retreive Password</button>
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
