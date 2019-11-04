package ru.unn.agile.StatisticsCalculation.Model;

import org.junit.Test;
import ru.unn.agile.StatisticsCalculation.model.StatisticsCalculation;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StatisticsCalculationTest {
    private final double delta = 0.001;

    @Test
    public void canCalculateExpectedValueForConstantOne() {
        Integer[] values = {1};
        double[] probabilities = {1.0};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 1.0;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForConstantTwo() {
        Integer[] values = {2};
        double[] probabilities = {1.0};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 2.0;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForConstantMinusOne() {
        Integer[] values = {-1};
        double[] probabilities = {1.0};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = -1.0;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForTwoDimValues() {
        Integer[] values = {-1 , 3};
        double[] probabilities = {0.5 , 0.5};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 1.0;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForThreeDimValues() {
        Integer[] values = {-1 , 3, 5};
        double[] probabilities = {0.2 , 0.3, 0.5};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 3.2;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForEmptyValues() {
        Integer[] values = {};
        double[] probabilities = {};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 0.0;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForDouble() {
        Double[] values = {-1.0,  3.0};
        double[] probabilities = {0.2, 0.8};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 2.2;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    @Test
    public void canCalculateExpectedValueForDoubleAndInteger() {
        Number[] values = {-1.0,  3, 5.0};
        double[] probabilities = {0.2, 0.4, 0.4};
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 3.0;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }

    private Number[] createSequentialValues(int size){
        Number [] values = new Number[size];
        for (int i=0;i<size;i++){
            values[i]=i;
        }
        return values;
    }

    @Test
    public void canCalculateExpectedValueForBigDimValues() {
        Number[] values = createSequentialValues(1000);
        double[] probabilities = new double[1000];
        Arrays.fill(probabilities, 0.001);
        StatisticsCalculation calculator = new StatisticsCalculation();
        double expectedValue = 499.5;

        double realExpectedValue = calculator.calculateExpectedValue (values, probabilities);

        assertEquals(expectedValue, realExpectedValue, delta);
    }
}