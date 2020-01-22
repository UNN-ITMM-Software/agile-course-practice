package ru.unn.agile.redblacktree.infrastructure;

import ru.unn.agile.redblacktree.viewmodel.ILogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtLogger implements ILogger {
    private final String fileName;
    private BufferedWriter writer = null;

    public TxtLogger(final String filename) {
        this.fileName = filename;

        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLog(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLogMessages() {
        return null;
    }
}
