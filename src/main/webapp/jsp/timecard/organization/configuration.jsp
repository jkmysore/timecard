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
    
    
      <div class="row">
        
        <div class="col-md-11">
          <form:form class="form-horizontal" action="${pageContext.request.contextPath}/organization/configuration.htm" method="post" id="basicForm" novalidate="novalidate" commandName="configuration" enctype="multipart/form-data" onsubmit="return validateOrganization();">
	          <div class="panel panel-default">
	               <div class="titleHeader">Configuration Settings</div>
	              <div class="panel-body">
	                <div class="form-group">
	                  <label class="col-sm-2 control-label"> Timecard Period  <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:select path="timecardPeriod.id" required="required" class="form-control" >	                    
	                    	<form:option value="">--Select--</form:option>
	                    	<c:forEach var="period" items="${timecardPeriods }">
	                    		<form:option value="${period.id}">${period.timeCardPeriod }</form:option>
	                    	</c:forEach>	                    
	                    </form:select>
	                    <form:errors  id="timecardPeriod_error" class="org_error" path="timecardPeriod.id" />
	                  </div>
	                 </div>
					
					
					<div class="form-group">
	                  <label class="col-sm-2 control-label">Week Ending Day<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:select path="weekEndingDay" required="required" class="form-control" >	                    
	                    	<form:option value="">--Select--</form:option>
	                    		<form:option value="1">Monday</form:option>
	                    		<form:option value="2">Tuesday</form:option>
	                    		<form:option value="3">Wednesday</form:option>     
	                    		<form:option value="4">Thursday</form:option>     
	                    		<form:option value="5">Friday</form:option> 
	                    		<form:option value="6">Saturday</form:option>
	                    		<form:option value="7">Sunday</form:option>
	                    </form:select>
	                 	 <form:errors id="weekEndingDay_error" class="org_error" path="weekEndingDay"/>
	                  </div>
	                </div>
	                
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">Min Hours Per Week<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Short Name..." class="form-control" path="minHoursPerWeek" />
	                 	 <form:errors id="minHoursPerWeek_error" class="org_error" path="minHoursPerWeek"/>
	                  </div>
	                </div>
				
					 <div class="form-group">
	                  <label class="col-sm-2 control-label">Max Hours Per Week<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Short Name..." class="form-control" path="maxHoursPerWeek" />
	                 	 <form:errors id="maxHoursPerWeek_error" class="org_error" path="maxHoursPerWeek"/>
	                  </div>
	                </div>
					
					 <div class="form-group">
	                  <label class="col-sm-2 control-label">Whine List<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <form:input type="text" required="required" placeholder="Type your Organization Short Name..." class="form-control" path="whineList" />
	                 	 <form:errors id="whineList_error" class="org_error" path="whineList"/>
	                  </div>
	                </div>
					
					
					<input type="hidden" name="userId" value="${userId}" /> 
				
	                
	           </div><!-- panel-body -->
	           
	           <!--  To Make it Visible for Change Password Condition -->
	          <c:if test="${empty firstVisit}" > 
		           <div class="panel-footer">
			            <div class="row">
			             	<div class="col-sm-9 col-sm-offset-3">
			                    <button class="btn btn-primary">Submit</button>
			                    <button class="btn btn-default" type="reset">Reset</button>
			                 </div>
			            </div>
			        
		          </div>
	         </c:if>	 
	         
	        <c:if test="${firstVisit eq true }">
	         <!--  These Buttons Will be Displayed during First Visit  -->
	          <div style="margin-right: 1%;text-align: right;padding: 13px;">      	      		
      						<button class="btn btn-primary">Next</button>
      						 <a href="${pageContext.request.contextPath}/firstlogin.htm?userId=${userId}" class="btn btn-default" type="reset">Skip</a>    
		                   
    		  </div>  
    		</c:if>     
	      </div><!-- panel -->
	    
	      
       </form:form>          
             
     </div>    </div>
      
     
      
      
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	  
	</section>



</body>


</html>
