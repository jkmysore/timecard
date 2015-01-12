<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>${title }</title>

			<%@ include file="/jsp/common/common.jsp" %>
	
 		 <link href="${pageContext.request.contextPath}/css/jquery.datatables.css" rel="stylesheet">		
		<script src="${pageContext.request.contextPath}/js/jquery.sparkline.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/toggles.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/retina.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.cookies.js"></script>		
		<script src="${pageContext.request.contextPath}/js/flot.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/flot.resize.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/morris.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/raphael-2.1.0.min.js"></script>		
		<script src="${pageContext.request.contextPath}/js/jquery.datatables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/chosen.jquery.min.js"></script>	
		
	
</head>

<body>

	<!-- Preloader -->
	<div id="preloader">
	    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
	</div>
	
	<section> 
	  
	  <!--  Left Panel -->
	  	<%@ include file="/jsp/panels/leftPanel.jsp" %>
	  <!--  Left Panel -->
	  
	  
	  <div class="mainpanel">	    
	   
		   <!--  Header Bar -->
		    	<%@ include file="/jsp/home/header.jsp" %>
		    <!--  Header Bar -->
	<div class="contentpanel">
    
     <c:if test="${firstVisit eq true}">
    	  <div class="title">	You are Logged in for First Time, Please Change Your Password.     </div>
     </c:if>
    	
      <div class="row">
        
        <div class="col-md-11">
          <form:form class="form-horizontal" action="${pageContext.request.contextPath}/changepassword.htm" method="post" id="basicForm" novalidate="novalidate"  enctype="multipart/form-data" commandName="changePassword" onsubmit="return validateOrganization();">
	          <div class="panel panel-default">
	               <div class="titleHeader">Change Password</div>
	               <div class="title" style="color: #636e7b;">
	               		${message }
	               </div>
	              <div class="panel-body">
	                <div class="form-group">
	                  <label class="col-sm-2 control-label"> Current Password  <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="password" required="required" placeholder="Type your Organization Name..." class="form-control" path="oldPassword" />
	                    <form:errors  id="oldPassword_error" class="org_error" path="oldPassword" />
	                  </div>
	                 </div>
					
					<div class="form-group">
	                  <label class="col-sm-2 control-label">New Password<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="password" required="required" placeholder="Type your Organization Short Name..." class="form-control" path="newPassword" />
	                 	 <form:errors id="newPassword_error" class="org_error" path="newPassword"/>
	                  </div>
	                </div>
					
	            
					<div class="form-group">
		                <label class="col-sm-2 control-label">Confirm Password<span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="password" required="required" placeholder="Type your Site Admin User Name..." class="form-control" path="confirmPassword"/>
		                    <form:errors  id="confirmPassword_error" class="org_error" path="confirmPassword" />
		                  </div>
	                </div>
					
					
					<form:input type="hidden" path="userId" value="${userId}" /> 
				
	                
	           </div><!-- panel-body -->
	           
	           <!--  To Make it Visible for Change Password Condition -->
	          <c:if test="${empty firstVisit }" > 
		           <div class="panel-footer">
			            <div class="row">
			             	<div class="col-sm-9 col-sm-offset-3">
			                    <button class="btn btn-primary">Submit</button>
			                    <button class="btn btn-default" type="reset">Reset</button>
			                 </div>
			            </div>
			        
		          </div>
	         </c:if>	 
	         
	         <!--  These Buttons Will be Displayed during First Visit  -->
	          <div style="margin-right: 1%;text-align: right;padding: 13px;">      	      		
      						<button class="btn btn-primary">Next</button>
      						<c:choose>
      							<c:when test="${userType eq 'SITE_ADMIN'}">
      									<a href="${pageContext.request.contextPath}/organization/configuration.htm?userId=${userId}&firstVisit=${firstVisit}" class="btn btn-default" type="reset">Skip</a> 
      							</c:when>
      							<c:otherwise>
      									 <a href="${pageContext.request.contextPath}/firstlogin.htm?firstVisit=${firstVisit}" class="btn btn-default" type="reset">Skip</a>      
      							</c:otherwise>
      						</c:choose>
      						
      						
      						
		                   
    		  </div>           
	      </div><!-- panel -->
	       <input type="hidden" name="userType" value="${userType}"> 
	       <input type="hidden" name="firstVisit" value="${firstVisit }"> 
       </form:form>          
             
     </div>
    </div>
      
     
      
      
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	  
	</section>



</body>


</html>
