 <div class="headerbar">	      
	      <a class="menutoggle"><i class="fa fa-bars"></i></a>	      
	     <!--  <form class="searchform" action="http://themepixels.com/demo/webpage/bracket/index.html" method="post">
	        <input type="text" class="form-control" name="keyword" placeholder="Search here..." />
	      </form> -->	
	            
	      <div class="header-right">
	        <ul class="headermenu">          
	          <li>
	            <div class="btn-group">
	              <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:50;height:25">
	                <img src="/profile/${sessionScope.userId}/${sessionScope.profilePic}" alt=""  />
	               ${sessionScope.user_name }
	                <span class="caret"></span>
	              </button>
	              <ul class="dropdown-menu dropdown-menu-usermenu pull-right"><li><a href="${pageContext.request.contextPath}/logout.htm"><i class="fa fa-sign-out"></i> Log Out</a></li>
	              </ul>
	            </div>
	          </li>	          
	        </ul>
	      </div><!-- header-right -->
</div><!-- headerbar -->