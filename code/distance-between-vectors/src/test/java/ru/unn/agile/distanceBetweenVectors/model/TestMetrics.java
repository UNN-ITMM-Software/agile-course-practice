package ru.unn.agile.distanceBetweenVectors.model;

import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class TestMetrics {
    @Test
    public void canCalculateMetricOneDimensionalVectorL1() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.2f);
        float metricL1 = new VectorMetric().calculateL1(v);
        assertEquals(2.2f, metricL1, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL1() {
        Vector<Float> v = new Vector<Float>();
        v.add(3.3f);
        v.add(-6.7f);
        float metricL1 = new VectorMetric().calculateL1(v);
        assertEquals(10f, metricL1, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL1() {
        Vector<Float> v = new Vector<Float>();
        v.add(1.0f);
        v.add(5.8f);
        v.add(-0.3f);
        float metricL1 = new VectorMetric().calculateL1(v);
        assertEquals(7.1f, metricL1, 0.0001);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorL2() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.5f);
        float metricL2 = new VectorMetric().calculateL2(v);
        assertEquals(2.5f, metricL2, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL2() {
        Vector<Float> v = new Vector<Float>();
        v.add(1.0f);
        v.add(-2.0f);
        float metricL2 = new VectorMetric().calculateL2(v);
        assertEquals(2.23606797749979f, metricL2, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL2() {
        Vector<Float> v = new Vector<Float>();
        v.add(-3.0f);
        v.add(7.1f);
        v.add(0.5f);
        float metricL2 = new VectorMetric().calculateL2(v);
        assertEquals(7.723988606931007f, metricL2, 0.0001);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorL3() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.0f);
        float metricL3 = new VectorMetric().calculateL3(v);
        assertEquals(2.0f, metricL3, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL3() {
        Vector<Float> v = new Vector<Float>();
        v.add(2.0f);
        v.add(-3.0f);
        float metricL3 = new VectorMetric().calculateL3(v);
        assertEquals(3.2710663101886f, metricL3, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL3() {
        Vector<Float> v = new Vector<Float>();
        v.add(2.0f);
        v.add(2.5f);
        v.add(-4.0f);
        float metricL3 = new VectorMetric().calculateL3(v);
        assertEquals(4.4416330599643f, metricL3, 0.0001);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorL4() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.0f);
        float metricL4 = new VectorMetric().calculateL4(v);
        assertEquals(2.0f, metricL4, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorL4() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.0f);
        v.add(1.0f);
        float metricL4 = new VectorMetric().calculateL4(v);
        assertEquals(2.0305431848689f, metricL4, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorL4() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.0f);
        v.add(-2.0f);
        v.add(-2.1f);
        float metricL4 = new VectorMetric().calculateL4(v);
        assertEquals(2.678195865012f, metricL4, 0.0001);
    }

    @Test
    public void canCalculateMetricOneDimensionalVectorLinf() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.0f);
        float metricLinf = new VectorMetric().calculateLinf(v);
        assertEquals(-2.0f, metricLinf, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVectorLinf() {
        Vector<Float> v = new Vector<Float>();
        v.add(4.0f);
        v.add(-100.0f);
        float metricLinf = new VectorMetric().calculateLinf(v);
        assertEquals(4.0f, metricLinf, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVectorLinf() {
        Vector<Float> v = new Vector<Float>();
        v.add(-55.0f);
        v.add(-2.0f);
        v.add(150.1f);
        float metricLinf = new VectorMetric().calculateLinf(v);
        assertEquals(150.1f, metricLinf, 0.0001);
    }
}

