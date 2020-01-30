package ru.unn.agile.numberstowords.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.numberstowords.model.NumbersToWordsConverter;

import java.util.List;


public class ViewModel {
    private final StringProperty numberInput = new SimpleStringProperty();
    private final StringProperty textOutput = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private ILogger logger;

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        init();
        setLogger(logger);
    }

    private void init() {
        numberInput.set("");
        textOutput.set("");
        status.set("");
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger can't be null");
        }
        this.logger = logger;
    }

    public StringProperty numberInputProperty() {
        return numberInput;
    }

    public StringProperty textOutputProperty() {
        return textOutput;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private static boolean isNumeric(final String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String convertLogMessage() {
        return LogMessages.CONVERT_WAS_PRESSED
                + "Input: \"" + numberInput.get() + "\".";
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = "";
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    public void convert() {
        logger.log(convertLogMessage());
        updateLogs();

        if (numberInput.get().equals("")) {
            status.set(Status.EMPTY_INPUT.toString());
            logger.log(LogMessages.EMPTY_INPUT.toString());
            updateLogs();
        } else if (!isNumeric(numberInput.get())) {
            status.set(Status.WRONG_INPUT.toString());
            logger.log(LogMessages.WRONG_INPUT.toString());
            updateLogs();
        } else {
            int number = Integer.parseInt(numberInput.get());
            status.set("");
            textOutput.set(NumbersToWordsConverter.toWord(number));
            logger.log(LogMessages.CONVERT_WAS_COMPLETED.toString());
            updateLogs();
        }
    }
}

enum Status {
    EMPTY_INPUT("Please provide input data"),
    WRONG_INPUT("Please provide correct input data");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

enum LogMessages {
    CONVERT_WAS_PRESSED("Convert was pressed."),
    CONVERT_WAS_COMPLETED("The conversion was completed."),
    EMPTY_INPUT("It is impossible to convert. Reason: empty input."),
    WRONG_INPUT("It is impossible to convert. Reason: wrong input.");

    private final String name;
    LogMessages(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
