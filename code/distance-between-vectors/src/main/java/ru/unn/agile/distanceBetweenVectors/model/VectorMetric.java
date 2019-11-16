package ru.unn.agile.distanceBetweenVectors.model;

import java.util.Collections;
import java.util.Vector;

public class VectorMetric {
    private float sumOfModules = 0f;

    public float calculateL1(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModules += Math.abs((float) elem));
        return sumOfModules;
    }

    public float calculateL2(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModules += Math.pow((float) elem, 2.0));
        return (float) Math.sqrt(sumOfModules);
    }

    public float calculateL3(final Vector<Float> x) {
        final float pow = 3.0f;
        x.forEach((elem) -> sumOfModules += Math.abs(Math.pow((float) elem, pow)));
        return (float) Math.pow((float) sumOfModules, 1.0 / pow);
    }

    public float calculateL4(final Vector<Float> x) {
        final float pow = 4.0f;
        x.forEach((elem) -> sumOfModules += Math.pow((float) elem, pow));
        return (float) Math.pow((float) sumOfModules, 1.0 / pow);
    }

    public float calculateLinf(final Vector<Float> x) {
        return (float) Collections.max(x);
    }
}
