package ru.unn.agile.numbertowords.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.unn.agile.numbertowords.model.ConverterNumbersToWord.convertToWord;


public class NumbersToWordConverterTest {

    @Test
    public void canConvertZero() {
        String convertedNumber = convertToWord(0);
        assertEquals("Zero", convertedNumber);
    }

    @Test
    public void canConvertOne() {
        String convertedNumber = convertToWord(1);
        assertEquals("One", convertedNumber);
    }

    @Test
    public void canConvertTwo() {
        String convertedNumber = convertToWord(2);
        assertEquals("Two", convertedNumber);
    }

    @Test
    public void canConvertThree() {
        String convertedNumber = convertToWord(3);
        assertEquals("Three", convertedNumber);
    }

    @Test
    public void canConvertFive() {
        String convertedNumber = convertToWord(5);
        assertEquals("Five", convertedNumber);
    }

    @Test
    public void canConvertEight() {
        String convertedNumber = convertToWord(8);
        assertEquals("Eight", convertedNumber);
    }

    @Test
    public void canConvertTen() {
        String convertedNumber = convertToWord(10);
        assertEquals("Ten", convertedNumber);
    }

    @Test
    public void cantConvertEleven() {
        String convertedNumber = convertToWord(11);
        assertEquals("", convertedNumber);
    }
}
