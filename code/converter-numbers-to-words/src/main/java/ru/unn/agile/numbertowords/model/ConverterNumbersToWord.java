package ru.unn.agile.numbertowords.model;

public final class ConverterNumbersToWord {

    private static final String[] numbers = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
    };

    public static String convertToWord(int number) {
        if (number == 0) {
            return "Zero";
        }
        return getNameOfNumber(number);
    }

    private static String getNameOfNumber(int number) {
        if (number > 0 && number < numbers.length) {
            return numbers[number];
        }
        return numbers[0];
    }
}


