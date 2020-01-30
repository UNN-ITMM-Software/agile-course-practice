package ru.unn.agile.gameoflife.infrastructure;

import ru.unn.agile.gameoflife.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TextLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String filename;

    public TextLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    private static String getDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(getDateTime() + " >>> " + s + "\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufReader;
        ArrayList<String> logList = new ArrayList<String>();

        try {
            bufReader = new BufferedReader(new FileReader(filename));
            String logLine = bufReader.readLine();

            while (logLine != null) {
                logList.add(logLine);
                logLine = bufReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logList;
    }

}
