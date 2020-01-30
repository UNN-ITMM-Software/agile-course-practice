package ru.unn.agile.vectorsdistancescalculator.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String message);

    List<String> getLog();
}
