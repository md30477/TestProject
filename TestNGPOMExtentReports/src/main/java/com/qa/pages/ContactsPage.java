package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.utils.SeleniumKeywordslib;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ContactsPage extends TestBase{
	SeleniumKeywordslib lib = new SeleniumKeywordslib();
//	PropertyFileReader prop;
	 static  ExtentTest Extenttest;
	
	@FindBy(xpath="//*[text()='Contacts']")
	WebElement contacts;
	
	@FindBy(xpath="//*[text()='New']")
	WebElement newBtn;

	@FindBy(name="first_name")
	WebElement frstName;
	
	@FindBy(name="last_name")
	WebElement lastname;

	@FindBy(xpath="//*[text()='Save']")
	WebElement saveBtn;
	
	public ContactsPage()
	{
		System.out.println("Driver in contacts"+driver) ;
		PageFactory.initElements(driver, this);
		
	}

	
	public void clickonContacts(ExtentTest Extenttest)
	{
	//contacts.click();
	//Extenttest.log(LogStatus.PASS, "Click on contacts button");
		lib.click(driver, contacts,Extenttest);
		try {
		 	Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createNewContacts(String frstname,String lastnme,ExtentTest Extenttest)
	{
		//newBtn.click();
		//Extenttest.log(LogStatus.PASS, "Click on New button");
		lib.click(driver, contacts,Extenttest);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frstName.sendKeys(frstname);
		lastname.sendKeys(lastnme);
		saveBtn.click();
	}
}
