package uiTests.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import uiTests.tests.common.BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reports {


	private ExtentTest test;
	private static ExtentReports extent;
	public DateFormat dateFormat= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S");
	private static String testExecutionTimeStamp = BaseTest.testExecutionTimeStamp;


	public void reportStep(RemoteWebDriver driver, String desc, String status) {

		Date date = new Date();

		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			captureScreenShot(date,driver);
			test.log(LogStatus.PASS, desc+test.addScreenCapture("./../screenshots_" + testExecutionTimeStamp +"/images/"+ dateFormat.format(date) + ".jpg"));
        }else if(status.toUpperCase().equals("FAIL")){
			captureScreenShot(date,driver);
			test.log(LogStatus.FAIL, desc+test.addScreenCapture("./../screenshots_" + testExecutionTimeStamp +"/images/"+ dateFormat.format(date) + ".jpg"));
			throw new  RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.INFO, desc);
		}else if(status.toUpperCase().equals("SKIP")){
			captureScreenShot(date,driver);
			test.log(LogStatus.SKIP, desc+test.addScreenCapture("./../screenshots_" + testExecutionTimeStamp +"/images/"+ dateFormat.format(date) + ".jpg"));
		}else if(status.toUpperCase().equals("ERROR")) {
			test.log(LogStatus.ERROR, desc);
		}

	}

	public  void captureScreenShot(Date date,RemoteWebDriver driver){

		File src= (driver.getScreenshotAs(OutputType.FILE));
		try {
			FileUtils.copyFile(src, new File("./Output/screenshots_" + testExecutionTimeStamp +"/images/"+ dateFormat.format(date) + ".jpg"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void startResult(){
		extent = new ExtentReports("./Output/screenshots_" + testExecutionTimeStamp + "/" + "TestReport.html", true);
	}

	public void startTestCase(String testname, String desc)
	{
		test = extent.startTest(testname, desc);
	}

	public void endTest() {extent.endTest(test);}

	public void endResult(){
		//extent.endTest(test);
		extent.flush();
	}


}
