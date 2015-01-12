<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>${title }</title>
		
		
			<%@ include file="/jsp/common/common.jsp" %>
			
		<script>
		 $(document).ready(function(){
				$('li').removeClass('active');
				$('#organization1').addClass('active');
		 });
		 </script>	
		
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
		<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap-fileupload.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/organization.js"></script>
						
		
	
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
    
      <c:if test="${not empty param.message }">
      		<div class="title">
      				Organization Created Successfully
      		</div>      	
      </c:if>
    	
      <div class="row">
        
        <div class="col-md-11">
          <form:form class="form-horizontal" action="" id="basicForm" novalidate="novalidate" commandName="organization" enctype="multipart/form-data" onsubmit="return validateOrganization();">
	          <div class="panel panel-default">
	              	<div class="titleHeader">
	              	Add Organization 
	              	</div>
	              <div class="panel-body">
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">Organization Name <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Name..." class="form-control" path="organizationName" id="organizationName"/><span id="organizationName_error" style="color:red"></span>
	                    <form:errors path="organizationName" id="organizationName_error" cssClass="org_error"/>
	                  </div>
	                 </div>
					
					<div class="form-group">
	                  <label class="col-sm-2 control-label">Organization Short Name <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Short Name..." class="form-control" path="organizationShortName"  id="organizationShortName"/><span id="organizationShortName_error" style="color:red"></span>
	                 	<form:errors path="organizationShortName" id="organizationShotName_error" cssClass="org_error"/>
	                  </div>
	                </div>
					
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">Upload Logo</label>
	                  <div class="col-sm-4">
		                    <form:input type="file" required="required" placeholder="Logo file" class="form-control" path="logo" id="logo" /><span id="logo_error" style=" color:red"></span>
		                    <form:errors path="logo" id="logo_error" class="org_error"/>
		                  </div>
	               </div>
					
					<div class="form-group">
		                <label class="col-sm-2 control-label">Site Admin User Name</label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type your Site Admin User Name..." class="form-control" path="siteAdmin.timeCardCredentials.username" id="siteAdminUserName"/><span id="siteAdminUserName_error" style="color:red"></span>
		                    <form:errors path="siteAdmin.timeCardCredentials.username" id="siteAdminUserName_error" class="org_error"/>
		                  </div>
	                </div>
					
					
					
					<div class="form-group">
		                  <label class="col-sm-2 control-label">Site Admin email <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="siteAdminEmail" required="required" placeholder="Type your Site Admin email..." class="form-control" path="siteAdmin.timeCardCredentials.email" id="siteAdminEmail" /><span id="siteAdminEmail_error" style="color:red"></span>
		                  	  <form:errors path="siteAdmin.timeCardCredentials.email" id="siteAdminEmail_error" class="org_error"/>
		                  </div>
	                </div>
					
					<div class="form-group">
		                  <label class="col-sm-2 control-label">Site Admin Password <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="email" required="required" placeholder="Type your Site Admin Password..." class="form-control" path="siteAdmin.timeCardCredentials.password"  id="siteAdminPassword" /><span id="siteAdminPassword_error" style="color:red"></span>
		                      <form:errors path="siteAdmin.timeCardCredentials.password" id="siteAdminPassword_error" class="org_error"/>
		                  </div>
	                </div>
	                
	           </div><!-- panel-body -->
	           
	           
	           <div class="panel-footer">
		            <div class="row">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <button class="btn btn-primary">Submit</button>
		                    <button class="btn btn-default" type="reset">Reset</button>
		                 </div>
		            </div>
	          </div>	            
	      </div><!-- panel -->
       </form:form>          
          
     </div>
    </div>
      
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	  
	</section>



</body>


</html>
