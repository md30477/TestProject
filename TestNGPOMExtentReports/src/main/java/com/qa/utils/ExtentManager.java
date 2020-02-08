package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	  private static ExtentReports extent;
	
	    @SuppressWarnings("deprecation")
		public synchronized static ExtentReports getReporter() {
	       if(extent== null) {
	            //Set HTML reporting file location
	          /*  String workingDir = System.getProperty("user.dir");
	            if (System.getProperty("os.name").toLowerCase().contains("win")) {
	                extent = new ExtentReports(workingDir + "\\ExtentReports\\ExtentReportResults.html", true);
	            }
	            else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
	                extent = new ExtentReports(workingDir + "/ExtentReports/ExtentReportResults.html", true);
	            }*/
	        	FreeCRMLogger.writeToLog("ExtentReportManager", "Instance()", " method called");
				  String path = ReportsFolderPath.createScreenShotDir(); 
			    // extent = new ExtentReports(path+"\\"+"AWCTestSuiteReport"+".html", false);
				String  reportName="TestNG TestSuite Results"+" @ "+getTimeStamp();
				extent = new ExtentReports(path+"\\"+reportName+".html", true);
				extent.config().documentTitle("Automation Report").reportName("TestNGTestSuiteResults");
	    }
	      
	        return extent;
	    }
	    
	    public static String CaptureScreen(WebDriver driver, String ImagesPath)
		 {
			// newScrnShotPath = createScreenShotDir(ImagesPath);
			 FreeCRMLogger.writeToLog("ExtentReportManager", "CaptureScreen()", " method called");
		     TakesScreenshot oScn = (TakesScreenshot) driver;
		     File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
			  File oDest = new File(ImagesPath+".png"); //".jpg"); //.jpg commented as it is not displayed in IE
			  try {
			      FileUtils.copyFile(oScnShot, oDest);
			  } catch (IOException e) {
				  System.out.println(e.getMessage());}
			//   ImagesPath = ImagesPath.replace("C:"+ System.getProperty("file.separator")+"Selenium", ".");;
				
			  	return ImagesPath+".png";
			  }
		 
		 public static String CaptureScreenForExcel(WebDriver driver, String sheetname)
		 {
			// newScrnShotPath = createScreenShotDir(ImagesPath);
			 FreeCRMLogger.writeToLog("ExtentReportManager", "CaptureScreen()", " method called");
		     TakesScreenshot oScn = (TakesScreenshot) driver;
		     File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
			  File oDest = new File(sheetname+".xls"); //".jpg"); //.jpg commented as it is not displayed in IE
			  try {
			       FileUtils.copyFile(oScnShot, oDest);
			  } catch (IOException e) {
				  System.out.println(e.getMessage());}
			  	return sheetname+".xls";
			  }
		 
		  public static String getTimeStamp(){
		         Date dateNow = new Date();
			  SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss.SSS");
		    //     SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyy-MMM-dd");	   
		       String timeStamp = new String(dateformatYYYYMMDD.format(dateNow));
		       return timeStamp;
		   }

}
