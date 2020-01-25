package ru.unn.agile.distancebetweenvector.model;

import java.util.Vector;

public class VectorMetric {
    private float sumOfModulesL1 = 0f;
    public float calculateL1(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModulesL1 += Math.abs((float) elem));
        return sumOfModulesL1;
    }
}


