package com.qa.testcases;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.utils.CustomListener;
import com.qa.utils.ExcelTestDataReader;
import com.qa.utils.ExcelTestDataVO;
import com.qa.utils.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//@Listeners(CustomListener.class)
public class LoginTest extends TestBase implements ITestListener{
	LoginPage loginpage;
	TestBase base = new TestBase();
	WebDriver driver; 
	//ITestResult result = null ;
	LinkedHashMap<String, String> map = new LinkedHashMap<>();
   static  ExtentTest Extenttest;
	public LoginTest()
	{
		super();
		
	}
	 
		
	@BeforeMethod
	public void setUp()
	{
	//	extentTestReport = extentReports.startTest("", driver.getCurrentUrl());
		
			driver = base.intialization();
			driver.get(map.get("url"));
		 loginpage = new LoginPage();
	}
	@BeforeClass
	
	public void getTestData()
	{
		
		ExcelTestDataReader dataExcelxReader = new ExcelTestDataReader();
		LinkedHashMap<String, List<ExcelTestDataVO>> map1 = dataExcelxReader.readTestData();
		
		  List<ExcelTestDataVO> list =   map1.get("login");
			 
		  for(ExcelTestDataVO vo : list)
			  
    	  {
			  map.put(vo.getName(), vo.getValue());
    	  }
		  System.out.println("url from excel"+map.get("url"));
	}
	
	@Test( alwaysRun = true)
    public void verifyPageTitle()
	{
		
		
		Extenttest= ExtentTestManager.startTest("verifyPageTitle", "verify page title.");
		 
		System.out.println("driver:"+driver);
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		// To get the site title.
		String title = (String)JS.executeScript("return document.title");
		System.out.println("Title of the webpage : " + title);
		String title2 = loginpage.verifyTitle();
		if(title2.contains("Cogmento"))
		{
			Extenttest.log(LogStatus.PASS, "title of the page" +title2);
		}
		else
		{
			Extenttest.log(LogStatus.FAIL, "title of the page" +title2+"is incorrect");
		}
    	//Assert.ver(title2, "Cogmento CRMs");	
    	
		
	}
	@Test(alwaysRun = true)
	public void login()
	{
		
		 Extenttest = ExtentTestManager.startTest("login","login");
		loginpage.login(map.get("username"),map.get("password"),Extenttest);
	}
	
		
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		 ExtentTestManager.endTest();
		//extentReports.endTest(extentTestReport);
		//extentReports.flush();
		//driver.quit();
	}

}
