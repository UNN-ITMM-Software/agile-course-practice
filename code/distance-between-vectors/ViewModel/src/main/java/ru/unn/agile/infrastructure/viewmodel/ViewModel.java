package ru.unn.agile.infrastructure.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.infrastructure.model.VectorMetric;

import java.util.List;
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
    private final StringProperty logs = new SimpleStringProperty();
    private ILogger logger;

    public final void setLogger(final ILogger log) {
        if (null == log) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = log;
    }

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger log) {
        setLogger(log);
        init();
    }

    private void init() {
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
        try {
            x = Float.parseFloat(coordX.get());
            y = Float.parseFloat(coordY.get());
            z = Float.parseFloat(coordZ.get());
        } catch (Exception e) {
            message.set("Пожалуйста, введите числа");
            resultL1.set("ERROR");
            resultL2.set("ERROR");
            resultL3.set("ERROR");
            resultL4.set("ERROR");
            resultLinf.set("ERROR");

            StringBuilder mess = new StringBuilder();
            mess.append("INCORRECT VALUE OF COORDINATES");
            logger.log(mess.toString());
            updateLogs();
            return false;
        }
        message.set("");
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

        StringBuilder mess = new StringBuilder(LogMessages.CALCULATE_BTN_PRESSED_CORRECT);
        mess.append("x = ").append(coordX.get())
                .append("; y = ").append(coordY.get())
                .append("; z = ").append(coordZ.get());
        logger.log(mess.toString());
        updateLogs();
        return true;
    }

    public List<String> getLog() {
        return logger.getLog();
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
    public StringProperty messageProperty() {
        return message;
    }
    public final String getMessage() {
        return message.get();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }

    final class LogMessages {
        public static final String CALCULATE_BTN_PRESSED_CORRECT = "Calculate. ";
        public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";

        private LogMessages() { }
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String("");
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }
}







