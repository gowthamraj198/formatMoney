package uiTests.tests.web_tests;

import org.testng.annotations.Test;
import uiTests.pages.HomePage;
import uiTests.utils.DataProviders;
import uiTests.tests.common.BaseTest;

public class FormatMoneyHappyFlow extends BaseTest{

    @Test(dataProvider = "validMoney", dataProviderClass = DataProviders.class)
    public void formatMoney(String money, String output) throws Exception {
        reports.startTestCase("Convert money to string with input : " + money, "Get money in number and show it as string");
        driver.get(pageURL);
        new HomePage(driver,reports)
                .enterValue(money)
                .submitForm()
                .assertOutput(output);
    }
}
