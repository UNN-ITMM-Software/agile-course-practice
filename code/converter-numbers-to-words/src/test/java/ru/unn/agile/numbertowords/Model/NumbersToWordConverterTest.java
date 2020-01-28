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
}
