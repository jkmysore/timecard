<!DOCTYPE html>
<html>

<head>
	<title>Gracular Home</title>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/gracular/user.js"></script>
	<style>
	 html ul.tabs1 li.active a{
		border-color: blue !important;
	}
	
	</style>
	
	
</head>

<body>
	
				<section id="content">
                	<div class="main">
                    	<div class="bg-1 indent">
                            <div class="container_12">
                                <article class="grid_12">
                                	
                                    <div class="wrap">
                                        <div class="extra-wrap">
                                            <ul class="tabs">
                                                <li><a href="#tab1" class="m1"><span>Patent Search</span></a></li>
                     <!--  Commented as Per Client Request to Show only Search for Current Version  -->    
                                                <!-- <li><a href="#tab2" class="m2"><span>Patent Drafting</span></a></li>
                                                <li><a href="#tab3" class="m3"><span>Patent Prosecution</span></a></li> -->
                                               
                                            </ul>
                                        </div>
                                        <div class="tab_container">
                                            <div id="tab1" class="tab_content">
                                                <div class="extra-wrap">
		                                            <ul class="tabs1">
		                                                <li id="prod" class="active"><a href="#" class="m1" id="product_search" ><span>Product Patent Search</span></a></li>
		                                                <li id="patent"><a href="#" class="m2" id="patent_search"><span>Text Patent Search</span></a></li>
		                                               
		                                            </ul>
                                       			 </div>
                                                <%-- <form id="form-1" method="post" enctype="multipart/form-data"> --%>
                                                    <fieldset style="border:none;">
                                                        <div class="wrap" style="border:none !important;">
                                                          
                                                        	<div class="col-1" id="product_box" >     
                                                        	  <form id="form-1" method="get" action="${pageContext.request.contextPath}/search/product.do">                                                          
                                                                <label class="d-block margin-bot2">
                                                                    <span class="input-1"> <input type="text" name="keyword" placeholder="Enter Your Product Search" id="prod_search"></span> </label>
																	<div class="fleft indent-top1">
	                                                                	 <button class="button-4" class="button-4" name="keyword" type="submit">Search</button>    	  
	                                                                </div>
	                                                            </form>
                                                            </div>
                                                            
                                                            <div class="col-1" id="patent_box" style="display: none;"> 
                                                              <form id="form-1" method="get" action="${pageContext.request.contextPath}/search/patent.do">                                                              
                                                                <label class="d-block margin-bot2">
                                                                    <span class="input-1"> <input type="text" name="keyword" placeholder="Enter Your Patent Search" id="p_search"></span> </label>
																	<div class="fleft indent-top1">
	                                                          			  <button class="button-4" class="button-4" type="submit">Search</button>     
	                                                               </div>
	                                                           </form>
                                                            </div>
                                                            <div style="text-align: right;padding-right: 130px;padding-top: 57px;">
                                                             	<a class="button-4" class="button-4" href="${pageContext.request.contextPath}/search/advanced.do" >Advanced Search</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </div>
                                                    </fieldset>
                                               <%--  </form> --%>
                                            </div>
                                            <div id="tab2" class="tab_content">
											
                                                
                                            </div>
                                            
                                            
                                            <div id="tab3" class="tab_content">
                                                
                                            </div>
                                            
                                            
                                        </div>
									</div>
                                </article>
                                
                                
                                <div class="clear"></div>
                            </div>
                        </div>
                        
                        
                        <div class="bg-2">
                        	<div class="container_12">
                            	<div class="wrapper">
                                	<article class="grid_6">
                                        <div class="indent-top3">
                                           <div class="box">
										  <div style="font-family:Impact; font-size:20px; color:#fff; padding-left:80px; padding-top:18px;">Product Patent Search</div>
										   <p style="padding-top:35px; padding-left:15px;">Search for patents using manufactured products. Lookup patents or reference materials for specific products and/or components.</p>
										    <div style="float:right; padding-right:15px;"><a href="${pageContext.request.contextPath}/plans.do"><img src="${pageContext.request.contextPath}/images/sub.PNG"></a></div>
										   </div>
                                        </div>
                                    </article>
                                	<article class="grid_6">
                                        <div class="indent-top3">
                                           <div class="box1">
										    <div style="font-family:Impact; font-size:20px; color:#fff; padding-left:80px; padding-top:18px;">Text Patent Search</div>
											 <p style="padding-top:35px; padding-left:15px;">Search for patents using keywords</p>
										   <div style="float:right; padding-right:15px;padding-top: 18px;"><a href="#"><img src="${pageContext.request.contextPath}/images/free.PNG"></a></div>
										   </div>
                                            
                                        </div>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
	
	


</body>


</html>