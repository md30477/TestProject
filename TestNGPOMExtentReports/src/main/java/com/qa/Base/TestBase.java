package com.qa.Base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.qa.utils.FreeCRMLogger;
import com.qa.utils.PropertyFileReader;
public class TestBase {
	 PropertyFileReader propertyFileReader ;
	public static WebDriver driver;
	public String browserType="";
	FreeCRMLogger log =null;
		// @SuppressWarnings("static-access")

	
	public TestBase() {
		// TODO Auto-generated constructor stub
		 propertyFileReader = new PropertyFileReader();
		 //extentReports = TestBase.Instance();
	}
	
	public WebDriver intialization()
	{
		System.out.println("madviiiiiiiiiiiiiiiii"+propertyFileReader.getValue("BROWSER_NAME"));
		
		String browserType=propertyFileReader.getValue("BROWSER_NAME");
		String downloadFilepath = propertyFileReader.getValue("FILE_DOWNLOAD_LOC");
		if (browserType.toLowerCase().contains("fire")) {
			
			FreeCRMLogger.writeToLog("Before getting firefox driver");
			//DesiredCapabilities dc=DesiredCapabilities.firefox();
		//	String fireFoxBrowser=propertyFileReader.getValue(FirefoxDriver.BINARY);
			
//			if (fireFoxBrowser==null || fireFoxBrowser.trim().equalsIgnoreCase("")){
//				fireFoxBrowser="";
//				//propertyFileReader.setValue(FirefoxDriver.BINARY, fireFoxBrowser);
//			 }else {
//				dc.setCapability(FirefoxDriver.BINARY , fireFoxBrowser);
//			}	
//			
			try {
				
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("browser.download.folderList", 2);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
				firefoxProfile.setPreference("browser.download.dir", downloadFilepath);
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/x-excel, application/x-msexcel, application/excel, application/vnd.ms-excel");
				driver=new FirefoxDriver();
				JavascriptExecutor js=(JavascriptExecutor)driver;
				Long sw=(Long)js.executeScript("return screen.availWidth;");		
				Long sh=(Long)js.executeScript("return screen.availHeight;");
				int isw=(int)((long)sw);
				int ish=(int)((long)sh);
				driver.manage().window().setPosition(new Point(0,0));
				driver.manage().window().setSize(new Dimension(isw, ish));
				driver.manage().window().maximize();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			FreeCRMLogger.writeToLog("After getting chrome driver");
			
		} else if ((browserType.toLowerCase().contains("safari"))) {
			
			//DesiredCapabilities caps = DesiredCapabilities.safari();
			//caps.setJavascriptEnabled(true);
			//caps.setCapability("ignoreProtectedModeSettings", true);
			driver=new SafariDriver();
			
		} 
		else if ((browserType.toLowerCase().contains("opera"))) {
			
			FreeCRMLogger.writeToLog("Before getting opera driver");
			String operaBrowser=propertyFileReader.getValue("opera_binary");
			//DesiredCapabilities dc=DesiredCapabilities.opera();
			
			if (operaBrowser==null || operaBrowser.trim().equalsIgnoreCase("")){
				operaBrowser="";
				propertyFileReader.setValue("opera_binary", operaBrowser);
			}else {
				//dc.setCapability("opera_binary" , operaBrowser);
			}	
			try{
				driver=new OperaDriver();
				JavascriptExecutor js=(JavascriptExecutor)driver;
				Long sw=(Long)js.executeScript("return screen.availWidth;");		
				Long sh=(Long)js.executeScript("return screen.availHeight;");
				int isw=(int)((long)sw);
				int ish=(int)((long)sh);
				driver.manage().window().setPosition(new Point(0,0));
				driver.manage().window().setSize(new Dimension(isw, ish));
			}catch(Exception e){
				e.printStackTrace();
			}
			FreeCRMLogger.writeToLog("After getting opera driver");

		} else if ((browserType.toLowerCase().contains("google") || ((browserType.toLowerCase().contains("chrome"))))) {
			
			FreeCRMLogger.writeToLog("Before getting chrome driver");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		//	String chromeBinaryLoc = propertyFileReader.getValue("CHROME_BINARY_LOCATION");
			String chromeDriverLoc = propertyFileReader.getValue("CHROME_DRIVER_LOCATION");
			 
		
		//	co.setBinary(new File(chromeBinaryLoc));			
			System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
			
			
			try{
				
	
		       // options.merge(cap);
		       driver = new ChromeDriver();
			   driver.manage().deleteAllCookies();
			   driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			   JavascriptExecutor js=(JavascriptExecutor)driver;
				Long sw=(Long)js.executeScript("return screen.availWidth;");		
				Long sh=(Long)js.executeScript("return screen.availHeight;");
				int isw=(int)((long)sw);
				int ish=(int)((long)sh);
			}catch(Exception e){
				e.printStackTrace();
			}
			FreeCRMLogger.writeToLog("After getting chrome driver");

		} else if ((browserType.toLowerCase().contains("ie"))|| ((browserType.toLowerCase().contains("internet")))) {
			
			
			//DesiredCapabilities capability=DesiredCapabilities.internetExplorer();
		//	capability.setCapability("ignoreProtectedModeSettings", true);			       
		//	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			String ieDriverLoc = propertyFileReader.getValue("IE_DRIVER_LOCATION");
			File file = new File(ieDriverLoc); 
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath()); 
			//DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();   
		//	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
			
		    try{
		    	driver=new InternetExplorerDriver();
		    //	driver=new InternetExplorerDriver();
		    	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
				JavascriptExecutor js=(JavascriptExecutor)driver;
				Long sw=(Long)js.executeScript("return screen.availWidth;");		
				Long sh=(Long)js.executeScript("return screen.availHeight;");
				int isw=(int)((long)sw);
				int ish=(int)((long)sh);
				driver.manage().window().setPosition(new Point(0,0));
				driver.manage().window().setSize(new Dimension(isw, ish));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return driver;
		
		
		//return driver;

}
	
 		  
}
