package uiTests.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uiTests.utils.Reports;


public class OutputPage extends BasePage{

    private     RemoteWebDriver driver;
    private     Reports         reports;
    protected   Logger          log = Logger.getLogger(OutputPage.class);

    @FindBy (tagName = "body")
    private WebElement output;
    @FindBy (className = "status")
    private WebElement responseStatus;
    @FindBy (className = "message")
    private WebElement responseMessage;

    public OutputPage(RemoteWebDriver driver, Reports reports)
    {
        this.driver = driver;
        this.reports = reports;
        PageFactory.initElements(driver,this);
    }

    public OutputPage assertOutput(String money) throws Exception
    {
        try {
            if(output.getText().equals(money))
            {
                log.info("Output matched!");
                reports.reportStep(driver, "Output matched!", "PASS");
            }
            else
            {
                log.info("Output is not as expected");
                reports.reportStep(driver, "Output is not as expected", "FAIL");
            }
        } catch (Exception e) {
            log.error("Cannot read output value " + e.getMessage());
            reports.reportStep(driver, "Cannot read output value ", "FAIL");
            throw new Exception();
        }
        return new OutputPage(driver,reports);
    }

    public OutputPage assertFailureStatus(String status) throws Exception
    {
        try {
            if(responseStatus.getText().equals(status))
            {
                log.info("Response status is 400");
                reports.reportStep(driver, "Response status is 400", "PASS");
            }
            else
            {
                log.info("Response status is not 400");
                reports.reportStep(driver, "Response status is not 400", "FAIL");
            }
        } catch (Exception e) {
            log.error("Cannot read response status " + e.getMessage());
            reports.reportStep(driver, "Cannot read response status ", "FAIL");
            throw new Exception();
        }
        return new OutputPage(driver,reports);
    }

    public OutputPage assertFailureMessage(String message) throws Exception
    {
        try {
            if(responseMessage.getText().equals(message))
            {
                log.info("Response message is as expected");
                reports.reportStep(driver, "Response message is as expected", "PASS");
            }
            else
            {
                log.info("Response message is not as expected");
                reports.reportStep(driver, "Response message is not as expected", "FAIL");
            }
        } catch (Exception e) {
            log.error("Cannot read response message " + e.getMessage());
            reports.reportStep(driver, "Cannot read response message ", "FAIL");
            throw new Exception();
        }
        return new OutputPage(driver,reports);
    }
}
