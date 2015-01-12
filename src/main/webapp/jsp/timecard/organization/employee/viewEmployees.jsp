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
<style>
	
		td,th{
							text-align: left;
							text-transform: capitalize;
						}
						
						th, th a{
							font-size: 14px !important;
							background: #464747 !important;
							color:#fff;
							padding-top:5px;
							padding-bottom: 5px;
							padding: 5px;
						}
						td{
							padding: 10px !important;
						}
						
		
	#loader {
		        width: 220px;
		        height: 80px;
		        position: fixed;
		        top: 50%;
		        left: 50%;
		        z-index: -1;
		        opacity: 0;
		       transition: all .5s ease-in-out;
		        margin: -40px 0 0 -110px;
		    }
		
		    #loader img {position: relative;  margin-top: -30px; left: 10px;}
		
		    .loading #loader {z-index: 1000; opacity: 1.0}
		    
		}
	</style>			
			
		<script>
		 $(document).ready(function(){
				$('li').removeClass('active');
				$('#organization1').addClass('active');
				 
		 });
		 </script>	
		  <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">		
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
	<div class="contentpanel">
    
      <div class="row">
        
        <div class="col-md-11">
          <form class="form-horizontal" action="${pageContext.request.contextPath}/employee/viewemployees.htm" method="get"  >
	          
	          
	          <div class="panel panel-default">
	          
	          
	          <div class="titleHeader">
	               Employees 
	              	</div>
	              		<c:if test="${not empty employeeMessage}">
	      				<div  style="text-align: center;color:green;font-size:05px;font-weight: bold; width:850px;margin-left:50px; margin-top:20px;">
	  						 <h2 align="center" style="font-size:20px; " > ${employeeMessage}</h2>
	  						 </div>
	      				</c:if>
	      				<c:if test="${not empty managerMessage}">
	      				<div  style="text-align: center;color:green;font-size:05px;font-weight: bold; width:850px;margin-left:50px; margin-top:20px;">
	  						 <h2 align="center"  style="font-size:20px; "> ${managerMessage}</h2>
	  						 </div>
	      				</c:if>
	              	<div class="panel-body">
	              
	               <div class="form-group" style="margin-left: -79px;margin-right: -70px;">
	                  <label class="col-sm-2 control-label" >Select Organization<span class="asterisk">*</span></label>
	                  <div class="col-sm-4" style="width:250px;">
	                    <select name="organizationId" required="required" class="form-control" id="organizationId" >	                    
	                    	<option value="">--Select--</option>
	                    	<c:forEach var="organization" items="${organizations}">
	                    		<option value="${organization.organizationId}" ${organization.organizationName eq organization.organizationName ? 'selected' : '' }>${organization.organizationName}</option>
	                    	</c:forEach>	                    
	                    </select><span id="organizationName_error" style="color:red"></span>
	                     </div>
	                     <label class="col-sm-2 control-label" >Select Employees/Managers<span class="asterisk">*</span></label>
	                  <div class="col-sm-4" style="width:250px;">
	                    <select name="employeeType" required="required" class="form-control" id="employeeType" >	                    
	                    	<option value="">--Select--</option>
	                    	<option value="employees" ${employeeType eq 'employees' ? 'selected' : '' }>Employees</option>
	                    	<option value="managers" ${employeeType eq 'managers' ? 'selected' : '' }>Managers</option>
	                    	<option value="allemployees" ${employeeType eq 'allemployees' ? 'selected' : '' }>AllEmployees</option>
	                    	<%-- <option value="managers&employees" ${employeeType eq 'managers&employees' ? 'selected' : '' }>Employees&amp;Managers</option> --%>
	                    	                    		                    
	                    </select><span id="employeeType_error" style="color:red"></span>
	                     </div>
	                 </div>
					
	           </div><!-- panel-body -->
	           
	           
	           <div class="panel-footer">
		            <div class="row">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <button class="btn btn-primary" style="margin-left:270px  ">View</button>
		                    </div>
		            </div>
	          </div>
	                      
	      </div><!-- panel -->
	      <div>
	      
	      			<c:if test="${not empty message}">
	      				<div  style="text-align: center;color:green;font-size:10px;font-weight: bold; width:850px;margin-left:50px; margin-top:50px;">
	  						 <h2 align="center" style="font-size:20px"> ${message}</h2>
	  						 <div style=" margin-top: 10px; ">
                           		<a href="${pageContext.request.contextPath}/employee/addemployee.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:98px  ">Add Employee</a>
                           		<a href="${pageContext.request.contextPath}/employee/manager/add.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:98px  ">Add Manager</a>
                           	</div>
	  					</div>
	      			</c:if>
	      				<c:if test="${employeeType=='employees'}">
	      						<c:if test="${empty employees}">
	      							<div  style="text-align: center;color:green;font-size:10px;font-weight: bold; width:850px;margin-left:50px; margin-top:50px;">
	  						 			<h2 align="center" style="font-size:20px">Employees Not Found,You Can Add Employees</h2>
	  						 			<div style=" margin-top: 10px; ">
                           			 		<a href="${pageContext.request.contextPath}/employee/addemployee.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:98px  ">Add Employee</a>
                           			 	</div>
	  							</div>
	  						</c:if>
	  					</c:if>
	  						
	  						<c:if test="${employeeType=='managers'}">
	  							<c:if test="${empty employees}">
	  								<div  style="text-align: center;color:green;font-size:10px;font-weight: bold; width:850px;margin-left:50px; margin-top:50px;">
	  						 			<h2 align="center" style="font-size:20px"> Managers Not Found,You Can Add Managers</h2>
	  									 <div style=" margin-top: 10px; ">
                           			 		<a href="${pageContext.request.contextPath}/employee/manager/add.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:98px  ">Add Manager</a>
                           			 	</div>
	  								</div>
	  						</c:if>
	      				</c:if>
	      				
  						<c:if test="${not empty employees}">
                           <div style="float: right;font-weight: bold;font-size: 14px;">												    
								Showing ${first } to ${last } of ${total }													
							</div>
					                              	
	              	 <div style="margin:  0 auto;width: 975px;">
					                                <input type="hidden" name=employeeId value="${employeeId}">
					                                
						                              <table style="table-layout:fixed;width:100%;word-wrap:break-word;margin-top: 10px;display: inline-table;
						                               border: 1px solid #ccc;border-radius: 5px 5px 5px 5px;color:#636e7b;background: white;">
						                               
						                               <tr>
						                               			<th>SNO</th>
						                               			<th>Employee Id</th>
						                               			<th>First Name</th>
						                               			<th>Last Name</th>
						                               			<th>Username</th>
						                               			<th>Email</th>
						                               			<th>StartDate</th>
						                               			<th>Manager</th>	
						                               			<th>Edit</th>
						                               			<%-- <c:if test="${empty employeeType or employeeType=='allemployees'}">
						                               				<th>Manager</th>
						                               				<th>Edit</th>
						                               			</c:if>
						                               			<c:if test="${employeeType=='employees'}">	
						                               				<th>Manager</th>
						                               				<th>Edit</th>
						                               			</c:if>
						                               			<c:if test="${employeeType=='managers'}">
						                               				<th>Manager</th>	
						                               				<th>Edit</th>
						                               			</c:if> --%>
						                               			
							                          </tr>
						                               <c:forEach var="employee" items="${employees}">
							                                <tr>
							                                  	<td>${i}</td>
							                               		<td>${employee.employeeNo}</td>
							                               		<td>${employee.timecardUser.firstName}</td>
							                               		<td>${employee.timecardUser.lastName}</td>
							                               		<td>${employee.timecardUser.timeCardCredentials.username}</td>
							                               		<td>${employee.timecardUser.timeCardCredentials.email}</td>
							                               		<td><fmt:formatDate value="${employee.startDate}" pattern="MM/dd/yyyy"/></td>
							                               		
							                               		<c:if test="${employee.isManager==false}">	
							                               			<c:set var="employeeId" value="${employee.employeeId}"></c:set>
							                               	    <td style="vertical-align: middle;">
							                               	    	 <a href="${pageContext.request.contextPath}/employee/manager/add.htm?employeeType=${employeeType}&employeeNo=${employee.employeeNo}" >Add Manager </a>
							                               	    </td>
							                               	    <td style="vertical-align: middle;">
							                               	    	 <a href="${pageContext.request.contextPath}/employee/editemployee.htm?employeeType=${employeeType}&empNo=${employee.employeeNo}" ><img src="${pageContext.request.contextPath}/images/edit.png" height="24px;" width="24px;" /> </a>
							                               	    </td>
							                               	     </c:if>
							                               	    
							                               	    
							                               	    <c:if test="${employee.isManager==true}">
							                               	    <td style="vertical-align: middle;"> 
							                               	    	<a href="${pageContext.request.contextPath}/employee/manager/remove.htm?employeeType=${employeeType}&employeeNo=${employee.employeeNo}" >Remove Manager </a> 
							                               	   </td>	
							                               	    <td style="vertical-align: middle;">
							                               	    	  <a href="${pageContext.request.contextPath}/employee/editemployee.htm?employeeType=${employeeType}&empNo=${employee.employeeNo}" ><img src="${pageContext.request.contextPath}/images/edit.png" height="24px;" width="24px;" /> </a> 
							                               	   </td>
							                               	    
							                               	   </c:if>
							                               	   
							                               	 
							                               </tr>
						                               <c:set var="i" value="${i+1}" /> 
						                               </c:forEach>
						                              
 													</table>
						                      
			</div>
		
	           
	           
	           <div class="panel-footer">
		            <div class="row">
		            
		            <c:if test="${empty employeeType or employeeType=='allemployees'}">
		             	<div class="col-sm-9 col-sm-offset-3">
		             	<a href="${pageContext.request.contextPath}/employee/addemployee.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:232px  ">Add Employee</a>
		                    <a href="${pageContext.request.contextPath}/employee/manager/add.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:29px  ">Add Manager</a>
		                    </div>
		                    </c:if>
		            <c:if test="${employeeType=='employees'}">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <a href="${pageContext.request.contextPath}/employee/addemployee.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:232px  ">Add Employee</a>
		                    </div>
		                    </c:if>
		                    <c:if test="${employeeType=='managers'}">
		             	<div class="col-sm-9 col-sm-offset-3">
		                    <a href="${pageContext.request.contextPath}/employee/manager/add.htm?employeeType=${employeeType}" class="btn btn-primary" style="margin-top:20px;margin-left:232px  ">Add Manager</a>
		                    </div>
		                    </c:if>
		            </div>
		            <!--  Pagination -->  
									 <div class="pagination" style="clear:both;padding-top:40px;">								
											<ul class="pagin" style="margin-left: 330px;">
												<c:if test="${page ne 0 }">
													<li><a  href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&organizationId=${organizationId}&employeeType=${employeeType}&page=0">First</a></li>
													<li>  <a href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${page-1}&organizationId=${organizationId}&employeeType=${employeeType}">Prev</a></li>
												</c:if>
																
												<c:choose>
														<c:when test="${end eq 1 }"></c:when>
																<c:when test="${end lt 10 }">
																	<c:forEach var="i" begin="0" end="${end-1 }">
																			<c:choose>
																				<c:when test="${page eq i }">
																					<li><a class="current" href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																				</c:when>
																				<c:otherwise>
																					<li><a  href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																				</c:otherwise>
																			</c:choose>																	
																	</c:forEach>																
																</c:when>
																			
																<c:when test="${ page  lt 5}">
																	<c:forEach var="i" begin="0" end="9">
																		<c:choose>
																			<c:when test="${page eq i }">
																				<li><a class="current" href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a  href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																			</c:otherwise>
																		</c:choose>																	
																	</c:forEach>																
																</c:when>
																			
																			
																<c:when test="${page ge 5 && page+5 le end }">
																	<c:forEach var="i" begin="${page-5 }" end="${page+4 }">
																		<c:choose>
																			<c:when test="${page eq i }">
																				<li><a class="current" href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a  href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																			</c:otherwise>
																		</c:choose>																	
																	</c:forEach>												
																</c:when>
																			
																 <c:otherwise>
																	<c:forEach var="i" begin="${end-10 }" end="${end-1 }">
																		<c:choose>
																			<c:when test="${page eq i }">
																				<li><a class="current" href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a  href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${i}&organizationId=${organizationId}&employeeType=${employeeType}">${i+1 }</a></li>
																			</c:otherwise>
																		</c:choose>																	
																	</c:forEach>															
																</c:otherwise>							 	
											</c:choose>									
															
																  
													<c:if test="${page lt end-1 }">
														<li>  <a href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${page+1}&organizationId=${organizationId}&employeeType=${employeeType}">Next</a></li>
													</c:if>
													<c:if test="${page ne end-1 }">
														<li><a  href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?${query }&page=${end-1}&organizationId=${organizationId}&employeeType=${employeeType}">Last</a></li>
													</c:if>
																</ul>
													</div>
									<!--  PAgination -->	
	          </div>
	                      
	     </c:if>	
	      </div>	
	       </form>          
          
     </div>
    </div>
      
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	</section>



</body>


</html>
