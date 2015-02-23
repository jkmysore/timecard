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
	      <h2><i class="fa fa-users"></i> Organizations</h2>
	      <div class="breadcrumb-wrapper">
	        <span class="label">You are here:</span>
	        <ol class="breadcrumb">
	          <li><a href="${pageContext.request.contextPath}/home.htm">KNS TECHNOLOGIES</a></li>
	           <li class="active">Organizations</li>
	        </ol>
	      </div>
	   </div>
	    	    
		    
		    
		    
	<div class="contentpanel">
    
      <c:if test="${not empty param.message }">
      		<div class="title">
      			
      				Organization Created Successfully
      		</div>      	
      </c:if>
    	
      <div class="panel panel-default">
	        <div class="panel-heading">
		          <div class="panel-btns">
		            <a href="#" class="panel-close">X</a>
		            <a href="#" class="minimize">-</a>
		          </div><!-- panel-btns -->
		          <h3 class="panel-title">View Organizations </h3>          
	        </div>
       	   <div class="panel-body">
	          <div class="col-md-12">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-7" style="text-align: center;">
							<span class="errorMsg">${param.status }</span>
							<c:if test="${not empty message }">
								<span class="errorMsg">No Organizations Found </span>
							</c:if>
						</div>
						<div class="col-sm-4" style="float:right;">
			            	<a href="${pageContext.request.contextPath}/admin/addorganization.htm" style="float:right;" class="btn btn-info">Add Organization </a>		            
			            </div>
			         </div>
		           <!--  <div style="float:left;color:#EF851E;">
		            
		            </div> -->
		            
		         </div>
			<%--   <c:if test="${empty message }"> --%>
	             <div class="panel-body table-responsive">
	             		<form:form
	             			commandName="displayListBean"
							method="POST"
							action="${pageContext.request.contextPath }/admin/organizations.htm"
							class="form-horizontal" 
							role="form">
								             
	             			<form:hidden path="sortBy"/>
	             			<form:hidden path="searchBy"/>
							<form:hidden path="sortDirection" value="${displayListBean.sortDirection }"/>
							<form:hidden path="pagerDto.range"/>
							<form:hidden path="pagerDto.pageNo"/>
							
							<div class="form-group">
								<div class="col-sm-2" > </div>
								<div class="col-sm-2 searchDiv"> 
							   		 <label class="control-label searchLbl">Search Organization:</label>
							   </div>
							    <div class="col-sm-4 controls">
							    	<input id="searchText" name="searchText" type="text" value=""  class="searchTxt">
							     </div>
							     <div class="col-sm-3" > </div>
							 </div>
							 
							 
							<div class="form-group form-actions">							   
							    <div class="col-sm-11" style="text-align: center;">
							      	<button  class="btn btn-danger" type="button" value="Search"  onclick="searchResults();">Search</button>
							     	<button class="btn btn-warning" type="button" onclick="window.location='${pageContext.request.contextPath}/admin/organizations.htm'">Reset</button>
							   	</div>
							 </div>		                    
									                    
						
	             			
					          <table class="table table-striped table-hover  mb30">
					            <thead>
						              <tr>
							               <th>S.No</th>
							               <th> <a href="#" onclick="sortResults('organizationName');" >Organization Name </a></th>
							               <th><a href="#" onclick="sortResults('organizationShortName');" >Short Name</a></th>
							               <th>Site Admin</th>
							               <th><a href="#" onclick="sortResults('isActive');" >Status</a></th>
							               <th>Logo</th>
							               <th>Actions</th>								
						              </tr>
						          </thead>
						          <c:set var="i" value="1" />
						          
					              <tbody>
					              	<c:choose>
					              		<c:when test="${empty message }">
						              			<c:forEach var="org" items="${organizations }">
						              			<tr>
						              				<td>${i}</td>
						              				<td>${org.organizationName}</td>
						              				<td>${org.organizationShortName }</td>
						              				<td>${org.siteAdmin.timeCardCredentials.username }</td>
						              				<td>
						              					<c:choose>
						              						<c:when test="${org.isActive eq true }">
						              							Active
						              						</c:when>
						              						<c:otherwise>
						              							In Active
						              						</c:otherwise>						              					
						              					</c:choose>
						              				</td>
						              				<td> <img src="/timecard_images/org/${org.organizationId}/${org.logoPath}" width="28" height="28"/></td>
						              				
						              				<td class="menu-action">
						              					
														<a href="${pageContext.request.contextPath}/admin/orgsettings.htm?organizationId=${org.organizationId}" title="Settings">	<i class="fa fa-wrench fa-2x"></i> </a>
						              					&nbsp;
						              					
						              					<a href="${pageContext.request.contextPath}/admin/editorganization.htm?organizationId=${org.organizationId}" title="Edit Organization">	<i class="fa fa-edit fa-2x"></i> </a>
						              						&nbsp;
						              					<c:choose>
						              						<c:when test="${org.isActive eq true }">	
						              							<a href="${pageContext.request.contextPath}/admin/changeorganization.htm?organizationId=${org.organizationId}&activate=false" title="Activate Organization">	<i class="fa fa-ban fa-2x"></i> </a>	
						              						</c:when>
						              						<c:otherwise>
						              							<a href="${pageContext.request.contextPath}/admin/changeorganization.htm?organizationId=${org.organizationId}&activate=true" title="InActivate Organization">	<i class="fa fa-check fa-2x"></i> </a>	
						              					    </c:otherwise>
						              					  </c:choose>
						              				</td>
						              				
						              			</tr>		     
						              			<c:set var="i" value="${i+1}"/>         		
						              		</c:forEach>					              		
					              		</c:when>
					              		<c:otherwise>
					              			<tr>
					              				<td colspan="6" style="text-align: center;"> No Organizations Found </td>
					              			</tr>
					              		
					              		</c:otherwise>
					              	</c:choose>
					              		
					              		
					             </tbody>
					          </table>
					         
			          </form:form>
			          
			         
					  <c:if test="${empty message }">
					  	 <c:set var="first" value="0"/>
			          <c:set var="end" value="${displayListBean.pagerDto.pagesNeeded }"/>
					  <c:set var="page" value="${displayListBean.pagerDto.pageNo}"/>
			          	<div style="text-align:center;">
							<%@ include file="/jsp/common/pager.jsp" %>
						</div>
			         </c:if>
	        	  </div><!-- table-responsive -->
			 <%--   </c:if> --%>
			  
					  <div class="form-group">
					  <div class="col-sm-12">
					  <button class="btn btn-primary">Submit</button>
	                    <button type="reset" class="btn btn-lightblue">Save</button>
						
						</div>
	                </div>	
	        </div>
          
        </div>	
		
      </div>
      
      
      
      
      
      
    </div><!-- contentpanel -->
		    
	</div><!-- mainpanel -->
	  
	
	  
	</section>



</body>
	
	
	<script>
	
	
	
	
	</script>

</html>
