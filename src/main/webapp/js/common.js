	


	function sortResults(element){
		if(jQuery("input[name='sortBy']").val()==element){
		if((jQuery("input[name='sortDirection']").val())=="true"){
		jQuery("input[name='sortDirection']").val(false);
		}
		else{
		jQuery("input[name='sortDirection']").val(true);
		}
		}
		else{
		jQuery("input[name='sortDirection']").val(true);
		}
		jQuery("input[name='pagerDto.pageNo']").val(0);


		jQuery("input[name='sortBy']").val(element).closest('form').submit();
		}


	
	function searchResults(){
		var searchText=jQuery('#searchText').val();
		jQuery("input[name='searchBy']").val(searchText).closest('form').submit();
	}
	
	