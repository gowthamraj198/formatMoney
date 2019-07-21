package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;

import static org.testng.AssertJUnit.assertEquals;

public class BaseClass {
    RequestSpecification request;
    JSONObject requestParams = new JSONObject();
    public static String invalidNewPasswordMessage = "Enter only valid whole or decimal absolute number" ;
    public static String invalidContentTypeMessage = "Unsupported Media Type" ;
    public static String mandatoryMessage = "Input cannot be empty!" ;

    @BeforeTest
    public void beforeTest()
    {
        request = RestAssured.given();
    }

    public Response postTest(JSONObject requestParams, String content_type) {
        request.header("Content-Type", content_type);
        request.body(requestParams.toJSONString());
        return request.post("http://localhost:7238/getmoney");
    }

    public JSONObject jsonObject(String money)
    {
        requestParams.put("money",money);
        return requestParams;
    }

    public void  assertResponse(Response response, int statusCode, String message)
    {
        assertEquals(response.getStatusCode(), statusCode);
        if(statusCode==200)
        {
            System.out.println(response.asString());
            assertEquals(response.asString(),message);
        }
        else if(statusCode==415)
        {
            assertEquals(response.asString().contains(invalidContentTypeMessage),true);
        }
        else
            response.then().body("message", Matchers.is(message));
    }
}
