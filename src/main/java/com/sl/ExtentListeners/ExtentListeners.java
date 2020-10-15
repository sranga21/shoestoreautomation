package com.sl.ExtentListeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sl.utilities.DriverManager;




public class ExtentListeners implements ITestListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	

	public void onTestStart(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test); 

	}
	
	
	public void onTestSuccess(ITestResult result) {
		
		try {
			String screenshotPath = captureScreenshot();
			testReport.get().info("<b>" + "<font color=" + "black>" + "Screenshot on Success" + "</font>" + "</b>",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {

			}
		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		

	}

	public void onTestFailure(ITestResult result) {	
		
		String reason = result.getThrowable().toString();			
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		
		/*String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");*/		
			
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" +"Finished Test Case: "+ Thread.currentThread().getName()+" with failure : Expand to see"
				+ "</font>" + "</b >" + "</summary>" + "<b>"+ "Failure at [" + reason + "]</b><br>"+excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
			
	 try {

		 String screenshotPath = captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (IOException e) {

		}
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}
	
	

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}
 
	public String captureScreenshot() {		
		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		long threadId = Thread.currentThread().getId();
		String screenshotName = System.currentTimeMillis()+"_threadId_"+threadId+".jpg";
		String screenshotPath =System.getProperty("user.dir")+ "\\reports\\screenshots\\"+screenshotName;
		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return screenshotPath;	
	}
	

}
