
package ru.unn.agile.ComplexNumber.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AntipinNikitaTest {
    @Test
    public void canMultiplyTwoComplexNumbers() {
        ComplexNumber complexNumber1 = new ComplexNumber(3, 4);
        ComplexNumber complexNumber2 = new ComplexNumber(2, 3);
        ComplexNumber multiplyComplexNumbers = complexNumber1.multiply(complexNumber2);
        assertEquals(new ComplexNumber(-6, 17), multiplyComplexNumbers);
    }

    @Test
    public void canAddTwoComplexNumbers() {
        ComplexNumber complexNumber1 = new ComplexNumber(2, 2);
        ComplexNumber complexNumber2 = new ComplexNumber(1, 6);
        ComplexNumber addComplexNumbers = complexNumber1.add(complexNumber2);
        assertEquals(new ComplexNumber(3, 8), addComplexNumbers);
    }

    @Test
    public void twoEqualNumbersAreEqual() {
        ComplexNumber complexNumber1 = new ComplexNumber(2.19, 1.22);
        ComplexNumber complexNumber2 = new ComplexNumber(2.19, 1.22);
        assertTrue(complexNumber1.equals(complexNumber2));
    }
}
