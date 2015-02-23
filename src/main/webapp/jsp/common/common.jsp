<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	 <link href = "${pageContext.request.contextPath}/images/logo.png" rel="icon" type="image/png" />

	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/modernizr.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/retina.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.sparkline.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/toggles.min.js"></script>


 	<link href="${pageContext.request.contextPath}/css/style.default.css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
  	  	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
  	  	
  	  
  	   
  	   <style>
  	   	
  	   	  .logopanel, .headerbar, .panel-heading, .panel-body, .headermenu .dropdown-toggle{
  	   	  	background : ${sessionScope.theme} !important;
  	   	  }
  	   
  	   
  	   </style>
  	   
  	   
  	   
  	   
  	
 	 <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
 	 <!--[if lt IE 9]>
	  <script src="js/html5shiv.js"></script>
	 <script src="js/respond.min.js"></script>
 	 <![endif]-->