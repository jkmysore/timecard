<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="en">


<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  
		
			<%@ include file="/jsp/common/common.jsp" %>

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
	<div  style="text-align: center;color:green;font-size: 20px;font-weight: bold; width:850px;margin-left:50px; margin-top:50px;">
	  						 <h2 align="center" style="font-size:20px"> ${message}</h2>
	  						 <div style=" margin-top: 10px; ">
                           			 		<a href="${pageContext.request.contextPath}/division/adddivision.htm" class="btn btn-primary" style="margin-top:20px;margin-left:98px  ">Add Division</a>
                           			 	</div>
	  						</div>
	</div>
	</div>
	</section>
	      				 