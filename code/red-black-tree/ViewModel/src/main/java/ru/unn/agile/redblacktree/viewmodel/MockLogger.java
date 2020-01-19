package ru.unn.agile.redblacktree.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MockLogger implements ILogger {
    private final ArrayList<String> logs = new ArrayList<>();

    @Override
    public void addLog(final String s) {
        logs.add(s);
    }

    @Override
    public List<String> getLogMessages() {
        return logs;
    }
}