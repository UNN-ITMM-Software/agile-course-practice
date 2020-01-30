package ru.unn.agile.numberstowords.infrastructure;

import ru.unn.agile.numberstowords.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private BufferedWriter writer = null;
    private final String fileName;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDateNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String logFileLine = reader.readLine();

            while (logFileLine != null) {
                log.add(logFileLine);
                logFileLine = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    @Override
    public void log(final String s) {
        try {
            String logMessage = getDateNow() + " :\t" + s + "\n";
            writer.write(logMessage);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
