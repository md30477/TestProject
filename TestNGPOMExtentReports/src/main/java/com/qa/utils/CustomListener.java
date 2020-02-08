package com.qa.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.Base.TestBase;

public class CustomListener extends TestBase implements ITestListener{
	//public static ExtentReports extentReports;
	// public static  ExtentTest extentTestReport;
	
	 
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		//extentReports.close();
		 System.out.println("onFinish:");
		
	}

	
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

		System.out.println("onStart:");
		//extentReports =TestBase.Instance();
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("onTestFailedButWithinSuccessPercentage:");
	}

	
	public void onTestFailure(ITestResult result) {
		//captureScreenShot(result.getMethod().getMethodName());
		System.out.println("onTestFailure:");
		//extentReports.flush();
		//extentReports.endTest(extentTestReport);
	}

	
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("onTestSkipped:");
	}


	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println(" madvi, i am in onTestStart"+ITestNGMethod.class);
		//extentTestReport = extentReports.startTest(result.getMethod().getMethodName(), driver.getCurrentUrl());
		
		
	}

	
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		//extentReports.flush();
		//extentReports.endTest(extentTestReport);
		 //extentReports.flush();
	}

}
