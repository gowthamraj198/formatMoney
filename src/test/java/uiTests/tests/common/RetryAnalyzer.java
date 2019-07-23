package uiTests.tests.common;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 1;        //change to 1 to retry one more time
	Logger log = Logger.getLogger(BaseTest.class);
	String resultName;
	static String makeReports="";

	@Override
	public boolean retry(ITestResult result) {

		if(counter < retryLimit)
		{
			log.error("Retrying test " + result.getName() + " with status "
						+ getResultStatusName(result.getStatus()) + " for one more time");
			counter++;
			makeReports="FAIL";
			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {

    	if(status==1)
    		resultName = "SUCCESS";
    	if(status==2)
    		resultName = "FAILURE";
    	if(status==3)
    		resultName = "SKIP";
		return resultName;
    }
}
