package ru.unn.agile.numberstowords.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void log(final String logMessage) {
        log.add(logMessage);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
