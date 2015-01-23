package org.kns.timecard.frontend.utility.dto;


/***
 * Created by Jeevan on January 21, 2014
 * Class for DisplayingBeansWithPaginationSearchandSort
 * 
 * @author KNS-ACCONTS
 *
 */
public class DisplayListBeanDto {

	private PagerDto pagerDto=new PagerDto();
	private String sortBy="organizationName";
	private String searchBy;
	private Boolean sortDirection=true;
	
	
	
	public Boolean getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(Boolean sortDirection) {
		this.sortDirection = sortDirection;
	}
	public PagerDto getPagerDto() {
		return pagerDto;
	}
	public void setPagetDto(PagerDto pagerDto) {
		this.pagerDto = pagerDto;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
	
	
}
