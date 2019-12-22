package ru.unn.agile.salarycalculator.infrastructure;

import ru.unn.agile.salarycalculator.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TextLogger implements ILogger {
    private final ArrayList<String> logsList;
    private final String filename;
    private final BufferedWriter writer;

    public TextLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;

        logsList = new ArrayList<String>();
    }

    @Override
    public void log(final String s) {
        try {
            if (!s.isEmpty()) {
                writer.write(" > " + s);
                writer.newLine();
                writer.flush();

                logsList.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        return logsList;
    }
}
