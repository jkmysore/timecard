package org.kns.timecard.common.logging;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 * 
 * Created by Jeevan on June 19, 2014
 * Support Class for Creating Log Files Dynamically
 *
 */

public class LoggingFactory {
	
	public static Logger getCustomLogger(String organization, String employee,Boolean isOrgnaization) throws Exception{
		Logger logger=Logger.getLogger("app");
		FileAppender appender= new FileAppender();
		StringBuilder filePath=new StringBuilder();
		filePath.append("F:/work/timecard/logs/");
		if(isOrgnaization){
			filePath.append(organization+".log");
		}
		else{
			filePath.append(organization+"/"+employee+".log");
		}
		appender.setFile(filePath.toString());
		appender.setLayout(new PatternLayout());
		appender.activateOptions();
		logger.removeAllAppenders();
		logger.addAppender(appender);
		return logger;
	}

}
