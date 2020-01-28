package ru.unn.agile.numbertowords.model;

public final class ConverterNumbersToWord {

    private static final String[] numbers = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public static String convertToWord(int number) {
        if (number == 0) {
            return "Zero";
        }
        return getNameOfNumber(number);
    }

    private static String getNameOfNumber(int number) {
        if (number >= 20) {
            return tens[number / 10] + " " + numbers[number % 10];
        }
        return numbers[number];
    }
}


