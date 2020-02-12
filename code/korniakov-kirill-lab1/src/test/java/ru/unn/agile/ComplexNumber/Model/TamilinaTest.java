package ru.unn.agile.ComplexNumber.Model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.*;

public class TamilinaTest {

     @Test
    public void CreateComplexNumber() {
        ComplexNumber z = new ComplexNumber();
        ComplexNumber z1 = new ComplexNumber(0, 0);
        assertEquals(z, z1);
    }

    @Test
  public void AddNumbers() {
        ComplexNumber z1 = new ComplexNumber(7, -2);
        ComplexNumber z2 = new ComplexNumber(1, -4);
        ComplexNumber sum = z1.add(z2);
        assertTrue(sum.equals(new ComplexNumber(8, -6)));
    }
}
