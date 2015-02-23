	


<<<<<<< HEAD






  function sortResults(element){
=======
	function sortResults(element){
>>>>>>> 18d3f8b44ab48797c6464cd4ad9d6403ee6fce96
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
<<<<<<< HEAD
		
		
=======


>>>>>>> 18d3f8b44ab48797c6464cd4ad9d6403ee6fce96
		jQuery("input[name='sortBy']").val(element).closest('form').submit();
		}


	
	function searchResults(){
		var searchText=jQuery('#searchText').val();
		jQuery("input[name='pagerDto.pageNo']").val(0);
		jQuery("input[name='searchBy']").val(searchText).closest('form').submit();
	}
	
	
