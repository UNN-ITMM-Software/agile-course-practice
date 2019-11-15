package ru.unn.agile.ComplexNumber.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class KiselevaOlgaTest {
    @Test
    public void canCreateZeroComplexNumber() {
        ComplexNumber z = new ComplexNumber();

        assertEquals(new ComplexNumber(0, 0), z);
    }

    @Test
    public void canCreateComplexNumberFromString() {
        ComplexNumber z1 = new ComplexNumber("5", "6");
        ComplexNumber z2 = new ComplexNumber(5, 6);

        assertTrue(z1.equals(z2));
    }
}
