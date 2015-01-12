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
		<script src="${pageContext.request.contextPath}/js/division.js"></script>
			<style type="text/css">
			.ui-datepicker-trigger{
			z-index: 99999;
		}
		
			</style>			
		
	
</head>

<body>

	<!-- Preloader -->
	<div id="preloader">
	    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
	</div>
	
	<section> 
	  
	  <!--  Left Panel -->
	  	<%@ include file="/jsp/panels/siteAdminPanel.jsp" %>
	  <!--  Left Panel -->
	  
	  
	  <div class="mainpanel">	    
	   
		   <!--  Header Bar -->
		    	<%@ include file="/jsp/home/header.jsp" %>
		    <!--  Header Bar -->
	<div class="contentpanel">
    
     
      <div class="row">
       
        <div class="col-md-11">
          <form:form class="form-horizontal" action=""  id="basicForm" novalidate="novalidate" commandName="divisionDto" enctype="multipart/form-data" onsubmit="return validateDivision();" >
	          <div class="panel panel-default">
	          <div class="titleHeader">
	              	Edit Division
	              	</div>
	              <div class="panel-body">
	               <div class="form-group">
	                  <label class="col-sm-2 control-label" > Organization Name  <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:select path="organization.organizationName" required="required" class="form-control" id="organizationName">	                    
	                    	<form:option value="${division.organization.organizationId}">${division.organization.organizationName}</form:option>
	                        	<c:forEach var="organization" items="${organizations}">
	                    		<form:option value="${organization.organizationId}" >${organization.organizationName}</form:option>
	                    	</c:forEach>	                    
	                    </form:select><span id="organizationName_error" style="color:red"></span>
	                    <form:errors  id="organizationName_error" class="org_error" path="organization.organizationName" />
	                  </div>
	                 </div>
					
	                <div class="form-group">
		                <label class="col-sm-2 control-label" >Division Id<span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type your Division Id..." class="form-control" path="divisionNo" id="departmentId" value="${division.divisionNo}"/><span id="departmentId_error" style="color:red"></span>
		                    <form:errors path="divisionNo" id="divisionNo_error" class="org_error"/>
		                  </div>
	                </div>
					<div class="form-group">
		                <label class="col-sm-2 control-label" >Division Name<span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type your Division Name..." class="form-control" path="divisionName" id="departmentName" value="${division.divisionName}"/><span id="departmentName_error" style="color:red"></span>
		                    <form:errors path="divisionName" id="divisionName_error" class="org_error"/>
		                  </div>
	                </div>
					
					
					
					
	               
	                
	           </div><!-- panel-body -->
	           
	           
	           <div class="panel-footer">
		            <div class="row">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <button class="btn btn-primary">EDIT</button>
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
