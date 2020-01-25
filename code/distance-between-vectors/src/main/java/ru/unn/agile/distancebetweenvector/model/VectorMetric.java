package ru.unn.agile.distancebetweenvector.model;

import java.util.Vector;

public class VectorMetric {
    private float sumOfModulesL1 = 0f;
    private float sumOfModulesL2 = 0f;
    public float calculateL1(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModulesL1 += Math.abs((float) elem));
        return sumOfModulesL1;
    }

    public float calculateL2(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModulesL2 += Math.pow((float) elem, 2.0));
        return (float) Math.sqrt(sumOfModulesL2);
    }
}



