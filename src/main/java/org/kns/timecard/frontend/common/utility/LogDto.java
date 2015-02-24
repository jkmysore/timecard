package org.kns.timecard.frontend.common.utility;


/**
 * Created by Jeevan on Jan 23, 2014
 * Class to Hold Organization Log levels..
 * 
 * @author KNS-ACCONTS
 *
 */

public class LogDto {
	
	private Boolean saveOrganizationLog;
	private Boolean saveOrganizationUserLog;
	
	
	public Boolean getSaveOrganizationLog() {
		return saveOrganizationLog;
	}
	public void setSaveOrganizationLog(Boolean saveOrganizationLog) {
		this.saveOrganizationLog = saveOrganizationLog;
	}
	public Boolean getSaveOrganizationUserLog() {
		return saveOrganizationUserLog;
	}
	public void setSaveOrganizationUserLog(Boolean saveOrganizationUserLog) {
		this.saveOrganizationUserLog = saveOrganizationUserLog;
	}
	
	
}
