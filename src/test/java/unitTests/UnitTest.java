package unitTests;


import formatMoney.base.FormatMoney;

import static org.junit.Assert.assertEquals;

public class UnitTest {
    FormatMoney formatMoney = new FormatMoney();
    public String invalidNewPasswordMessage = "Enter only valid whole or decimal absolute number" ;

    public void getNumber(String money) {
        try {
            formatMoney.getNumber(money);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), invalidNewPasswordMessage);
        }
    }

    public void formatMoney(String money) {
        try {
            formatMoney.formatMoney(money);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), invalidNewPasswordMessage);
        }
    }
}
