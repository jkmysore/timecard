<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>KNS TECHNOLOGIES Application - Dashboard page</title>

			<%@ include file="/jsp/common/common.jsp" %>
	
 		 <link href="css/jquery.datatables.css" rel="stylesheet">		
		<script src="js/jquery.sparkline.min.js"></script>
		<script src="js/toggles.min.js"></script>
		<script src="js/retina.min.js"></script>
		<script src="js/jquery.cookies.js"></script>		
		<script src="js/flot.min.js"></script>
		<script src="js/flot.resize.min.js"></script>
		<script src="js/morris.min.js"></script>
		<script src="js/raphael-2.1.0.min.js"></script>		
		<script src="js/jquery.datatables.min.js"></script>
		<script src="js/chosen.jquery.min.js"></script>	
		<script src="js/dashboard.js"></script>
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
	    
	    <div class="pageheader">
	      <h2><i class="fa fa-home"></i> Dashboard <span>Subtitle goes here...</span></h2>
	      <div class="breadcrumb-wrapper">
	        <span class="label">You are here:</span>
	        <ol class="breadcrumb">
	          <li><a href="index.html">KNS TECHNOLOGIES</a></li>
	          <li class="active">Dashboard</li>
	        </ol>
	      </div>
	    </div>
	    
	    
	    
	   
	  </div><!-- mainpanel -->
	  
	
	  
	</section>



</body>


</html>
