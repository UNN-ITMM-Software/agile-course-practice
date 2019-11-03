package ru.unn.agile.ComplexNumber.Model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.*;

public class OvcharukOlegTest {

    private final double delta = 1e-3;

    @Test
    public void createsWithCorrectDoubleParams() {
        var complexNumber = new ComplexNumber(1.2, 3.4);

        assertNotNull(complexNumber);
    }

    @Test
    public void createsWithCorrectStringParams() {
        var complexNumber = new ComplexNumber("1.2", "3.4");

        assertNotNull(complexNumber);
    }

    @Test(expected = NumberFormatException.class)
    public void throwsWithIncorrectParams() {
        new ComplexNumber("Incorrect", "Params");
    }

    @Test
    public void returnsCorrectRealPart() {
        var complexNumber = new ComplexNumber(1.2, 3.4);

        var expectedRealPart = 1.2;
        assertEquals(expectedRealPart, complexNumber.getReal(), delta);
    }

    @Test
    public void returnsCorrectImaginaryPart() {
        var complexNumber = new ComplexNumber(1.2, 3.4);

        var expectedImaginaryPart = 3.4;
        assertEquals(expectedImaginaryPart, complexNumber.getImaginary(), delta);
    }

    @Test
    public void equalsWithSameComplexNumber() {
        var complexNumber1 = new ComplexNumber(1.2, 3.4);
        var complexNumber2 = new ComplexNumber(1.2, 3.4);

        assertEquals(complexNumber1, complexNumber2);
    }

    @Test
    public void formatsToString() {
        var complexNumber = new ComplexNumber(1.2, 3.4);

        var expectedString = "1.2 + 3.4i";
        assertEquals(expectedString, complexNumber.toString());
    }

    @Test
    public void notEqualsWithDifferentComplexNumber() {
        var complexNumber1 = new ComplexNumber(1.2, 3.4);
        var complexNumber2 = new ComplexNumber(5.6, 7.8);

        assertNotEquals(complexNumber1, complexNumber2);
    }

    @Test
    public void addsTwoComplexNumbers() {
        var complexNumber1 = new ComplexNumber(1.2, 3.4);
        var complexNumber2 = new ComplexNumber(5.6, 7.8);

        var sum = complexNumber1.add(complexNumber2);

        var expectedSum = new ComplexNumber(6.8, 11.2);
        assertEquals(expectedSum, sum);
    }

    @Test
    public void multipliesTwoComplexNumbers() {
        var complexNumber1 = new ComplexNumber(1.2, 3.4);
        var complexNumber2 = new ComplexNumber(5.6, 7.8);

        var product = complexNumber1.multiply(complexNumber2);

        var expectedProduct = new ComplexNumber(-19.8, 28.4);
        assertEquals(expectedProduct, product);
    }
}
