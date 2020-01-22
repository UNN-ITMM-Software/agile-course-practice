package ru.unn.agile.redblacktree.viewmodel;

import java.util.List;

public interface ILogger {
    void addLog(String message);

    List<String> getLogMessages();
}

final class LogPrefix {
    public static final String ELEMENT_ADDED = "[Element added] ";
}