package com.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports/ExtentReport.html");
            spark.config().setReportName("Sauce Demo Test Report");
            spark.config().setDocumentTitle("Automation Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Farhad Javadi");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
