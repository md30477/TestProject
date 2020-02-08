package com.qa.testcases;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.LoginPage;
import com.qa.utils.ExcelTestDataReader;
import com.qa.utils.ExcelTestDataVO;
import com.qa.utils.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
//@Listeners(CustomListener.class)
public class ContactsTest extends TestBase{
	//LinkedHashMap<String, String> map = new LinkedHashMap<>();
	LoginPage loginpage;
	ContactsPage contacts;
	 static  ExtentTest Extenttest;
public ContactsTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		
		
		intialization();
		driver.get("https://ui.freecrm.com/");
		 loginpage = new LoginPage();
		
		 contacts= new ContactsPage();
	}
	
	/*@BeforeClass
	public void getTestData()
	{
		
		ExcelTestDataReader dataExcelxReader = new ExcelTestDataReader();
		LinkedHashMap<String, List<ExcelTestDataVO>> map1 = dataExcelxReader.readTestData();
		
		  List<ExcelTestDataVO> list =   map1.get("login");
			 
		  for(ExcelTestDataVO vo : list)
			  
    	  {
			  map.put(vo.getName(), vo.getValue());
    	  }
	}*/
	@Test(alwaysRun = true)
    public void clickonContacts()
	{
		Extenttest= ExtentTestManager.startTest("clickonContacts", "clickonContacts.");
		 loginpage.login("30477md@gmail.com", "Pranuthi2020$",Extenttest);
		contacts.clickonContacts(Extenttest);
		
	}
	@Test(alwaysRun = true)
    public void createContacts()
	{
		Extenttest = ExtentTestManager.startTest("createContacts", "createContacts.");
		 loginpage.login("30477md@gmail.com", "Pranuthi2020$",Extenttest);
		contacts.clickonContacts(Extenttest);
		contacts.createNewContacts("Madyyyyyyyyyyyy", "D",Extenttest);
	}
		
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		 ExtentTestManager.endTest();
	}
}
