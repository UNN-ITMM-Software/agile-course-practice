package ru.unn.agile.redblacktree.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.redblacktree.model.RedBlackTree;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private ILogger logger;

    private final StringProperty addField = new SimpleStringProperty();
    private final StringProperty findInsertField = new SimpleStringProperty();
    private final StringProperty removeInsertField = new SimpleStringProperty();
    private RedBlackTree rbTree = new RedBlackTree();

    private final BooleanProperty addElementToTreeDisabled = new SimpleBooleanProperty();
    private final BooleanProperty findElementInTreeDisabled = new SimpleBooleanProperty();
    private final BooleanProperty removeElementFromTreeDisabled = new SimpleBooleanProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final StringProperty resultFind = new SimpleStringProperty();
    private final StringProperty resultRemove = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public BooleanProperty addElementToTreeDisabledProperty() {
        return addElementToTreeDisabled;
    }
    public BooleanProperty findElementInTreeDisabledProperty() {
        return findElementInTreeDisabled;
    }
    public BooleanProperty removeElementFromTreeDisabledProperty() {
        return removeElementFromTreeDisabled;
    }

    public StringProperty resultFindProperty() {
        return resultFind;
    }
    public StringProperty resultRemoveProperty() {
        return resultRemove;
    }

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Invalid argument: logger is null");
        }
        this.logger = logger;

        addField.set("");
        findInsertField.set("");
        removeInsertField.set("");
        resultFind.set("");
        resultRemove.set("");

        status.set(Status.WAITING.toString());

        BooleanBinding couldAddElementToTree = new BooleanBinding() {
            {
                super.bind(addField);
            }
            @Override
            protected boolean computeValue() {
                return getInputsStatus() == Status.READY;
            }
        };
        addElementToTreeDisabled.bind(couldAddElementToTree.not());

        BooleanBinding couldFindElementInTree = new BooleanBinding() {
            {
                super.bind(findInsertField);
            }
            @Override
            protected boolean computeValue() {
                return getInputsStatus() == Status.READY;
            }
        };
        findElementInTreeDisabled.bind(couldFindElementInTree.not());

        BooleanBinding couldRemoveElementFromTree = new BooleanBinding() {
            {
                super.bind(removeInsertField);
            }
            @Override
            protected boolean computeValue() {
                return getInputsStatus() == Status.READY;
            }
        };
        removeElementFromTreeDisabled.bind(couldRemoveElementFromTree.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(addField);
            add(findInsertField);
            add(removeInsertField);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty addFieldProperty() {
        return addField;
    }
    public StringProperty findInsertFieldProperty() {
        return findInsertField;
    }
    public StringProperty removeInsertFieldProperty() {
        return removeInsertField;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputsStatus() {
        Status inputStatus = Status.READY;
        if (addField.get().isEmpty()
                && findInsertField.get().isEmpty()
                && removeInsertField.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!addField.get().isEmpty()) {
                Integer.parseInt(addField.get());
            }
            if (!findInsertField.get().isEmpty()) {
                Integer.parseInt(findInsertField.get());
            }
            if (!removeInsertField.get().isEmpty()) {
                Integer.parseInt(removeInsertField.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    public void addElementToTree() {
        if (addElementToTreeDisabled.get()) {
            return;
        }
        rbTree.insert(Integer.parseInt(addField.get()));
        status.set(Status.SUCCESS.toString());
    }

    public void findElementInTree() {
        if (findElementInTreeDisabled.get()) {
            return;
        }
        boolean answer = rbTree.find(Integer.parseInt(findInsertField.get()));
        resultFind.set(Boolean.toString(answer));
        status.set(Status.SUCCESS.toString());
    }

    public void removeElementFromTree() {
        if (removeElementFromTreeDisabled.get()) {
            return;
        }
        boolean answer = rbTree.remove(Integer.parseInt(removeInsertField.get()));
        resultRemove.set(Boolean.toString(answer));
        status.set(Status.SUCCESS.toString());
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputsStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press button"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
