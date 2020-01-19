package ru.unn.agile.redblacktree.viewmodel;

import java.io.IOException;
import java.util.List;

public interface ILogger {
    void addLog(String message);

    List<String> getLogMessages();
}