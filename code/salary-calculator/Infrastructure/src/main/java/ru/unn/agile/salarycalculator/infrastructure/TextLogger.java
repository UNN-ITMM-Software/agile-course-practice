package ru.unn.agile.salarycalculator.infrastructure;

import ru.unn.agile.salarycalculator.viewmodel.ILogger;

import java.util.ArrayList;
import java.util.List;

public class TextLogger implements ILogger {
    ArrayList<String> logsList;

    public TextLogger() {
        logsList = new ArrayList<String>();
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
