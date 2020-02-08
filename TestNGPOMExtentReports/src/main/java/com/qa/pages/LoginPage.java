package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import com.qa.Base.TestBase;
import com.qa.utils.SeleniumKeywordslib;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends TestBase{
	SeleniumKeywordslib lib = new SeleniumKeywordslib();
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//*[text()='Login']")
	WebElement submitbtn;
	
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
		
	}
	
	public String verifyTitle()
	{
		return  driver.getTitle();
	}
	public HomePage login(String username,String pwd,ExtentTest Extenttest)
	{
		/*email.sendKeys(username);
		Extenttest.log(LogStatus.PASS, "Enter email" +email +"in user name");
		password.sendKeys(pwd);
		Extenttest.log(LogStatus.PASS, "Enter password" +pwd +"in user name");
		submitbtn.click();
		Extenttest.log(LogStatus.PASS, "click on "+submitbtn);*/
		
		lib.enterText(driver, email, username,Extenttest);
		lib.enterText(driver, password, pwd,Extenttest);
		
		lib.click(driver, submitbtn,Extenttest);
		return new HomePage();
		
	}

}
