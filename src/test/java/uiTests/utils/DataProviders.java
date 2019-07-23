package uiTests.utils;

import org.testng.annotations.DataProvider;

public class DataProviders{
    public static String invalidNewPasswordMessage = "Enter only valid whole or decimal absolute number" ;
    public static String mandatoryMessage = "Input cannot be empty!" ;

    @DataProvider(name = "validMoney")
    public static Object[][] validMoney() {
        return new Object[][]{
                {"12", "12.00"},
                {"12.5","12.50"},
                {"125768","125 768.00"},
                {"3458765434256789765432567.2395678","3 458 765 434 256 789 765 432 567.24"},
                {"12353534.2995678","12 353 534.30"}
        };
    }

    @DataProvider(name = "invalidMoney")
    public static Object[][] invalidMoney() {
        return new Object[][]{
                {"123y", invalidNewPasswordMessage,"400"},
                {"123.", invalidNewPasswordMessage,"400"},
                {"123.y", invalidNewPasswordMessage,"400"},
                {"123.87.09", invalidNewPasswordMessage,"400"},
                {"-1", invalidNewPasswordMessage,"400"}
        };
    }

    @DataProvider(name = "emptyMoney")
    public static Object[][] emptyMoney() {
        return new Object[][]{
                {"", mandatoryMessage, "400"}
        };
    }
}
