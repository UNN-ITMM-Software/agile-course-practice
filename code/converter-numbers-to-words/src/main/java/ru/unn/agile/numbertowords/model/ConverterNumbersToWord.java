package ru.unn.agile.numbertowords.model;

public final class ConverterNumbersToWord {

    private static final String[] numbers = {
            "", "One", "Two", "Three"
    };

    public static String convertToWord(int number) {
        if (number == 0) {
            return "Zero";
        }
        return getNameOfNumber(number);
    }

    private static String getNameOfNumber(int number) {
        if (number == 1) {
            return numbers[1];
        }
        if (number == 2) {
            return numbers[2];
        }
        return numbers[0];
    }
}


