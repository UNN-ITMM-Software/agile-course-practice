package ru.unn.agile.figuresvolumecalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.figuresvolumecalculator.model.FiguresVolumeCalculator.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty firstParameter = new SimpleStringProperty();
    private StringProperty secondParameter = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> listOfOperation =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabledFl = new SimpleBooleanProperty();

    private final StringProperty resultFld = new SimpleStringProperty();
    private final StringProperty statusFld = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        firstParameter.set("");
        secondParameter.set("");
        operation.set(Operation.CALCULATE_PYRAMID_VOLUME);
        resultFld.set("");
        statusFld.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(firstParameter, secondParameter);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabledFl.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<>() { {
            add(firstParameter);
            add(secondParameter);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (operation.get() == Operation.CALCULATE_PYRAMID_VOLUME
                || operation.get() == Operation.CALCULATE_PARALLELEPIPED_VOLUME
                || operation.get() == Operation.CALCULATE_CYLINDER_VOLUME
                || operation.get() == Operation.CALCULATE_CONE_VOLUME) {
            if (firstParameter.get().isEmpty() || secondParameter.get().isEmpty()) {
                inputStatus = Status.WAITING;
            }
            try {
                if (!firstParameter.get().isEmpty()) {
                    Double.parseDouble(firstParameter.get());
                }
                if (!secondParameter.get().isEmpty()) {
                    Double.parseDouble(secondParameter.get());
                }
            } catch (NumberFormatException nfe) {
                inputStatus = Status.BAD_FORMAT;
            }
        } else if (operation.get() == Operation.CALCULATE_SPHERE_VOLUME
                || operation.get() == Operation.CALCULATE_OCTAHEDRON_VOLUME) {
            if (firstParameter.get().isEmpty()) {
                inputStatus = Status.WAITING;
            }
            try {
                if (!firstParameter.get().isEmpty()) {
                    Double.parseDouble(firstParameter.get());
                }
            } catch (NumberFormatException nfe) {
                inputStatus = Status.BAD_FORMAT;
            }
        }


        return inputStatus;
    }

    public StringProperty firstParameterProperty() {
        return firstParameter;
    }
    public StringProperty secondParameterProperty() {
        return secondParameter;
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }
    public StringProperty resultFldProperty() {
        return resultFld;
    }
    public StringProperty statusFieldProperty() {
        return statusFld;
    }
    public BooleanProperty calculationDisabledFlProperty() {
        return calculationDisabledFl;
    }
    public final ObservableList<Operation> getListOfOperation() {
        return listOfOperation.get();
    }
    public final boolean getCalculationDisabledFl() {
        return calculationDisabledFl.get();
    }
    public final String getResultFld() {
        return resultFld.get();
    }
    public final String getStatusFld() {
        return statusFld.get();
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            statusFld.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please enter input data"),
    READY("Press 'Calculate'"),
    BAD_FORMAT("Bad format, please fix"),
    SUCCESS("Success!");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }

}
