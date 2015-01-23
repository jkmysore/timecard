package org.kns.timecard.frontend.utility.dto;


/**
 * Created by Jeevan on Jan 21, 2014
 * Class for Pagination...
 * All Pagination related variables and pagination logic shall be handled in this bean.
 * @author KNS-ACCONTS
 *
 */

public class PagerDto {

	
	private Integer pageNo=0;
	private Integer totalItems;
	private Integer range=03;
	private Integer firstResult;
	private Integer lastResult;
	private Integer pagesNeeded;
	
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	
	
	
	
	
	public Integer getFirstResult(){
		return (pageNo*range)+1;
	}
	
	
	public Integer getPagesNeeded(){
		 pagesNeeded=totalItems/range;
		 if(totalItems%range>0){
			 pagesNeeded+=1;
		 }
		 return pagesNeeded;
	}
	
	
	public Integer getLastResult(){
		lastResult=getFirstResult()+range-1;
		if(lastResult>totalItems){
			lastResult=(totalItems%range)+getFirstResult()-1;
		}
		return lastResult;
	}
	
	
	
	
	
}
