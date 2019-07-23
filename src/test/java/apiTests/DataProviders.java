package apiTests;

import org.testng.annotations.DataProvider;

public class DataProviders extends BaseClass{
    @DataProvider(name = "validMoney")
    public static Object[][] validMoney() {
        return new Object[][]{
                {"12", "12.00"},
                {"12.5","12.50"},
                {"125768","125 768.00"},
                {"123458765434256789765432567","123 458 765 434 256 789 765 432 567.00"},
                {"3458765434256789765432567.2395678","3 458 765 434 256 789 765 432 567.24"},
                {"12353534.2995678","12 353 534.30"},
                {"12353534.2925678","12 353 534.29"},
                {"12353534.096","12 353 534.10"},
                {"12353534.997","12 353 535.00"},
        };
    }

    @DataProvider(name = "invalidMoney")
    public static Object[][] invalidMoney() {
        return new Object[][]{
                {"test", invalidNewPasswordMessage},
                {"123.", invalidNewPasswordMessage},
                {"123.y", invalidNewPasswordMessage},
                {"123.87.09", invalidNewPasswordMessage},
                {"-1", invalidNewPasswordMessage},
                {"-123.35", invalidNewPasswordMessage},
                {"1234d.35", invalidNewPasswordMessage},
                {"1234.35r", invalidNewPasswordMessage},
        };
    }

    @DataProvider(name = "emptyMoney")
    public static Object[][] emptyMoney() {
        return new Object[][]{
                {"", mandatoryMessage}
        };
    }
}
