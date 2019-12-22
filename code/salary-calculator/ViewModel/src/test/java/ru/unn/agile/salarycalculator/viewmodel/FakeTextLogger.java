package ru.unn.agile.salarycalculator.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeTextLogger implements ILogger {
    ArrayList<String> logsList;

    public FakeTextLogger() {
    }

    @Override
    public void log(final String s) {
    }

    @Override
    public List<String> getLog() {
        return logsList;
    }
}