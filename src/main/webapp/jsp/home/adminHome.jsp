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
	  	<%@ include file="/jsp/panels/superAdminPanel.jsp" %>
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
	    
	    
	    
	    <div class="contentpanel">	      
	      <div class="row">	        
	        <div class="col-sm-6 col-md-3">
	          <div class="panel panel-success panel-stat">
	            <div class="panel-heading">
	              
	              <div class="stat">
	                <div class="row">
	                  <div class="col-xs-4">
	                    <img src="images/is-user.png" alt="" />
	                  </div>
	                  <div class="col-xs-8">
	                    <small class="stat-label">Visits Today</small>
	                    <h1>900k+</h1>
	                  </div>
	                </div><!-- row -->
	                
	                <div class="mb15"></div>
	                
	                <div class="row">
	                  <div class="col-xs-6">
	                    <small class="stat-label">Pages / Visit</small>
	                    <h4>7.80</h4>
	                  </div>
	                  
	                  <div class="col-xs-6">
	                    <small class="stat-label">% New Visits</small>
	                    <h4>76.43%</h4>
	                  </div>
	                </div><!-- row -->
	              </div><!-- stat -->
	              
	            </div><!-- panel-heading -->
	          </div><!-- panel -->
	        </div><!-- col-sm-6 -->
	        
	        <div class="col-sm-6 col-md-3">
	          <div class="panel panel-danger panel-stat">
	            <div class="panel-heading">	              
	              <div class="stat">
	                <div class="row">
	                  <div class="col-xs-4">
	                    <img src="images/is-document.png" alt="" />
	                  </div>
	                  <div class="col-xs-8">
	                    <small class="stat-label">% Unique Visitors</small>
	                    <h1>54.40%</h1>
	                  </div>
	                </div><!-- row -->	                
	                <div class="mb15"></div>	                
	                <small class="stat-label">Avg. Visit Duration</small>
	                <h4>01:80:22</h4>	                  
	              </div><!-- stat -->	              
	            </div><!-- panel-heading -->
	          </div><!-- panel -->
	        </div><!-- col-sm-6 -->
	        
	        
	        <div class="col-sm-6 col-md-3">
	          <div class="panel panel-primary panel-stat">
	            <div class="panel-heading">	              
	              <div class="stat">
	                <div class="row">
	                  <div class="col-xs-4">
	                    <img src="images/is-document.png" alt="" />
	                  </div>
	                  <div class="col-xs-8">
	                    <small class="stat-label">Page Views</small>
	                    <h1>300k+</h1>
	                  </div>
	                </div><!-- row -->	                
	                <div class="mb15"></div>
	                
	                <small class="stat-label">% Bounce Rate</small>
	                <h4>34.23%</h4>
	                  
	              </div><!-- stat -->
	              
	            </div><!-- panel-heading -->
	          </div><!-- panel -->
	        </div><!-- col-sm-6 -->
	        
	        
	        <div class="col-sm-6 col-md-3">
	          <div class="panel panel-dark panel-stat">
	            <div class="panel-heading">	              
	              <div class="stat">
	                <div class="row">
	                  <div class="col-xs-4">
	                    <img src="images/is-money.png" alt="" />
	                  </div>
	                  <div class="col-xs-8">
	                    <small class="stat-label">Today's Earnings</small>
	                    <h1>$655</h1>
	                  </div>
	                </div><!-- row -->
	                <div class="mb15"></div>
	                
	                <div class="row">
	                  <div class="col-xs-6">
	                    <small class="stat-label">Last Week</small>
	                    <h4>$32,322</h4>
	                  </div>
	                  
	                  <div class="col-xs-6">
	                    <small class="stat-label">Last Month</small>
	                    <h4>$503,000</h4>
	                  </div>
	                </div><!-- row -->
	                  
	              </div><!-- stat -->
	              
	            </div><!-- panel-heading -->
	          </div><!-- panel -->
	        </div><!-- col-sm-6 -->
	      </div><!-- row -->
	      
	      <div class="row">
	        <div class="col-sm-8 col-md-9">
	          <div class="panel panel-default">
	            <div class="panel-body">
	              <div class="row">
	                <div class="col-sm-8">
	                  <h5 class="subtitle mb5">Network Performance</h5>
	                  <p class="mb15">Duis autem vel eum iriure dolor in hendrerit in vulputate...</p>
	                  <div id="basicflot" style="width: 100%; height: 300px; margin-bottom: 20px"></div>
	                </div><!-- col-sm-8 -->
	                <div class="col-sm-4">
	                  <h5 class="subtitle mb5">Server Status</h5>
	                  <p class="mb15">Summary of the status of your server.</p>
	                  
	                  <span class="sublabel">CPU Usage (40.05 - 32 cpus)</span>
	                  <div class="progress progress-sm">
	                    <div style="width: 40%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-primary"></div>
	                  </div><!-- progress -->
	                  
	                  <span class="sublabel">Memory Usage (32.2%)</span>
	                  <div class="progress progress-sm">
	                    <div style="width: 32%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
	                  </div><!-- progress -->
	                  
	                  <span class="sublabel">Disk Usage (82.2%)</span>
	                  <div class="progress progress-sm">
	                    <div style="width: 82%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-danger"></div>
	                  </div><!-- progress -->
	                  
	                  <span class="sublabel">Databases (63/100)</span>
	                  <div class="progress progress-sm">
	                    <div style="width: 63%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-warning"></div>
	                  </div><!-- progress -->
	                  
	                  <span class="sublabel">Domains (2/10)</span>
	                  <div class="progress progress-sm">
	                    <div style="width: 20%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
	                  </div><!-- progress -->
	                  
	                  <span class="sublabel">Email Account (13/50)</span>
	                  <div class="progress progress-sm">
	                    <div style="width: 26%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
	                  </div><!-- progress -->
	                  
	                  
	                </div><!-- col-sm-4 -->
	              </div><!-- row -->
	            </div><!-- panel-body -->
	          </div><!-- panel -->
	        </div><!-- col-sm-9 -->
	        
	        <div class="col-sm-4 col-md-3">
	          
	          <div class="panel panel-default">
	            <div class="panel-body">
	            <h5 class="subtitle mb5">Most Browser Used</h5>
	            <p class="mb15">Duis autem vel eum iriure dolor in hendrerit in vulputate...</p>
	            <div id="donut-chart2" style="text-align: center; height: 298px;"></div>
	            </div><!-- panel-body -->
	          </div><!-- panel -->
	          
	        </div><!-- col-sm-3 -->
	        
	      </div><!-- row -->
	      
	      <!-- row -->
	      
	      
	      
	    </div><!-- contentpanel -->
	    
	  </div><!-- mainpanel -->
	  
	
	  
	</section>



</body>


</html>
