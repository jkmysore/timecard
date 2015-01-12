package org.kns;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.kns.timecard.backend.user.dao.UserDaoImpl;



public class Test {

	private static Logger log=Logger.getLogger(Test.class);
	//private static Logger log2=Logger.getLogger("app");
	
	/*static{
		FileAppender appender=null;
		try {
			appender = new FileAppender(new PatternLayout(), "F://log.txt",true);
			appender.activateOptions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log2.removeAllAppenders();
		log2.addAppender(appender);
		UserDaoImpl dao=new UserDaoImpl();
		dao.test();
	}
	*/
	public static void main(String[] args) {		
		log.info("inside Root log");
		UserDaoImpl dao=new UserDaoImpl();
		
		//log2.info("inisde samml elog");
	}
}
