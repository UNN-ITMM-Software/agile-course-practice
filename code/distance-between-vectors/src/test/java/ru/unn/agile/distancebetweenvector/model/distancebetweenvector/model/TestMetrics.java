package ru.unn.agile.distancebetweenvector.model.distancebetweenvector.model;

import org.junit.Test;
import ru.unn.agile.distancebetweenvector.model.VectorMetric;

import java.util.Vector;

import static org.junit.Assert.*;

public class TestMetrics {
    private final double comparisonDELTA = 0.0001;
    @Test
    public void canCalculateMetricOneDimensionalVectorL1() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.2f;
        final float expectedL1 = 2.2f;
        v.add(coordX);
        float metricL1 = new VectorMetric().calculateL1(v);
        assertEquals(expectedL1, metricL1, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL1() {
        final float coordX = 3.3f;
        final float coordY = -6.7f;
        final float expectedL1 = 10f;
        Vector<Float> v = new Vector<Float>();
        v.add(coordX);
        v.add(coordY);
        float metricL1 = new VectorMetric().calculateL1(v);
        assertEquals(expectedL1, metricL1, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL1() {
        final float coordX = 1.0f;
        final float coordY = 5.8f;
        final float coordZ = -0.3f;
        final float expectedL1 = 7.1f;
        Vector<Float> v = new Vector<Float>();
        v.add(coordX);
        v.add(coordY);
        v.add(coordZ);
        float metricL1 = new VectorMetric().calculateL1(v);
        assertEquals(expectedL1, metricL1, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorL2() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.5f;
        final float expectedL2 = 2.5f;
        v.add(coordX);
        float metricL2 = new VectorMetric().calculateL2(v);
        assertEquals(expectedL2, metricL2, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL2() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = 1.0f;
        final float coordY = -2.0f;
        final float expectedL2 = 2.23606797749979f;
        v.add(coordX);
        v.add(coordY);
        float metricL2 = new VectorMetric().calculateL2(v);
        assertEquals(expectedL2, metricL2, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL2() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -3.0f;
        final float coordY = 7.1f;
        final float coordZ = 0.5f;
        final float expectedL2 = 7.723988606931007f;
        v.add(coordX);
        v.add(coordY);
        v.add(coordZ);
        float metricL2 = new VectorMetric().calculateL2(v);
        assertEquals(expectedL2, metricL2, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorL3() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.0f;
        final float expectedL3 = 2.0f;
        v.add(coordX);
        float metricL3 = new VectorMetric().calculateL3(v);
        assertEquals(expectedL3, metricL3, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL3() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = 2.0f;
        final float coordY = -3.0f;
        final float expectedL3 = 3.2710663101886f;
        v.add(coordX);
        v.add(coordY);
        float metricL3 = new VectorMetric().calculateL3(v);
        assertEquals(expectedL3, metricL3, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL3() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = 2.0f;
        final float coordY = 2.5f;
        final float coordZ = -4.0f;
        final float expectedL3 = 4.4416330599643f;
        v.add(coordX);
        v.add(coordY);
        v.add(coordZ);
        float metricL3 = new VectorMetric().calculateL3(v);
        assertEquals(expectedL3, metricL3, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorL4() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.0f;
        final float expectedL4 = 2.0f;
        v.add(coordX);
        float metricL4 = new VectorMetric().calculateL4(v);
        assertEquals(expectedL4, metricL4, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL4() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.0f;
        final float coordY = 1.0f;
        final float expectedL4 = 2.0305431848689f;
        v.add(coordX);
        v.add(coordY);
        float metricL4 = new VectorMetric().calculateL4(v);
        assertEquals(expectedL4, metricL4, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL4() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.0f;
        final float coordY = -2.0f;
        final float coordZ = -2.1f;
        final float expectedL4 = 2.678195865012f;
        v.add(coordX);
        v.add(coordY);
        v.add(coordZ);
        float metricL4 = new VectorMetric().calculateL4(v);
        assertEquals(expectedL4, metricL4, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorLinf() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -2.0f;
        final float expectedLinf = -2.0f;
        v.add(coordX);
        float metricLinf = new VectorMetric().calculateLinf(v);
        assertEquals(expectedLinf, metricLinf, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorLinf() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = 4.0f;
        final float coordY = -100.0f;
        final float expectedLinf = 4.0f;
        v.add(coordX);
        v.add(coordY);
        float metricLinf = new VectorMetric().calculateLinf(v);
        assertEquals(expectedLinf, metricLinf, comparisonDELTA);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorLinf() {
        Vector<Float> v = new Vector<Float>();
        final float coordX = -55.0f;
        final float coordY = -2.0f;
        final float coordZ = 150.1f;
        final float expectedLinf = 150.1f;
        v.add(coordX);
        v.add(coordY);
        v.add(coordZ);
        float metricLinf = new VectorMetric().calculateLinf(v);
        assertEquals(expectedLinf, metricLinf, comparisonDELTA);
    }
}





