package ru.unn.agile.vectorsdistancescalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.vectorsdistancescalculator.model.VectorsDistancesCalculator.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty x1 = new SimpleStringProperty();
    private final StringProperty y1 = new SimpleStringProperty();
    private final StringProperty z1 = new SimpleStringProperty();
    private final StringProperty x2 = new SimpleStringProperty();
    private final StringProperty y2 = new SimpleStringProperty();
    private final StringProperty z2 = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operationList =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabledFlag = new SimpleBooleanProperty();

    private final StringProperty resultField = new SimpleStringProperty();
    private final StringProperty statusField = new SimpleStringProperty();

    private List<ListenerForChangeCachingValues> valueChangedListeners;

    private ILogger logger;
    private final StringProperty logs = new SimpleStringProperty();


    private void init() {
        x1.set("");
        y1.set("");
        z1.set("");
        x2.set("");
        y2.set("");
        z2.set("");
        operation.set(Operation.CALCULATE_L1_DISTANCE);
        resultField.set("");
        statusField.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(x1, y1, z1, x2, y2, z2);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabledFlag.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<>() { {
            add(x1);
            add(y1);
            add(z1);
            add(x2);
            add(y2);
            add(z2);
        } };

        valueChangedListeners = new ArrayList<>();
        for (StringProperty field : fields) {
            final ListenerForChangeCachingValues listener = new ListenerForChangeCachingValues();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {

        setLogger(logger);
        init();
    }

    public void onOperationChanged(final Operation oldValue, final Operation newValue) {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder messageLog = new StringBuilder(LogMessages.OPERATION_WAS_CHANGED);
        messageLog.append(newValue.toString());
        logger.log(messageLog.toString());
        updateLogs();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ListenerForChangeCachingValues listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(x1.get()).append("; ")
                        .append(y1.get()).append("; ")
                        .append(z1.get()).append("; ")
                        .append(x2.get()).append("; ")
                        .append(y2.get()).append("; ")
                        .append(z2.get()).append("]");
                logger.log(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (x1.get().isEmpty() || y1.get().isEmpty()
                || z1.get().isEmpty() || x2.get().isEmpty()
                || y2.get().isEmpty() || z2.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!x1.get().isEmpty()) {
                Double.parseDouble(x1.get());
            }
            if (!y1.get().isEmpty()) {
                Double.parseDouble(y1.get());
            }
            if (!z1.get().isEmpty()) {
                Double.parseDouble(z1.get());
            }
            if (!x2.get().isEmpty()) {
                Double.parseDouble(x2.get());
            }
            if (!y2.get().isEmpty()) {
                Double.parseDouble(y2.get());
            }
            if (!z2.get().isEmpty()) {
                Double.parseDouble(z2.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    public StringProperty x1Property() {
        return x1;
    }
    public StringProperty y1Property() {
        return y1;
    }
    public StringProperty z1Property() {
        return z1;
    }
    public StringProperty x2Property() {
        return x2;
    }
    public StringProperty y2Property() {
        return y2;
    }
    public StringProperty z2Property() {
        return z2;
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }
    public StringProperty resultFieldProperty() {
        return resultField;
    }
    public StringProperty statusFieldProperty() {
        return statusField;
    }
    public BooleanProperty calculationDisabledFlagProperty() {
        return calculationDisabledFlag;
    }
    public StringProperty logsProperty() {
        return logs;
    }
    public final String getLogs() {
        return logs.get();
    }
    public final ObservableList<Operation> getOperationList() {
        return operationList.get();
    }
    public final boolean isCalculationDisabledFlag() {
        return calculationDisabledFlag.get();
    }
    public final String getResultField() {
        return resultField.get();
    }
    public final String getStatusField() {
        return statusField.get();
    }

    private class ListenerForChangeCachingValues implements ChangeListener<String> {
        private String previousValue = new String("");
        private String currentValue = new String("");
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            statusField.set(getInputStatus().toString());
            currentValue = newValue;
        }
        public boolean isChanged() {
            return !previousValue.equals(currentValue);
        }
        public void cache() {
            previousValue = currentValue;
        }
    }

    public void calculate() {
        if (calculationDisabledFlag.get()) {
            return;
        }
        double x1d = Double.parseDouble(x1.get());
        double y1d = Double.parseDouble(y1.get());
        double z1d = Double.parseDouble(z1.get());
        double x2d = Double.parseDouble(x2.get());
        double y2d = Double.parseDouble(y2.get());
        double z2d = Double.parseDouble(z2.get());
        List<Double> x = new ArrayList<>(List.of(x1d, y1d, z1d));
        List<Double> y = new ArrayList<>(List.of(x2d, y2d, z2d));

        var result = String.valueOf(operation.get().apply(x, y));

        resultField.set(result);
        statusField.set(Status.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogMessages.CALCULATE_WAS_PRESSED);
        message.append("Arguments: X1 = ").append(x1.get())
                .append("; Y1 = ").append(y1.get())
                .append("; Z1 = ").append(z1.get())
                .append("; X2 = ").append(x2.get())
                .append("; Y2 = ").append(y2.get())
                .append("; Z2 = ").append(z2.get())
                .append(" Operation: ").append(operation.get().toString()).append(".");
        logger.log(message.toString());
        updateLogs();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
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

enum Status {
    WAITING("Please enter input data"),
    READY("Press 'Calculate'"),
    BAD_FORMAT("Bad format, please fix"),
    SUCCESS("Success!");

    private final String tittle;
    Status(final String name) {
        this.tittle = name;
    }
    public String toString() {
        return tittle;
    }
}


final class LogMessages {
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LogMessages() { }
}
