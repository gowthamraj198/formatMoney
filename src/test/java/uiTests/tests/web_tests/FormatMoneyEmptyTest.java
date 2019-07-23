package uiTests.tests.web_tests;

import org.testng.annotations.Test;
import uiTests.pages.HomePage;
import uiTests.tests.common.BaseTest;
import uiTests.utils.DataProviders;

public class FormatMoneyEmptyTest extends BaseTest{

    @Test(dataProvider = "emptyMoney", dataProviderClass = DataProviders.class)
    public void formatMoney(String money, String message, String status) throws Exception {
        reports.startTestCase("Convert money to string - empty case", "Get empty field and show it as string");
        driver.get(pageURL);
        new HomePage(driver,reports)
                .enterValue(money)
                .submitForm()
                .assertFailureStatus(status)
                .assertFailureMessage(message);
    }
}
