package ru.unn.agile.salarycalculator.infrastructure;

import ru.unn.agile.salarycalculator.viewmodel.legacy.ILogger;

import java.util.Arrays;
import java.util.List;

public class TextLogger implements ILogger {
    List<String> logsList;

    public TextLogger() {
    }

    @Override
    public void log(final String s) {
        logsList.add(s);
    }

    @Override
    public List<String> getLog() {
        return logsList;
    }
}
