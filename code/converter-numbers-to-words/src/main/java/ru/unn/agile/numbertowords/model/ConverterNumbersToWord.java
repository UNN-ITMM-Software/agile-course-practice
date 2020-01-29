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
        if (number < 20)
            return numbers[number];
        if (number < 100)
            return tens[number / 10] + ((number % 10 != 0) ? " " : "") + numbers[number % 10];
        if (number < 1000)
            return numbers[number / 100] + " Hundred" + ((number % 100 != 0) ? " " : "") + getNameOfNumber(number % 100);
        if (number < 100000)
            return getNameOfNumber(number / 1000) + " Thousand"
                    + ((number % 1000 != 0) ? " " : "") + getNameOfNumber(number % 1000);
        return "";
    }
}


