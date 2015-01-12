 <div class="leftpanel">     
    <div class="logopanel">
        <h1> KNS TECHNOLOGIES </h1>
    </div><!-- logopanel -->
        
    <div class="leftpanelinner"> 
      
      <h5 class="sidebartitle">Navigation</h5>
      <ul class="nav nav-pills nav-stacked nav-bracket">
        <li class="active" id="dashboard"><a href="dashbord.html"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
        
        <li class="nav-parent" id="organization" ><a href=""><i class="fa fa-edit"></i> <span>Organization</span></a>
          <ul class="children">
            <li><a href="${pageContext.request.contextPath}/organization/edit.htm?userId=${sessionScope.userId}"><i class="fa fa-caret-right"></i> Edit </a></li>
            <li><a href="${pageContext.request.contextPath}/organization/configuration.htm?userId=${sessionScope.userId}"><i class="fa fa-caret-right"></i> Configuration</a></li>
            
          </ul>
       <%--  </li>
        <li class="nav-parent" id="Manager"><a href=""><i class="fa fa-edit"></i> <span> Organization Manager</span></a>
        <ul class="children">
            <li><a href="${pageContext.request.contextPath}/employee/manager/add.htm"><i class="fa fa-caret-right"></i> Add Organization Manager </a></li>
            <!-- <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li> -->
          </ul>
         	
         </li>
         --%>
         <li  id="Employee"><a href="${pageContext.request.contextPath}/employee/viewemployees.htm"><i class="fa fa-edit"></i> <span>Employees</span></a>
         	<%-- <ul class="children">
            <li><a href="${pageContext.request.contextPath}/organization/addemployee.htm"><i class="fa fa-caret-right"></i> Add Employee</a></li>
            <li><a href="${pageContext.request.contextPath}/organization/viewemployees.htm"><i class="fa fa-caret-right"></i> View</a></li>
            <li><a href="${pageContext.request.contextPath}/export/uploademployees.htm"><i class="fa fa-caret-right"></i>Upload Employee</a></li>
          </ul> --%>
         </li>
         
        <li class="nav-parent" id="project"><a href=""><i class="fa fa-edit"></i> <span>Project</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> Add </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
             <li><a href="${pageContext.request.contextPath}/export/uploadprojects.htm"><i class="fa fa-caret-right"></i>Upload Project</a></li>
          </ul>
        </li>
        
        <li class="nav-parent" id="tasks"><a href=""><i class="fa fa-edit"></i> <span>Tasks</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> Add </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
            <li><a href="${pageContext.request.contextPath}/export/uploadtasks.htm"><i class="fa fa-caret-right"></i>Upload Task</a></li>
          </ul>
        </li>
        
        <li class="nav-parent" id="budget"><a href=""><i class="fa fa-edit"></i> <span>Budget</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> Add </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
          </ul>
        </li>
        
        <li class="nav-parent" id="timecard"><a href=""><i class="fa fa-edit"></i> <span>Timecard</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> Add </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
          </ul>
        </li>
        
        
        <li class="nav-parent" id="vacation"><a href=""><i class="fa fa-edit"></i> <span>Vacation</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> Add </a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> View</a></li>
          </ul>
        </li>
        <li  id="divisions"><a href="${pageContext.request.contextPath}/division/viewdivisions.htm"><i class="fa fa-edit"></i> <span>Divisions</span></a>
          <%-- <ul class="children">
            <li><a href="${pageContext.request.contextPath}/organization/departments/adddepartment.htm"><i class="fa fa-caret-right"></i> Add Department </a></li>
            <li><a href="${pageContext.request.contextPath}/organization/departments/viewdepartments.htm"><i class="fa fa-caret-right"></i> View Departments</a></li>
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
        
        <li class="nav-parent" id="userprofile"><a href=""> <i class="fa fa-edit"></i><span>User Profile</span></a>
        	<ul class="children">
        		<li><a href="${pageContext.request.contextPath}/edituserprofile.htm"><i class="fa fa-caret-right"></i> Edit UserProfile</a></li>
        	</ul>
       </li>
             
      </ul>
    </div><!-- leftpanelinner -->
  </div><!-- leftpanel -->