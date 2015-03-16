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
		<script src="${pageContext.request.contextPath}/js/organization.js"></script>	
	<style>	
	/* .configuration_error{
		color:red;
		font-size: 12px;
		margin-left: 10px; 
	} */
	
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
          <form class="form-horizontal" action="${pageContext.request.contextPath}/org/configuration.htm" method="post" id="basicForm" onsubmit="return validateConfiguration();">
	          <div class="panel panel-default">
	               <div class="titleHeader">Configuration Settings</div>
	              <div class="panel-body">
	              <div class="form-group">
	                  <label class="col-sm-2 control-label">Organization Name<span class="asterisk">*</span></label>
	                   <div class="col-sm-4 ">
	                   <input  type="text"  placeholder="Type your Organization Name..." class="form-control" name="organizationName" id="organizationName" value="${organization.organizationName}" readonly="true" />
	                   <span id="organizationName_error" class="org_error"></span>
	                   </div>
	               </div>
	                <div class="form-group">
	                  <label class="col-sm-2 control-label"> Timecard Period  <span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                   <select  class="choose form-control" id="timecardPeriod" name="timecardPeriod" >	                    
	                    	<option value="">--Select--</option>
	                    		<option value="7 Days">7 Days</option>
	                    		<option value="14 Days">14 Days</option>
	                    		<option value="15 Days">15 Days</option>     
	                    		<option value="end Of Month">End Of Month</option>     
	                    		<option value="monthly">Monthly</option> 
	                   </select>
	                   </div><span class="org_error" id="timecardperiod_error"></span>
	                 </div>
					
					
					<div class="form-group">
	                  <label class="col-sm-2 control-label">Week Ending Day<span class="asterisk">*</span></label>
	                  <div class="col-sm-4 ">
	                    <select   class="choose form-control" id="weekEndingDay" name="weekEndingDay" >	                    
	                    	<option value="">--Select--</option>
	                    		<option value="monday">Monday</option>
	                    		<option value="tuesday">Tuesday</option>
	                    		<option value="wednesday">Wednesday</option>     
	                    		<option value="thursday">Thursday</option>     
	                    		<option value="friday">Friday</option> 
	                    		<option value="saturday">Saturday</option>
	                    		<option value="sunday">Sunday<option>
	                    </select>
	                 	</div><span class="org_error" id="weekEndingDay_error"></span>
	                </div>
	                
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">Min Hours Per Week<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <input type="text"  placeholder="Type your Min Hours..." class="form-control"  id="minHoursPerWeek" name="minHoursPerWeek"/>
	                 	</div> <span class="org_error" id="minHoursPerWeek_error"></span>
	                </div>
				
					 <div class="form-group">
	                  <label class="col-sm-2 control-label">Max Hours Per Week<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                    <input type="text"  placeholder="Type your Max Hours..." class="form-control"  id="maxHoursPerWeek" name="maxHoursPerWeek"/>
	                    </div><span class="org_error" id="maxHoursPerWeek_error"></span>
	                </div>
					
					 <div class="form-group">
	                  <label class="col-sm-2 control-label">Whine List<span class="asterisk">*</span></label>
	                  <div class="col-sm-4">
	                 <select   class="choose form-control" id="whineList" name="whineList" >	                    
	                    	<option value="">--Select--</option>
	                    		<option value="every Hour">Every Hour</option>
	                    		<option value="6 Hours">6 Hours</option>
	                    		<option value="12 Hours">12 Hours</option>     
	                    		<option value="24 Hours">24 Hours</option>     
	                    		<option value="48 Hours">48 Hours</option> 
	                   </select>
	                 	</div><span class="org_error" id="whineList_error"></span>
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
	    
	      
       </form>          
             
     </div>    </div>
      
     
      
      
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	  
	</section>



</body>


</html>
