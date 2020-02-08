package com.qa.utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.qa.Base.TestBase;

public class SuiteListener  extends TestBase implements ISuiteListener{

	
	public void onStart(ISuite suite) {
		System.out.println(" SuiteListener onStart");
		//extentReports =TestBase.Instance();
		
	}

	
	public void onFinish(ISuite suite) {
		System.out.println(" SuiteListener onFinish");
		//flushReports();
		
	}

}
