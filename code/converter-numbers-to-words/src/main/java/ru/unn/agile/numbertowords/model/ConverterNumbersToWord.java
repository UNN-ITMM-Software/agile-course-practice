package ru.unn.agile.numbertowords.model;

import java.math.BigDecimal;

public final class ConverterNumbersToWord {

    private ConverterNumbersToWord() {
    }

    private static final String[] NAMES_OF_NUMBERS = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"
    };

    private static final String[] NAMES_OF_TENS = {
            "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 1000;
    private static final int MILLION = 1000000;
    private static final int BILLION = 1000000000;


    public static String convertToWord(final int number) {
        if (number < 0) {
            return "Minus " + getNameOfNumber(-number);
        }
        if (number == 0) {
            return "Zero";
        }
        return getNameOfNumber(number);
    }

    public static String convertToWord(final Double number) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
        int intPart = bigDecimal.intValue();
        String tempPart = bigDecimal.subtract(new BigDecimal(intPart)).toPlainString();
        int decimalPart = Integer.parseInt(tempPart.split("\\.")[1]);

        if (number < 0) {
            return "Minus " + convertToWord(-number);
        }
        if (number >= 0 && number < 1) {
            return "Zero point " + convertToWord(decimalPart);
        }

        return getNameOfNumber(intPart) + " point " + getNameOfNumber(decimalPart);
    }

    private static String getNameOfNumber(final int number) {
        if (number < TWENTY) {
            return NAMES_OF_NUMBERS[number];
        }
        if (number < HUNDRED) {
            return NAMES_OF_TENS[number / TEN] + ((number % TEN != 0) ? " " : "")
                    + NAMES_OF_NUMBERS[number % TEN];
        }
        if (number < THOUSAND) {
            return NAMES_OF_NUMBERS[number / HUNDRED] + " Hundred"
                    + ((number % HUNDRED != 0) ? " " : "") + getNameOfNumber(number % HUNDRED);
        }
        if (number < MILLION) {
            return getNameOfNumber(number / THOUSAND) + " Thousand"
                    + ((number % THOUSAND != 0) ? " " : "") + getNameOfNumber(number % THOUSAND);
        }
        if (number < BILLION) {
            return getNameOfNumber(number / MILLION) + " Million"
                    + ((number % MILLION != 0) ? " " : "") + getNameOfNumber(number % MILLION);
        }
        if (number < Integer.MAX_VALUE) {
            return getNameOfNumber(number / BILLION) + " Billion"
                    + ((number % BILLION != 0) ? " " : "") + getNameOfNumber(number % BILLION);
        }

        return "Cant convert number";
    }
}


