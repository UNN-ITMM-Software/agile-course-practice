package ru.unn.agile.vectorsdistancescalculator.infrastructure;

import ru.unn.agile.vectorsdistancescalculator.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final BufferedWriter bufferedWriter;
    private final String filename;

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filename));

            String messageLine = reader.readLine();
            while (messageLine != null) {
                log.add(messageLine);
                messageLine = reader.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return log;
    }

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        this.bufferedWriter = writer;
    }

    @Override
    public void log(final String message) {
        Date currentData = Calendar.getInstance().getTime();
        String currentDate = new SimpleDateFormat(DATE_FORMAT).format(currentData);

        try {
            bufferedWriter.write(currentDate + " > " + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

