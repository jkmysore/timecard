<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>${title }</title>
		
		
			<%@ include file="/jsp/common/common.jsp" %>
			<script src="http://code.jquery.com/jquery-latest.js"></script>
			<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script>
		 $(document).ready(function(){
				$('li').removeClass('active');
				$('#organization1').addClass('active');
				
				$("table tr:nth-child(even)").css('background-color', '#edeff0');
				/* $("#").css('width','21px'); */
				 $("#startDate").datepicker({
						showOn: "button",
						buttonImage: "${pageContext.request.contextPath}/images/calendar.gif",
						buttonImageOnly: true,
						
						 onSelect: function(selected) {
					          datepicker("option","maxDate", selected);
					           }
					});
					
				    $("#dateofBirth").datepicker({
						showOn: "button",
						buttonImage: "${pageContext.request.contextPath}/images/calendar.gif",
						buttonImageOnly: true,
						
						 onSelect: function(selected) {
					          datepicker("option","maxDate", selected);
					           }
					});
			    
			    
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
		<script src="${pageContext.request.contextPath}/js/employee.js"></script>
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
          <form:form class="form-horizontal" action="" id="basicForm" novalidate="novalidate" commandName="employeeDto"  onsubmit="return validateEmployee();">
	          <div class="panel panel-default">
	          <div class="titleHeader">
	              	Add Employee
	              	</div>
	              	
	              <div class="panel-body">
	             <div class="form-group">
	                  <label class="col-sm-2 control-label" style="text-align: left;">Organization Name<span class="asterisk">*</span></label>
	                   <div class="col-sm-4 ">
	                   <form:input  type="text"  placeholder="Type your Organization Name..." class="form-control" path="organization.organizationName" name="organizationName" id="organizationName" value="${organization.organizationName}" readonly="true" />
	                   <span id="organizationName_error" class="org_error"></span>
	                   <form:errors  id="organizationName_error" class="org_error" path="organization.organizationName" />
	                  </div>
	                 </div>
	               <div class="form-group">
		                <label class="col-sm-2 control-label" style="text-align: left;">Employee Id <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type Employee Id..." class="form-control" path="employeeNo" id="employeeId" /><span id="employeeId_error" style="color:red"></span>
		                    <form:errors path="employeeNo" id="employeeId_error" class="org_error"/>
		                  </div>
	                </div>
	                
	                <div class="form-group">
	                  <label class="col-sm-2 control-label" style="text-align: left;">Division Name<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:select path="division.divisionId" required="required" class="choose form-control" id="divisionName" >	                    
	                    	<form:option value="">--Select--</form:option>
	                    	<c:forEach var="division" items="${divisions}">
	                    		<form:option value="${division.divisionId}" >${division.divisionName}</form:option>
	                    	</c:forEach>	                    
	                    </form:select><span id="divisionName_error" style="color:red"></span>
	                    <form:errors  id="divisionName_error" class="org_error" path="division.divisionId" />
	                  </div>
	                 </div>
	                 
					<div class="form-group">
		                <label class="col-sm-2 control-label" style="text-align: left;" >First Name <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type Employee First Name..." class="form-control" path="timecardUser.firstName" id="firstName" /><span id="firstName_error" style="color:red"></span>
		                    <form:errors path="timecardUser.firstName" id="firstName_error" class="org_error"/>
		                  </div>
	                </div>
	                <div class="form-group">
		                <label class="col-sm-2 control-label" style="text-align: left;">Last Name <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type Employee Last Name..." class="form-control" path="timecardUser.lastName" id="lastName" /><span id="lastName_error" style="color:red"></span>
		                    <form:errors path="timecardUser.lastName" id="lastName_error" class="org_error"/>
		                  </div>
	                </div>
					<div class="form-group">
		                <label class="col-sm-2 control-label" style="text-align: left;">Username <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type Employee User Name..." class="form-control" path="timecardUser.timeCardCredentials.username" id="username" /><span id="username_error" style="color:red"></span>
		                    <form:errors path="timecardUser.timeCardCredentials.username" id="username_error" class="org_error"/>
		                  </div>
	                </div>
					
					
					
					<div class="form-group">
		                  <label class="col-sm-2 control-label" style="text-align: left;">Email <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="siteAdminEmail" required="required" placeholder="Type Employee Email..." class="form-control" path="timecardUser.timeCardCredentials.email" id="email" /><span id="email_error" style="color:red"></span>
		                  	  <form:errors path="timecardUser.timeCardCredentials.email" id="email_error" class="org_error"/>
		                  </div>
	                </div>
					
					<div class="form-group">
		                  <label class="col-sm-2 control-label" style="text-align: left;">Password <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="email" required="required" placeholder="Type Employee Password..." class="form-control" path="timecardUser.timeCardCredentials.password"  id="password" /><span id="password_error" style="color:red"></span>
		                      <form:errors path="timecardUser.timeCardCredentials.password" id="password_error" class="org_error"/>
		                  </div>
	                </div>
	                <div class="form-group">
		                  <label class="col-sm-2 control-label" style="text-align: left;">Confirm Password <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="email" required="required" placeholder="Type Employee Confirm Password..." class="form-control" path="confirmPassword"  id="confirmPassword"  /><span id="confirmPassword_error" style="color:red"></span>
		                      <form:errors path="confirmPassword" id="confirmPassword_error" class="org_error"/>
		                  </div>
	                </div>
	                <div class="form-group">
		                  <label class="col-sm-2 control-label"style="text-align: left;" >Employee Start Date <span class="asterisk">*</span></label>
		                  <div class="col-sm-4">
		                    <form:input type="text" required="required" placeholder="Type  Employee StartDate..." class="date-style form-control" path="startDate"  id="startDate" style="display: -webkit-inline-box;" /><span id="startDate_error" style="color:red"></span>
		                    
		                  </div>
	                </div>
	                <div class="form-group">
		                  <label class="col-sm-2 control-label" style="text-align: left;">Employee Date Of Birth <span class="asterisk">*</span></label>
		                  <div class="col-sm-4"  >
		                    <form:input type="text" required="required" placeholder="Type  Employee Date Of Birth..." class="date-style form-control" path="timecardUser.dateofBirth"  id="dateofBirth" style="display: -webkit-inline-box;" /><span id="dateofBirth_error" style="color:red"></span>
		                  
		                 	</div>
	                </div>
	                
	           </div><!-- panel-body -->
	           
	           
	           <div class="panel-footer">
		            <div class="row">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <button class="btn btn-primary">Submit</button>
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
