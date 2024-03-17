package com.dev.webautomation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.dev.webautomation.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Paths;


public class ExtentReportListener implements ITestListener {
    public static ExtentReports extent;
    public static ExtentTest test;

    public static String extentPath = System.getProperty("user.dir") + "/test-output";
    public static final String EXTENT_REPORTS = "TestExecutionReport";
    public static String extentHtmlFile = EXTENT_REPORTS + ".html";
    public static String extentScreenShot = "screenshot.png";

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extentPath = extentPath + "//" + EXTENT_REPORTS;
            File file = new File(extentPath);
            if (!file.exists()) {
                boolean bool = file.mkdir();
                if (bool) {
                    System.out.println("Directory created successfully");
                } else {
                    System.out.println("Sorry couldn't create specified directory");
                }
            }
            extentHtmlFile = file.getAbsolutePath() + "//" + extentHtmlFile;
            extentScreenShot = file.getAbsolutePath() + "//" + extentScreenShot;
            ExtentSparkReporter html = new ExtentSparkReporter(extentHtmlFile);

            extent = new ExtentReports();
            extent.attachReporter(html);
        }
        return extent;
    }

    public static void endReport() {
        extent.flush();
    }


    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());

    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test case is: " + result.getName());
        }
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String imgPath = takeScreenShot(PlaywrightFactory.page, result.getName());

            test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test case is: " + result.getName());
        }
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    //Method for capturing Screenshots
    public String takeScreenShot(Page page, String filename) {
               String path = System.getProperty("user.dir") + "/screenshots/" + filename + "_" + System.currentTimeMillis() + ".png";
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path)).setFullPage(true));
        return path;
    }
}