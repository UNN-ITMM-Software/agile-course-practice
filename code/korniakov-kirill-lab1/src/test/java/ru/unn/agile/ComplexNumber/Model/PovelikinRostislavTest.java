package ru.unn.agile.ComplexNumber.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PovelikinRostislavTest {
    @Test
    public void canCreateDefaultComplexNumber() {
        ComplexNumber number = new ComplexNumber();
        assertNotNull(number);
    }

    @Test
    public void isDefaultComplexNumberNeutral() {
        ComplexNumber default_number = new ComplexNumber();
        ComplexNumber z = new ComplexNumber(1, 2);

        ComplexNumber sum  = z.add(default_number;)

        assertTrue(sum.equals(z));
    }
}
