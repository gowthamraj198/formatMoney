package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class FormatMoneyApiTest extends DataProviders {
    Response response;


    @Test(dataProvider = "validMoney")
    public void formatMoney(String money, String expectedString) {
        response = postTest(money);
        assertResponse(response,200,expectedString);
    }

    @Test(dataProvider = "invalidMoney")
    public void formatMoneyInvalidMoney(String money, String expectedString) {
        response = postTest(money);
        assertResponse(response,400,expectedString);
    }

    @Test(dataProvider = "emptyMoney")
    public void formatMoneyEmptyMoney(String money, String expectedString) {
        response = postTest(money);
        assertResponse(response,400,expectedString);
    }

}
