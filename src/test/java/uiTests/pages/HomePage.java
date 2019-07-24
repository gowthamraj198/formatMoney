package uiTests.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import uiTests.utils.Reports;


public class HomePage extends BasePage {

    private RemoteWebDriver driver;
    private Reports reports;
    protected Logger log = Logger.getLogger(HomePage.class);

    @FindBy(name = "money")
    private WebElement moneyTextBox;
    @FindBy(name = "submit")
    private WebElement submitButton;


    public HomePage(RemoteWebDriver driver, Reports reports) {
        this.driver = driver;
        this.reports = reports;
        PageFactory.initElements(driver, this);
    }

    public HomePage enterValue(String money) throws Exception {
        try {
            moneyTextBox.sendKeys(money);
            log.info("Value entered");
            reports.reportStep(driver, "Value entered", "PASS");
        } catch (Exception e) {
            log.error("Failed to enter value " + e.getMessage());
            reports.reportStep(driver, "Failed to enter value ", "FAIL");
            throw new Exception();
        }
        return new HomePage(driver,reports);
    }

    public OutputPage submitForm() throws Exception {
        try {
            submitButton.click();
            log.info("Value entered and submitted");
            reports.reportStep(driver, "Value entered and submitted", "PASS");
        } catch (Exception e) {
            log.error("Failed to submit the form with value " + e.getMessage());
            reports.reportStep(driver, "Failed to submit the form with value ", "FAIL");
            throw new Exception();
        }
        return new OutputPage(driver,reports);
    }
}
