package formatMoney.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatMoney {

    /*
        Function to check a valid input
        The input should have only a number with or without decimal points
     */

    public boolean getNumber(String money) throws IllegalArgumentException
    {
        Pattern pattern = Pattern.compile("([0-9]*[.])?[0-9]+$");
        Matcher matcher = pattern.matcher(money);
        if(matcher.matches())
            return true;
        else
            throw new IllegalArgumentException(Messages.invalidInputMessage);
    }

       /*
        Function to convert Number in String format to
        a. BigDecimal if the input has decimal part
        b. BigInteger if the input is a whole number
     */

    public Object convertStringToNumber(String money)
    {
        if(money.contains("."))
        {
            return new BigDecimal(money);
        }
        else
        {
            return new BigInteger(money);
        }
    }

    /*
        Function to add 2 0s as decimal part in the input is a whole number
     */

    public String moneyToString(BigInteger money)
    {
        return money.toString()+".00";
    }

    /*
        Function to add scale the decimal part upto 2 decimal points
     */

    public String moneyToString(BigDecimal money)
    {
        return money.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    }

    /*
        Function to
        a. check the condition
        b. format money to string
     */

    public String formatMoney(String money) throws IllegalArgumentException
    {
        Object number;
        if(getNumber(money))
        {
           number = convertStringToNumber(money);
           if(number.getClass()==BigDecimal.class)
               return moneyToString((BigDecimal) number);
           else if(number.getClass()==BigInteger.class)
               return moneyToString((BigInteger) number);
        }
            return "money formating failed!";
    }

    /*
        Function to add place Value to the final String. i.e., a space after every 3rd digit in the non-decimal part
     */

    public String applyPlaceValue(String money)
    {
        String[] splitMoney;
        String moneyWholePartReverse;
        String moneyWithPlaceValue;
        String moneyWithPlaceValueRevere;
        splitMoney=money.split("\\.");
        if(splitMoney[0].length()>3)
        {

            moneyWholePartReverse = reverseString(splitMoney[0]);
            moneyWithPlaceValue = moneyWholePartReverse.replaceAll("...", "$0 ");
            moneyWithPlaceValueRevere = reverseString(moneyWithPlaceValue);
            return moneyWithPlaceValueRevere.trim() +"."+splitMoney[1];
        }
        else
        {
            return money;
        }
    }

     /*
        Function to reverse a string
     */

    public String reverseString(String text)

    {
        String reverseMoney="";
        char[] reverse = text.toCharArray();

        for (int i = reverse.length-1; i>=0; i--)
            reverseMoney = reverseMoney+reverse[i];

        return reverseMoney;
    }

    /*
        Function that gives output
     */

    public String moneyToStringFinal(String money) throws IllegalArgumentException
    {
        return applyPlaceValue(formatMoney(money));
    }

    public static void main(String[] args)
    {
        FormatMoney formatMoney=new FormatMoney();
        String money="12657";
//        System.out.println(formatMoney.formatMoney(money));
        System.out.println(formatMoney.moneyToStringFinal(money));
    }

}
