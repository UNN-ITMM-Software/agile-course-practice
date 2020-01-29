package ru.unn.agile.numbertowords.model;

import java.math.BigDecimal;

public final class ConverterNumbersToWord {

    private static final String[] numbers = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public static String convertToWord(int number) {
        if (number < 0)
            return "Minus " + getNameOfNumber(-number);
        if (number == 0)
            return "Zero";

        return getNameOfNumber(number);
    }

    public static String convertToWord(Double number) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
        int intPart = bigDecimal.intValue();
        String tempPart = bigDecimal.subtract(new BigDecimal(intPart)).toPlainString();
        int decimalPart = Integer.parseInt(tempPart.split("\\.")[1]);

        if (number < 0)
            return "Minus " + convertToWord(-number);
        if (number >= 0 && number < 1)
            return "Zero point " + convertToWord(decimalPart);

        return getNameOfNumber(intPart) + " point " + getNameOfNumber(decimalPart);
    }

    private static String getNameOfNumber(int number) {
        if (number < 20)
            return numbers[number];
        if (number < 100)
            return tens[number / 10] + ((number % 10 != 0) ? " " : "") + numbers[number % 10];
        if (number < 1000)
            return numbers[number / 100] + " Hundred" + ((number % 100 != 0) ? " " : "") + getNameOfNumber(number % 100);
        if (number < 1000000)
            return getNameOfNumber(number / 1000) + " Thousand"
                    + ((number % 1000 != 0) ? " " : "") + getNameOfNumber(number % 1000);
        if (number < 1000000000)
            return getNameOfNumber(number / 1000000) + " Million"
                    + ((number % 1000000 != 0) ? " " : "") + getNameOfNumber(number % 1000000);

        return "Cant convert number";
    }
}


