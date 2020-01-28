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
    public void canConvertEleven() {
        String convertedNumber = convertToWord(11);
        assertEquals("Eleven", convertedNumber);
    }

    @Test
    public void canConvertETwelve() {
        String convertedNumber = convertToWord(12);
        assertEquals("Twelve", convertedNumber);
    }

    @Test
    public void canConvertThirteen() {
        String convertedNumber = convertToWord(13);
        assertEquals("Thirteen", convertedNumber);
    }

    @Test
    public void canConvertESixteen() {
        String convertedNumber = convertToWord(16);
        assertEquals("Sixteen", convertedNumber);
    }

    @Test
    public void canConverEighteen() {
        String convertedNumber = convertToWord(18);
        assertEquals("Eighteen", convertedNumber);
    }

    @Test
    public void canConvertNineteen() {
        String convertedNumber = convertToWord(19);
        assertEquals("Nineteen", convertedNumber);
    }

    @Test
    public void canConvertTwenty() {
        String convertedNumber = convertToWord(20);
        assertEquals("Twenty", convertedNumber);
    }

    @Test
    public void canConvertTwentyOne() {
        String convertedNumber = convertToWord(21);
        assertEquals("Twenty One", convertedNumber);
    }

    @Test
    public void canConvertTwentyTwo() {
        String convertedNumber = convertToWord(22);
        assertEquals("Twenty Two", convertedNumber);
    }

    @Test
    public void canConvertTwentyFive() {
        String convertedNumber = convertToWord(25);
        assertEquals("Twenty Five", convertedNumber);
    }

    @Test
    public void canConvertTwentyNine() {
        String convertedNumber = convertToWord(29);
        assertEquals("Twenty Nine", convertedNumber);
    }

    @Test
    public void canConvertThirty() {
        String convertedNumber = convertToWord(30);
        assertEquals("Thirty", convertedNumber);
    }

    @Test
    public void canConvertThirtyFive() {
        String convertedNumber = convertToWord(35);
        assertEquals("Thirty Five", convertedNumber);
    }

    @Test
    public void canConvertThirtyyNine() {
        String convertedNumber = convertToWord(39);
        assertEquals("Thirty Nine", convertedNumber);
    }

    @Test
    public void canConvertFiftyOne() {
        String convertedNumber = convertToWord(51);
        assertEquals("Fifty One", convertedNumber);
    }

    @Test
    public void canConvertSixtyNine() {
        String convertedNumber = convertToWord(69);
        assertEquals("Sixty Nine", convertedNumber);
    }

    @Test
    public void canConvertSeventySeven() {
        String convertedNumber = convertToWord(77);
        assertEquals("Seventy Seven", convertedNumber);
    }

    @Test
    public void canConvertEighty() {
        String convertedNumber = convertToWord(85);
        assertEquals("Eighty Five", convertedNumber);
    }

    @Test
    public void canConvertNinetyNine() {
        String convertedNumber = convertToWord(99);
        assertEquals("Ninety Nine", convertedNumber);
    }

    @Test
    public void canConvertOneHundred() {
        String convertedNumber = convertToWord(100);
        assertEquals("One Hundred", convertedNumber);
    }

    @Test
    public void canConvertOneHundredThree() {
        String convertedNumber = convertToWord(103);
        assertEquals("One Hundred Three", convertedNumber);
    }

    @Test
    public void canConvertTheeHundredNineteen() {
        String convertedNumber = convertToWord(319);
        assertEquals("Three Hundred Nineteen", convertedNumber);
    }

    @Test
    public void canConvertTwoHundredTwentyTwo() {
        String convertedNumber = convertToWord(222);
        assertEquals("Two Hundred Twenty Two", convertedNumber);
    }

    @Test
    public void canConvertFiveHundredFortySix() {
        String convertedNumber = convertToWord(546);
        assertEquals("Five Hundred Forty Six", convertedNumber);
    }

    @Test
    public void canConvertNineHundredEleven() {
        String convertedNumber = convertToWord(911);
        assertEquals("Nine Hundred Eleven", convertedNumber);
    }

    @Test
    public void canConvertNineHundredNineteenNine() {
        String convertedNumber = convertToWord(999);
        assertEquals("Nine Hundred Ninety Nine", convertedNumber);
    }

    @Test
    public void canConvertOneThousand() {
        String convertedNumber = convertToWord(1000);
        assertEquals("One Thousand", convertedNumber);
    }

    @Test
    public void canConvertOneThousandOne() {
        String convertedNumber = convertToWord(1001);
        assertEquals("One Thousand One", convertedNumber);
    }

    @Test
    public void canConvertOneThousandEleven() {
        String convertedNumber = convertToWord(1011);
        assertEquals("One Thousand Eleven", convertedNumber);
    }

    @Test
    public void canConvertOneThousandOneHundredEleven() {
        String convertedNumber = convertToWord(1111);
        assertEquals("One Thousand One Hundred Eleven", convertedNumber);
    }

    @Test
    public void canConvertFiveThousandSixHundredNineteen() {
        String convertedNumber = convertToWord(5619);
        assertEquals("Five Thousand Six Hundred Nineteen", convertedNumber);
    }

    @Test
    public void canConvertNineThousandNineHundredNinetyNine() {
        String convertedNumber = convertToWord(9999);
        assertEquals("Nine Thousand Nine Hundred Ninety Nine", convertedNumber);
    }

}
