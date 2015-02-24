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
				
				$('.pwdAnchr').click(function(){
					$('.pwdAnchr').css('display','none');
					$('.pwdDiv').css('display','block');
				});
				
				$('.removePwd').click(function(){
					$('.pwdAnchr').css('display','block');
					$('.pwdDiv').css('display','none');
				});
				
				
		 });
		 </script>	
		
 		
		<script src="${pageContext.request.contextPath}/js/organization.js"></script>
						
		
	
</head>

<body>

	<!-- Preloader -->
	<div id="preloader">
	    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
	</div>
	
	<section> 
	  
	  <!--  Left Panel -->
	  	<%@ include file="/jsp/panels/superAdminPanel.jsp" %>
	  <!--  Left Panel -->
	  
	  
	  <div class="mainpanel">	    
	   
		   <!--  Header Bar -->
		    	<%@ include file="/jsp/home/header.jsp" %>
		    <!--  Header Bar -->
		    
	<div class="pageheader">
	      <h2><i class="fa fa-users"></i> Edit Organization</h2>
	      <div class="breadcrumb-wrapper">
	        <span class="label">You are here:</span>
	        <ol class="breadcrumb">
	          <li><a href="${pageContext.request.contextPath}/home.htm">KNS TECHNOLOGIES</a></li>
	          <li><a href="${pageContext.request.contextPath}/admin/organizations.htm">Organization</a></li>
	           <li class="active">Edit Organization</li>
	        </ol>
	      </div>
	   </div>
	    	    
		    
		    
		    
	<div class="contentpanel">
    
      <c:if test="${not empty param.message }">
      		<div class="title">
      				Organization Created Successfully
      		</div>      	
      </c:if>
    	
      <div class="row">
        
        <div class="col-md-11">
          <form:form class="form-horizontal" action="${pageContext.request.contextPath}/admin/editorganization.htm" id="basicForm" novalidate="novalidate" commandName="organization" enctype="multipart/form-data" onsubmit="return validateOrganization();">
	          <div class="panel panel-default">
	              	<div class="titleHeader">
	              	Edit Organization 
	              	</div>
	              <div class="panel-body">
	              	<form:hidden path="organizationId" value="${organizationDto.organizationId }"/>
	              
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">Organization Name <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Name..." class="form-control" path="organizationName" id="organizationName" value="${organizationDto.organizationName}"/><span id="organizationName_error" style="color:red"></span>
	                    <form:errors path="organizationName" id="organizationName_error" cssClass="org_error"/>
	                  </div>
	                 </div>
					
					<div class="form-group">
	                  <label class="col-sm-2 control-label">Organization Short Name <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Short Name..." class="form-control" path="organizationShortName"  id="organizationShortName" value="${organizationDto.organizationShortName}" /><span id="organizationShortName_error" style="color:red"></span>
	                 	<form:errors path="organizationShortName" id="organizationShotName_error" cssClass="org_error"/>
	                  </div>
	                </div>
					
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">Logo :</label>
	                  <div class="col-sm-4">
	                 		<img src="/timecard_images/org/${organizationDto.organizationId}/${organizationDto.logoPath}" width="32" height="32" />
	                  </div>	                
	               </div>
	               <div class="form-group">
	               	  <label class="col-sm-2 control-label">Change Logo</label>
	                  <div class="col-sm-4">
		                    <form:input type="file" required="required" placeholder="Logo file" class="form-control" path="logo" id="logo" /><span id="logo_error" style=" color:red"></span>
		                    <form:errors path="logo" id="logo_error" class="org_error"/>
		                  </div>
	               
	               </div>
					
					<div class="form-group">
		                <label class="col-sm-2 control-label">Site Admin User Name</label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type your Site Admin User Name..." class="form-control" path="siteAdmin.timeCardCredentials.username" id="siteAdminUserName" value="${organizationDto.siteAdmin.timeCardCredentials.username }"/><span id="siteAdminUserName_error" style="color:red"></span>
		                    <form:errors path="siteAdmin.timeCardCredentials.username" id="siteAdminUserName_error" class="org_error"/>
		                  </div>
	                </div>
					
					
					
					<div class="form-group">
		                  <label class="col-sm-2 control-label">Site Admin email <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="siteAdminEmail" required="required" placeholder="Type your Site Admin email..." class="form-control" path="siteAdmin.timeCardCredentials.email" id="siteAdminEmail" value="${organizationDto.siteAdmin.timeCardCredentials.email }"/><span id="siteAdminEmail_error" style="color:red"></span>
		                  	  <form:errors path="siteAdmin.timeCardCredentials.email" id="siteAdminEmail_error" class="org_error"/>
		                  </div>
	                </div>
	                
	                <div class="form-group">
	               		<label class="col-sm-2"></label>
	               		
	               		<div class="col-sm-4 pwdAnchr">
	               			<a href="#">Click Here to Reset Site Admin Password</a>	
	               		</div>
	                </div>
	                
					
					<div class="form-group pwdDiv" style="display:none;" >
		                  <label class="col-sm-2 control-label">Enter Password <span class="asterisk">*</span></label>
		                  <div class="col-sm-3">
		                    <form:input type="email" required="required" placeholder="Type your Site Admin Password..." class="form-control" path="siteAdmin.timeCardCredentials.password"  id="siteAdminPassword" /><span id="siteAdminPassword_error" style="color:red"></span>
		                      <form:errors path="siteAdmin.timeCardCredentials.password" id="siteAdminPassword_error" class="org_error"/>
		                  </div>
		                  <div class="col-sm-1" style="padding-top: 3px;">
		                 		 <a href="#" class="removePwd">Remove</a>	
		                  </div>
	                </div>
	                
	           </div><!-- panel-body -->
	           
	           
	           <div class="panel-footer">
		            <div class="row">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <button class="btn btn-primary">Edit</button>
		                    <button class="btn btn-default" type="reset" onclick="window.location='${pageContext.request.contextPath}/admin/editorganization.htm?organizationId=${organizationDto.organizationId }'">Reset</button>
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
