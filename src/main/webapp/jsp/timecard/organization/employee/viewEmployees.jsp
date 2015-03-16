<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
				$('#employeeType').change(function(){	    		 
	    			var employeeType=$("#employeeType :selected").val();	    			
	    			window.location="${pageContext.request.contextPath}/org/viewemployees.htm?employeeType="+employeeType;
				});
		 });
		 </script>	
		 
			<script src="${pageContext.request.contextPath}/js/employee.js"></script>
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
	  
	  
	  <div class="mainpanel" style="height:1063px;">	    
	   
		   <!--  Header Bar -->
		    	<%@ include file="/jsp/home/header.jsp" %>
		    <!--  Header Bar -->
	
    <!--  page Header  --> 
			<div class="pageheader">
			      <h2><i class="fa fa-users"></i> Employees</h2>
			      <div class="breadcrumb-wrapper">
			        <span class="label">You are here:</span>
			        <ol class="breadcrumb">
			          <li><a href="${pageContext.request.contextPath}/home.htm">KNS TECHNOLOGIES</a></li>
			           <li class="active">Employees</li>
			        </ol>
			      </div>
			</div>
	   	<!--  page Header  --> 
	 <div class="contentpanel">
	 		<c:if test="${not empty status}">
				<div class="title">${status}</div> 
	      	</c:if>
	      	 		
                          <div class="panel panel-default">
						           	<div class="panel-heading">
								          <div class="panel-btns">
								            <a href="#" class="panel-close">X</a>
								            <a href="#" class="minimize">-</a>
								          </div><!-- panel-btns -->
								          <h3 class="panel-title">View Employees </h3>          
						        	</div>
						    <div class="panel-body">
						        <div class="col-md-12">
						        		<div class="form-group">
										<div class="row">
								 </div>
							          
		     			 <div class="panel-body table-responsive">
		        
				        		<form:form commandName="displayListBean"
										method="POST"
										class="form-horizontal" 
										action="${pageContext.request.contextPath}/org/viewemployees.htm"
										role="form">
	        			
			        			<form:hidden path="sortBy"/>
			             			<form:hidden path="searchBy"/>
									<form:hidden path="sortDirection" value="${displayListBean.sortDirection }"/>
									<form:hidden path="pagerDto.range"/>
									<form:hidden path="pagerDto.pageNo"/>
	        		
	        					 		<div class="form-group">
				        					<div class="col-sm-12" >
										      	<label class="col-sm-2 control-label" >Select Employees/Managers</label>
									                  <div class="col-sm-2" > 
									                    <select name="employeeType"  class="form-control" id="employeeType" >	                    
									                    	<option value="">--Select--</option>
									                    	<option value="employees" ${employeeType eq 'employees' ? 'selected' : '' }>Employees</option>
									                    	<option value="managers" ${employeeType eq 'managers' ? 'selected' : '' }>Managers</option>
									                    	<option value="allemployees" ${employeeType eq 'allemployees' ? 'selected' : '' }>AllEmployees</option>
									                    </select><span id="employeeType_error" style="color:red"></span>
				                  					</div> 
										   		<div  style="text-align: right;" >
						        					<c:if test="${empty employeeType or employeeType=='allemployees'}">
														<a href="${pageContext.request.contextPath}/org/addemployee.htm?employeeType=${employeeType}"  class="btn btn-info"  >Add Employee</a>
					                    				<a href="${pageContext.request.contextPath}/org/addmanager.htm?employeeType=${employeeType}" class="btn btn-info"  >Add Manager</a>
				                    				</c:if>
				                    				<c:if test="${employeeType=='employees'}">
										             	 <a href="${pageContext.request.contextPath}/org/addemployee.htm?employeeType=${employeeType}" class="btn btn-info"   >Add Employee</a>
										          	</c:if>
				               						<c:if test="${employeeType=='managers'}">
				             		                	<a href="${pageContext.request.contextPath}/org/addmanager.htm?employeeType=${employeeType}" class="btn btn-info" >Add Manager</a>
				                    				</c:if>
								        	</div>
								        </div>
								       	</div>
					       				
					       				  <c:if test="${empty message}"> 
							       		<div class="form-group">
							       			<div class="col-sm-2" > </div>
											<div class="col-sm-2 searchDiv"> 
									   		 	<label class="control-label searchLbl">Search Employee:</label>
									    	</div>
										    <div class="col-sm-4 controls">
										    	<input id="searchText" name="searchText" type="text" value=""  class="searchTxt">
										    	<span class="help-inline">Search for first name,last name,user name,email</span>
										     </div>
											 <div class="col-sm-3" > </div>
											  </div>
					       			
								       			<div class="form-group form-actions">							   
										   			 <div class="col-sm-11" style="text-align: center;">
										      			<button  class="btn btn-danger" type="button" value="Search"  onclick="searchResults();">Search</button>
										     			<button class="btn btn-warning" type="button" onclick="window.location='${pageContext.request.contextPath}/org/viewemployees.htm'">Reset</button>
										   			</div>
										 		</div>
										
										 		
										 				      
			         		 					<table class="table table-striped table-hover  mb30">
			            							<thead>
						                               <tr>
						                             				
						                               			<th>Sno</th>
						                               			<th><a href="#" onclick="sortResults('employeeNo');">Employee Id</a></th>
						                               			<th><a href="#" onclick="sortResults('user.firstName');">First Name</a></th>
						                               			<th><a href="#" onclick="sortResults('user.lastName');">Last Name</a></th>
						                               			<th><a href="#" onclick="sortResults('userCredentials.username');">User Name</a></th>
						                               			<th><a href="#" onclick="sortResults('userCredentials.email');">Email</a></th>
						                               			<th><a href="#" onclick="sortResults('startDate');">Start Date</a></th>
						                               			 <th>Actions</th>
						                               			
						                               			
							                          </tr>
							                        </thead>
							                            
							                        <tbody>
							                       
						                               <c:forEach var="employee" items="${employees}">
							                                <tr>
							                                   	<td>${i}</td>
							                               		<td>${employee.employeeNo}</td>
							                               		<td>${employee.timecardUser.firstName}</td>
							                               		<td>${employee.timecardUser.lastName}</td>
							                               		<td>${employee.timecardUser.timeCardCredentials.username}</td>
							                               		<td>${employee.timecardUser.timeCardCredentials.email}</td>
							                               		<td><fmt:formatDate value="${employee.startDate}" pattern="MM/dd/yyyy"/></td>
							                               		<td class="menu-action"> 
							                               		<c:if test="${employee.isManager==false}">
						              							<a href="${pageContext.request.contextPath}/org/addmanager.htm?employeeType=${employeeType}&employeeNo=${employee.employeeNo}" >Add Manager </a>&nbsp;
						              							</c:if>
						              							<c:if test="${employee.isManager==true}">
						              							<a href="${pageContext.request.contextPath}/org/removemanager.htm?employeeType=${employeeType}&employeeNo=${employee.employeeNo}" >Remove Manager </a> &nbsp;
						              							</c:if>
							                               		<a href="${pageContext.request.contextPath}/org/editemployee.htm?employeeType=${employeeType}&empNo=${employee.employeeNo}" title="Edit Employee">	<i class="fa fa-edit fa-2x"></i> </a>
						              								&nbsp;
						                 						<a href="#" title="Delete Employee">	<i class="fa fa-trash-o fa-2x"></i> </a>
						              								&nbsp;
						              							
							                               		</td>
							                               		
							                               </tr>
						                              		 <c:set var="i" value="${i+1}" /> 
						                               </c:forEach>
						                              
						                            </tbody>
 										</table>
 										</c:if>
 						 		   <c:if test="${not empty filterMsg}"><td style="text-allign:center;">${filterMsg}</td></c:if>
	     			</form:form> 
	     	
					 <c:if test="${empty message && empty filterMsg}">
							  	 <c:set var="first" value="0"/>
					         	 <c:set var="end" value="${displayListBean.pagerDto.pagesNeeded }"/>
							  	 <c:set var="page" value="${displayListBean.pagerDto.pageNo}"/>
						          	<div style="text-align:center;">
										<%@ include file="/jsp/common/pager.jsp" %>
									</div>
					 </c:if>  
			<c:if test="${not empty message}">
							 <div class="col-sm-12" style="text-align: center;">	
							  	 <c:if test="${employeeType !='employees'&& employeeType !='managers'}"> 
					      				 <div class="title">${message}</div>
					   			 </c:if> 
					      	
						      	<c:if test="${employeeType=='employees'}">
			      							<div class="title">Employees Not Found,You Can Add Employees</div>
			  					</c:if>
		  						<c:if test="${employeeType=='managers'}">
		  							<div class="title"> Managers Not Found,You Can Add Managers</div>
		  						</c:if>
		  			</div>
	      	</c:if> 	
	      				
		</div><!-- table-responsive -->
	   </div>
     </div>
    </div>
   </div>
	                    
	    <%--  </c:if>	 --%>
	  
	    
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	</section>



</body>


</html>
