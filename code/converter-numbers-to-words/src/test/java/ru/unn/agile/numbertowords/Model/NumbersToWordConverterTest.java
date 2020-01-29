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

    @Test
    public void canConvertFifteenThousandFourHundredFiftySix() {
        String convertedNumber = convertToWord(15456);
        assertEquals("Fifteen Thousand Four Hundred Fifty Six", convertedNumber);
    }

    @Test
    public void canConvertFiftyThousand() {
        String convertedNumber = convertToWord(50000);
        assertEquals("Fifty Thousand", convertedNumber);
    }

    @Test
    public void canConvertSeventySevenThousandFiveHundredFortyEight() {
        String convertedNumber = convertToWord(77548);
        assertEquals("Seventy Seven Thousand Five Hundred Forty Eight", convertedNumber);
    }

    @Test
    public void canConvertFiftyThousandOne() {
        String convertedNumber = convertToWord(50001);
        assertEquals("Fifty Thousand One", convertedNumber);
    }

    @Test
    public void canConvertNinetyNineThousandNineHundredNinetyNine() {
        String convertedNumber = convertToWord(99999);
        assertEquals("Ninety Nine Thousand Nine Hundred Ninety Nine", convertedNumber);
    }

    @Test
    public void canConvertOneHundredThousand() {
        String convertedNumber = convertToWord(100000);
        assertEquals("One Hundred Thousand", convertedNumber);
    }

    @Test
    public void canConvertOneHundredElevenThousand() {
        String convertedNumber = convertToWord(111000);
        assertEquals("One Hundred Eleven Thousand", convertedNumber);
    }

    @Test
    public void canConvertTwoHundredFortySixThousandThreeHundredFiftyOne() {
        String convertedNumber = convertToWord(246351);
        assertEquals("Two Hundred Forty Six Thousand Three Hundred Fifty One", convertedNumber);
    }

    @Test
    public void canConvertFourHundredThirtyFiveThousandSixHundredForty() {
        String convertedNumber = convertToWord(435640);
        assertEquals("Four Hundred Thirty Five Thousand Six Hundred Forty", convertedNumber);
    }

    @Test
    public void canConvertSixHundredSixtySixThousandSixHundredSixtySix() {
        String convertedNumber = convertToWord(666666);
        assertEquals("Six Hundred Sixty Six Thousand Six Hundred Sixty Six", convertedNumber);
    }

    @Test
    public void canConvertNineHundredEightySevenThousandSixHundredFiftyFour() {
        String convertedNumber = convertToWord(987654);
        assertEquals("Nine Hundred Eighty Seven Thousand Six Hundred Fifty Four", convertedNumber);
    }

    @Test
    public void canConvertNegativeNumberSeventeen() {
        String convertedNumber = convertToWord(-17);
        assertEquals("Minus Seventeen", convertedNumber);
    }

    @Test
    public void canConvertNegativeNumberSixHundredForty() {
        String convertedNumber = convertToWord(-640);
        assertEquals("Minus Six Hundred Forty", convertedNumber);
    }

    @Test
    public void canConvertMinusSixHundredSixtySixThousandSixHundredSixtySix() {
        String convertedNumber = convertToWord(-666666);
        assertEquals("Minus Six Hundred Sixty Six Thousand Six Hundred Sixty Six", convertedNumber);
    }

    @Test
    public void canConvertOneMillion() {
        String convertedNumber = convertToWord(1000000);
        assertEquals("One Million", convertedNumber);
    }

    @Test
    public void canConvertOneMillionFiveHundredSixtyOneThousandFiveHundredOne() {
        String convertedNumber = convertToWord(1561501);
        assertEquals("One Million Five Hundred Sixty One Thousand Five Hundred One", convertedNumber);
    }

    @Test
    public void canConvertFiftyFourMillionOneHundred() {
        String convertedNumber = convertToWord(54000100);
        assertEquals("Fifty Four Million One Hundred", convertedNumber);
    }

    @Test
    public void canConvertNineHundredNineMillionNinetyThousandNineHundredNine() {
        String convertedNumber = convertToWord(909090909);
        assertEquals("Nine Hundred Nine Million Ninety Thousand Nine Hundred Nine", convertedNumber);
    }

    @Test
    public void canConvertFloat() {
        String convertedNumber = convertToWord(2.75);
        assertEquals("Two point Seventy Five", convertedNumber);
    }

    @Test
    public void canConvertFloatZero() {
        String convertedNumber = convertToWord(0.0);
        assertEquals("Zero point Zero", convertedNumber);
    }

    @Test
    public void canConvertFloatZeroPointOne() {
        String convertedNumber = convertToWord(0.1);
        assertEquals("Zero point One", convertedNumber);
    }

    @Test
    public void canConvertFloatZeroPointBigNumber() {
        String convertedNumber = convertToWord(0.11231231);
        assertEquals("Zero point Eleven Million Two Hundred Thirty One Thousand Two Hundred Thirty One", convertedNumber);
    }

    @Test
    public void canConvertFloatMinusTwelvePointSeventyFive() {
        String convertedNumber = convertToWord(-12.75);
        assertEquals("Minus Twelve point Seventy Five", convertedNumber);
    }

    @Test
    public void canConvertFloatMinusBigNumberPointBigNumber() {
        String convertedNumber = convertToWord(-11231232.7131235);
        assertEquals("Minus Eleven Million Two Hundred Thirty One Thousand Two Hundred Thirty Two point Seven Million One Hundred Thirty One Thousand Two Hundred Thirty Five", convertedNumber);
    }

    @Test
    public void canConvertBigNumber() {
        String convertedNumber = convertToWord(2115458978);
        assertEquals("Two Billion One Hundred Fifteen Million Four Hundred Fifty Eight Thousand Nine Hundred Seventy Eight", convertedNumber);

    }


    @Test
    public void canConvertNegativeBigNumber() {
        String convertedNumber = convertToWord(-2115458978);
        assertEquals("Minus Two Billion One Hundred Fifteen Million Four Hundred Fifty Eight Thousand Nine Hundred Seventy Eight", convertedNumber);

    }
}
