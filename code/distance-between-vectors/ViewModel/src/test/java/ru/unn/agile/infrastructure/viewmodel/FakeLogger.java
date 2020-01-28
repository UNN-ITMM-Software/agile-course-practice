package ru.unn.agile.infrastructure.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<String>();

    @Override
    public void log(final String st) {
        log.add(st);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
