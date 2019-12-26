package ru.unn.agile.ComplexNumber.Model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.assertEquals;

public class AkhmedovSarkarTest {

    @Test
    public void canSumNumbers() {
        ComplexNumber с1 = new ComplexNumber(1.3, 0.45);
        ComplexNumber с2 = new ComplexNumber(10, 5.5);
        ComplexNumber sum = с1.add(с2);
        assertEquals(sum, new ComplexNumber(11.3, 5.95));
    }

    @Test
    public void canSumNumbersWithNegatuivePart() {
        ComplexNumber с1 = new ComplexNumber(1.3, -0.45);
        ComplexNumber с2 = new ComplexNumber(-10, 5.5);
        ComplexNumber sum = с1.add(с2);
        assertEquals(sum, new ComplexNumber(-8.7, 5.05));
    }

    @Test
    public void cantSumNumbersWithDiffInitialization() {
        ComplexNumber с1 = new ComplexNumber("1", "1");
        ComplexNumber с2 = new ComplexNumber(2, 5);
        ComplexNumber sum = с1.add(с2);
        assertEquals(sum, new ComplexNumber(3, 6));
    }
}
