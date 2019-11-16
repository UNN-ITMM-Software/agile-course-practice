package ru.unn.agile.distanceBetweenVectors.model;

import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class TestMetrics {
    @Test
    public void canCalculateMetricOneDimensionalVector_L1() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.2f);
        float metricL1 = new VectorMetric().Calculate_L1(v);
        assertEquals(2.2f, metricL1, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVector_L1() {
        Vector<Float> v = new Vector<Float>();
        v.add(3.3f);
        v.add(-6.7f);
        float metricL1 = new VectorMetric().Calculate_L1(v);
        assertEquals(10f, metricL1, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVector_L1() {
        Vector<Float> v = new Vector<Float>();
        v.add(1.0f);
        v.add(5.8f);
        v.add(-0.3f);
        float metricL1 = new VectorMetric().Calculate_L1(v);
        assertEquals(7.1f, metricL1, 0.0001);
    }

    @Test
    public void canCalculateMetricOneDimensionalVector_L2() {
        Vector<Float> v = new Vector<Float>();
        v.add(-2.5f);
        float metricL2 = new VectorMetric().Calculate_L2(v);
        assertEquals(2.5f, metricL2, 0.0001);
    }

    @Test
    public void canCalculateMetricTwoDimensionalVector_L2() {
        Vector<Float> v = new Vector<Float>();
        v.add(1.0f);
        v.add(-2.0f);
        float metricL2 = new VectorMetric().Calculate_L2(v);
        assertEquals(2.23606797749979f, metricL2, 0.0001);
    }

    @Test
    public void canCalculateMetricThreeDimensionalVector_L2() {
        Vector<Float> v = new Vector<Float>();
        v.add(-3.0f);
        v.add(7.1f);
        v.add(0.5f);
        float metricL2 = new VectorMetric().Calculate_L2(v);
        assertEquals(7.723988606931007f, metricL2, 0.0001);
    }
}