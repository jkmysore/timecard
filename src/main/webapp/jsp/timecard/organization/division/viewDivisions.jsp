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
			
		 });
				
		 </script>
		 
		 <script src="${pageContext.request.contextPath}/js/division.js"></script>
	 
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
			      <h2><i class="fa fa-users"></i>Divisions</h2>
			      <div class="breadcrumb-wrapper">
			        <span class="label">You are here:</span>
			        <ol class="breadcrumb">
			          <li><a href="${pageContext.request.contextPath}/home.htm">KNS TECHNOLOGIES</a></li>
			           <li class="active">Divisions</li>
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
								          <h3 class="panel-title">View Divisions </h3>      
								            
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
										action="${pageContext.request.contextPath}/org/viewdivisions.htm"
										role="form">
	        			
			        			<form:hidden path="sortBy"/>
			             			<form:hidden path="searchBy"/>
									<form:hidden path="sortDirection" value="${displayListBean.sortDirection }"/>
									<form:hidden path="pagerDto.range"/>
									<form:hidden path="pagerDto.pageNo"/>
	        		
	        					
				        				<div class="form-group">
											<div class="col-sm-4" style="float: right">
								        		<a href="${pageContext.request.contextPath}/org/adddivision.htm" style="float:right;" class="btn btn-info ">Add Division</a>
								        	</div>
								       	</div>
					       			  <c:if test="${empty msg}">  
							       		<div class="form-group">
										<div class="col-sm-2" > </div>
										<div class="col-sm-2 searchDiv"> 
									   		 <label class="control-label searchLbl">Search Division:</label>
									    </div>
									    <div class="col-sm-4 controls">
									    	<input id="searchText" name="searchText" type="text" value=""  class="searchTxt">
									    	<span class="help-inline">Search for division name</span>
									     </div>
									     <div class="col-sm-3" > </div>
									  </div>
					       	
					       			<div class="form-group form-actions">							   
							   			 <div class="col-sm-11" style="text-align: center;">
							      			<button  class="btn btn-danger" type="button" value="Search"  onclick="searchResults();">Search</button>
							     			<button class="btn btn-warning" type="button" onclick="window.location='${pageContext.request.contextPath}/org/viewdivisions.htm'">Reset</button>
							   			</div>
							 		</div>	
					      
			         		 			<table class="table table-striped table-hover  mb30">
			            							<thead>
						                               <tr>
						                             			<th>Sno</th>
						                               			<th><a href="#" onclick="sortResults('divisionId');">Division Id</a></th>
						                               			<th><a href="#" onclick="sortResults('divisionName');">Division Name</a></th>
						                          	            <th>Actions</th>
						                               			
						                               			
							                          </tr>
							                        </thead>
							                            
							                        <tbody>
							                      
						                               <c:forEach var="division" items="${divisions}">
							                                <tr>
							                                   	<td>${i}</td>
							                               		<td>${division.divisionNo}</td>
							                               		<td>${division.divisionName}</td>
							                               		<td class="menu-action"> 
							                               		
							                               		<a href="${pageContext.request.contextPath}/org/editdivision.htm?divisionId=${division.divisionId}" title="Edit Division">	<i class="fa fa-edit fa-2x"></i> </a>
						              								&nbsp;
						              							
						              							<a href="#" title="Delete Division">	<i class="fa fa-trash-o fa-2x"></i> </a>
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
			 <c:if test="${empty msg && empty filterMsg}">
				 <c:set var="first" value="0"/>
			     <c:set var="end" value="${displayListBean.pagerDto.pagesNeeded }"/>
				 <c:set var="page" value="${displayListBean.pagerDto.pageNo}"/>
				  <div style="text-align:center;">
				       <%@ include file="/jsp/common/pager.jsp" %>
				  </div>
			 </c:if>      
			
	      	<c:if test="${not empty msg}">
	      		<div class="title">${msg}</div><br><br> 
	      	</c:if>
	      				         
						</div><!-- table-responsive -->
	         
     				</div>
     			</div>
     		</div>
     	</div>
   
    </div><!-- contentpanel --> 
	
	</div><!-- mainpanel -->
	  
	
	</section>



</body>


</html>
