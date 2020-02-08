package com.qa.utils;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.xml.DOMConfigurator;

public class FreeCRMLogger {
	private static FreeCRMLogger freeCRMlogger;
	public static Logger log = Logger.getLogger(FreeCRMLogger.class);
	
	  /** Creates a new instance of AsyncLogging */
	  public FreeCRMLogger() {
		  
		  URL url = Loader.getResource("./log4j.xml");		  
		  DOMConfigurator.configure("./log4j.xml");
		  
		  
	  }
	  	
	public static void writeToLog(String message)
	  {
		
		if(null==freeCRMlogger)
		{
			freeCRMlogger=new FreeCRMLogger();
		}
		
		  PropertyFileReader cfg = new PropertyFileReader();
		  String LOG_TYPE=cfg.getValue("LOG_TYPE");
		  Date currDt= new Date();
		  if(LOG_TYPE!=null)
		  {
		
			  if(LOG_TYPE.equalsIgnoreCase("file"))
				  log.debug(message);
			  else if(LOG_TYPE.equalsIgnoreCase("console"))				  
				  System.out.println(currDt.getDay()+"  "+message);
		  }

	  }
	  
	public static void writeToLog(String className,String methodName,String message)
	  {
		if(null==freeCRMlogger)
		{
			freeCRMlogger= new FreeCRMLogger();
		}
		  PropertyFileReader cfg = new PropertyFileReader();
		  String LOG_TYPE=cfg.getValue("LOG_TYPE");
		  Date currDt= new Date();
		  if(LOG_TYPE!=null)
		  {
		    if(LOG_TYPE.equalsIgnoreCase("file")){
				  log.debug(currDt.getTime()+" : "+className+" : "+methodName+" : "+message);
		    }
			  else if(LOG_TYPE.equalsIgnoreCase("console")){			  
				  System.out.println(currDt.getDate()+" : "+className+" : "+methodName+" : "+message);
			  }
		  }

	  }



	  public static void main(String args[]) {
		  FreeCRMLogger.writeToLog("Test");
		 // System.out.println("mad");
		 
	  }

	/**
	 * @param exception
	 */
	public static void writeExceptionToLog(Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
