package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import static org.testng.AssertJUnit.assertEquals;

public class BaseClass {
    public String URL="http://localhost:7338/getmoney";
    public static String invalidNewPasswordMessage = "Enter only valid whole or decimal absolute number" ;
    public static String invalidContentTypeMessage = "Unsupported Media Type" ;
    public static String mandatoryMessage = "Input cannot be empty!" ;


    public Response postTest(String requestParam) {
         return RestAssured.given().formParams("money", requestParam).post(URL);
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
