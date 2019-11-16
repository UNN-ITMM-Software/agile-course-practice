package ru.unn.agile.distanceBetweenVectors.model;

import java.util.Vector;

public class VectorMetric {
    private float sumOfModules = 0f;

    public float Calculate_L1(final Vector x) {
        x.forEach((elem) -> sumOfModules += Math.abs((float) elem));
        return sumOfModules;
    }

    public float Calculate_L2(final Vector x) {
        x.forEach((elem) -> sumOfModules += (float) elem*(float) elem);
        return (float)Math.sqrt(sumOfModules);
    }
}