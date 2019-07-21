package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class FormatMoneyApiTest extends DataProviders {
    Response response;


    @Test(dataProvider = "validMoney")
    public void formatMoney(String money, String expectedString) {
        response = postTest(jsonObject(money),"application/json");
        assertResponse(response,200,expectedString);
    }

    @Test(dataProvider = "validMoney")
    public void formatMoneyInvalidContentType(String money, String expectedString) {
        response = postTest(jsonObject(money),"text/plain");
        assertResponse(response,415,expectedString);
    }

    @Test(dataProvider = "invalidMoney")
    public void formatMoneyInvalidMoney(String money, String expectedString) {
        response = postTest(jsonObject(money),"application/json");
        assertResponse(response,400,expectedString);
    }

}
