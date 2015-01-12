/**
	Author: Bhagya
	Created on : October 22, 2014
	
	Javascript file to handle or perfom validations related to Division

*/

		/*Added by bhagya on october 22nd,2014
		 *This function for validation the addDivision page and editpage
		 */
		
		function validateDivision(){
			var orgName=$('#organizationName').val();
			var deptId=$('#departmentId').val();
			var deptName=$('#departmentName').val();

			var deptIdFilter=/^[0-9a-zA-Z]+$/;
			var nameFilter=/^[0-9a-zA-Z. ]+$/;
			if(orgName=='--Select--'||orgName==''|| orgName==null){
				$('#organizationName_error').html('*Organization Name Should be select');
				a=false;
			}
			else{
				$('#organizationName_error').html('');
				a=true;
			}
			if(deptId==""|| deptId==null){
				$('#departmentId_error').html('*Division Id Cannot Be Empty');
				b=false;
			}
			else if(deptIdFilter.test(deptId)==false){
				$('#departmentId_error').html('*Special Characters Should Not Allowed');
				b=false;
			}
			else{
				$('#departmentId_error').html('');
				b=true;
			}
			if(deptName==""|| deptName==null){
				$('#departmentName_error').html('*Division Name Cannot Be Empty');
				c=false;
			}
			else if(nameFilter.test(deptName)==false){
				$('#departmentName_error').html('*Special Characters Should Not Allowed');
				c=false;
			}
			else{
				$('#departmentName_error').html('');
				c=true;
			}
			if(a==true && b==true && c==true ){
				return true;
			}
			else{
				return false
			}
		}