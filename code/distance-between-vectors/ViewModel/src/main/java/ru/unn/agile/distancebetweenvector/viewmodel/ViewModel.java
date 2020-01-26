package ru.unn.agile.distancebetweenvector.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.distancebetweenvector.model.VectorMetric;

import java.util.Vector;

public class ViewModel {
    private final StringProperty coordX = new SimpleStringProperty();
    private final StringProperty coordY = new SimpleStringProperty();
    private final StringProperty coordZ = new SimpleStringProperty();
    private final StringProperty resultL1 = new SimpleStringProperty();
    private final StringProperty resultL2 = new SimpleStringProperty();
    private final StringProperty resultL3 = new SimpleStringProperty();
    private final StringProperty resultL4 = new SimpleStringProperty();
    private final StringProperty resultLinf = new SimpleStringProperty();
    private final StringProperty message = new SimpleStringProperty();

    public ViewModel() {
        coordX.set("");
        coordY.set("");
        coordZ.set("");
        resultL1.set("");
        resultL2.set("");
        resultL3.set("");
        resultL4.set("");
        resultLinf.set("");
        message.set("");
    }

    public boolean calculate() {
        Vector<Float> v = new Vector<>();
        Float x, y, z;

        x = Float.parseFloat(coordX.get());
        y = Float.parseFloat(coordY.get());
        z = Float.parseFloat(coordZ.get());

        v.add(x);
        v.add(y);
        v.add(z);
        float metricL1 = new VectorMetric().calculateL1(v);
        float metricL2 = new VectorMetric().calculateL2(v);
        float metricL3 = new VectorMetric().calculateL3(v);
        float metricL4 = new VectorMetric().calculateL4(v);
        float metricLinf = new VectorMetric().calculateLinf(v);

        resultL1.set(String.valueOf(metricL1));
        resultL2.set(String.valueOf(metricL2));
        resultL3.set(String.valueOf(metricL3));
        resultL4.set(String.valueOf(metricL4));
        resultLinf.set(String.valueOf(metricLinf));
        return true;
    }

    public StringProperty coordXProperty() {
        return coordX;
    }
    public StringProperty coordYProperty() {
        return coordY;
    }
    public StringProperty coordZProperty() {
        return coordZ;
    }

    public StringProperty resultL1Property() {
        return resultL1;
    }
    public final String getResultL1() {
        return resultL1.get();
    }
    public StringProperty resultL2Property() {
        return resultL2;
    }
    public final String getResultL2() {
        return resultL2.get();
    }
    public StringProperty resultL3Property() {
        return resultL3;
    }
    public final String getResultL3() {
        return resultL3.get();
    }
    public StringProperty resultL4Property() {
        return resultL4;
    }
    public final String getResultL4() {
        return resultL4.get();
    }
    public StringProperty resultLinfProperty() {
        return resultLinf;
    }
    public final String getResultLinf() {
        return resultLinf.get();
    }
}







