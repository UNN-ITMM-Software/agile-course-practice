package ru.unn.agile.numbertowords.model;

public final class ConverterNumbersToWord {

    public static String convertToWord(int number) {
        if (number == 1) {
            return "One";
        }
        if (number == 0) {
            return "Zero";
        }
        return "";
    }
}


