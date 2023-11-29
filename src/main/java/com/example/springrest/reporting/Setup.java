package com.example.springrest.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Setup implements ITestListener {
private static ExtentReports extentReports;
public static ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTest test = extentReports.createTest("Test Name" + iTestResult.getTestClass().getName()
        +" - " + iTestResult.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        String fileName=ExtendReporterManager.getReprtNameWithTimeStamp();
        String fullReportPath=System.getProperty("user.dir)"+"\\reports\\"+fileName);
        extentReports=ExtendReporterManager.createInstance(fullReportPath,"Test Automation Report ", "GetAPI");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
      if(extentReports !=null)
      {
          extentReports.flush();
      }
    }
}
