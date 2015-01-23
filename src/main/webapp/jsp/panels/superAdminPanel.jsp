 
 <div class="leftpanel">     
    <div class="logopanel">
        <h1> KNS TECHNOLOGIES </h1>
    </div><!-- logopanel -->
        
    <div class="leftpanelinner"> 
      
      <h5 class="sidebartitle">Navigation</h5>
      <ul class="nav nav-pills nav-stacked nav-bracket">
        <li class="active" id="dashboard"><a href="dashbord.html"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
        
        <li id="organization1" > <a href="${pageContext.request.contextPath}/admin/organizations.htm"><i class="fa fa-users"></i> <span>Organization</span></a>
          <ul class="children">
            <li><a href="${pageContext.request.contextPath}/admin/addorganization.htm"><i class="fa fa-caret-right"></i> Add </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
          </ul>
        </li>
        
        
         <li class="nav-parent " id="settings"><a href=""><i class="fa  fa-cog"></i> <span>Settings</span></a>
          <ul class="children">
            <li><a href="${pageContext.request.contextPath}/su/settings.htm"><i class="fa fa-caret-right"></i> Settings </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
          </ul>
        </li>
        
        
         <li  id="settings"><a href=""><i class="fa fa-edit"></i> <span>Templates</span></a>
         <%--  <ul class="children">
            <li><a href="${pageContext.request.contextPath}/su/settings.htm"><i class="fa fa-caret-right"></i> Settings </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
          </ul> --%>
        </li>
        
        
        
        
        
        <li class="nav-parent" id="reports"><a href=""><i class="fa fa-suitcase"></i> <span>Reports</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> Forms1</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> Forms2</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> Forms3</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> Forms4</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> Forms5</a></li>
          </ul>
        </li>
             
      </ul>
    </div><!-- leftpanelinner -->
  </div><!-- leftpanel -->