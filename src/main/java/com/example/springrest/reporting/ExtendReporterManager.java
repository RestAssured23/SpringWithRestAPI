package com.example.springrest.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtendReporterManager {
    public static ExtentReports extentReports;
    public static ExtentReports createInstance(String fileName,String reportName,String documentTitle){
        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentReports=new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }
    public static String getReprtNameWithTimeStamp(){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("YYYY_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime=LocalDateTime.now();
        String formattedTime=dateTimeFormatter.format(localDateTime);
        String reportName="TestReport" + formattedTime+".html";
        return reportName;
    }
    public static void logPassDetails(String log){
        Setup.extentTestThreadLocal.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }
    public static void logFailureDetails(String log){
        Setup.extentTestThreadLocal.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logInfoDetails(String log){
        Setup.extentTestThreadLocal.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarningDetails(String log){
        Setup.extentTestThreadLocal.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
        System.out.println("test");
    }
}
