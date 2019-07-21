package unitTests;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class FormatMoneyUnitTest extends UnitTest{

    @Test
    public void isMoneyValidWholeNumberTest() {
        assertEquals(formatMoney.getNumber("12"), true);
    }

    @Test
    public void isMoneyValidDecimalNumberTest() {
        assertEquals(formatMoney.getNumber("1223423423.54"), true);
    }

    @Test
    public void isMoneyValidLessThanOne() {
        assertEquals(formatMoney.getNumber("0.54"), true);
    }

    @Test
    public void isMoneyValidNegativeWholeNumber() {
        getNumber("-154");
    }

    @Test
    public void isMoneyValidNegativeDecimalNumber() {
        getNumber("-154.54");
    }

    @Test
    public void isMoneyValidWithSpecialChars() {
        getNumber("154&");
    }

    @Test
    public void isMoneyValidDecimalNumberWithSpecialChars() {
        getNumber("154&");
    }

    @Test
    public void isMoneyValidWithChars() {
        getNumber("154P");
    }

    @Test
    public void isMoneyValidDecimalNumberWithChars() {
        getNumber("154.23P");
    }

    @Test
    public void isMoneyValidWithSpace() {
        getNumber("154234 23");
    }

    @Test
    public void convertWholeNumberToBigInteger() {
        assertEquals(formatMoney.convertStringToNumber("1287").getClass()== BigInteger.class, true);
    }

    @Test
    public void convertDecimalNumberToBigDecimal() {
        assertEquals(formatMoney.convertStringToNumber("12987.34").getClass()== BigDecimal.class, true);
    }

    @Test
    public void convertBigDecimalToStringRoundToNextNumber() {
        assertEquals(formatMoney.moneyToString(new BigDecimal("12987243.12587243")), "12987243.13");
    }

    @Test
    public void convertBigDecimalToStringWith1DecimalPoint() {
        assertEquals(formatMoney.moneyToString(new BigDecimal("12987243.1")), "12987243.10");
    }

    @Test
    public void convertBigDecimalToStringWithDecimalPointsAs0() {
        assertEquals(formatMoney.moneyToString(new BigDecimal("12987243.0")), "12987243.00");
    }

    @Test
    public void convertBigDecimalToStringRoundToSameNumber() {
        assertEquals(formatMoney.moneyToString(new BigDecimal("12987243.12187243")), "12987243.12");
    }

    @Test
    public void convertBigIntegerToString() {
        assertEquals(formatMoney.moneyToString(new BigDecimal("1298724356887645324536787967653432245467586")), "1298724356887645324536787967653432245467586.00");
    }

    @Test
    public void convertStringToBigDecimal() {
        assertEquals(formatMoney.formatMoney("1298724356887645324536787967653432245467586.2345678"), "1298724356887645324536787967653432245467586.23");
    }

    @Test
    public void convertStringToBigInteger() {
        assertEquals(formatMoney.formatMoney("1298724"), "1298724.00");
    }

    @Test
    public void convertStringToBigIntegerwithChars() {
        formatMoney("23r");
    }

    @Test
    public void convertStringToBigDecimalwithChars() {
        formatMoney("23.45r");
    }

    @Test
    public void applyPlaceValueWith2Digits() {
        assertEquals(formatMoney.applyPlaceValue("23.45"),"23.45");
    }

    @Test
    public void applyPlaceValueWith3Digits() {
        assertEquals(formatMoney.applyPlaceValue("235.45"),"235.45");
    }

    @Test
    public void applyPlaceValueWith3DigitsWholeNumber() {
        assertEquals(formatMoney.applyPlaceValue("235"),"235");
    }

    @Test
    public void applyPlaceValueWith4DigitsWholeNumber() {
        assertEquals(formatMoney.applyPlaceValue("2357.00"),"2 357.00");
    }

    @Test
    public void applyPlaceValueWith7DigitsWholeNumber() {
        assertEquals(formatMoney.applyPlaceValue("2357587.00"),"2 357 587.00");
    }

    @Test
    public void applyPlaceValueWith7DigitsWholeNumberAndDecimalDigits() {
        assertEquals(formatMoney.applyPlaceValue("2357587.34534"),"2 357 587.34534");
    }
}
