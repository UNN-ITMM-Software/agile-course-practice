package ru.unn.agile.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String LOG = "./metricslog";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(LOG);
    }

    @Test
    public void isExists() {
        assertNotNull(txtLogger);
    }

    @Test
    public void createEmptyLog() {
        var log = txtLogger.getLog();
        assertEquals(log.size(), 0);
    }

    @Test
    public void canCreateLogInFolder() {
        try {
            new BufferedReader(new FileReader(LOG));
        } catch (FileNotFoundException e) {
            fail("The " + LOG + " was not founded!");
        }
    }

    @Test
    public void logInEqualLogOut() {
        final String logMessage = "AAAAA!";
        Integer sizeOfMessage = logMessage.length();
        var b = false;
        List<String> example = new ArrayList<String>();
        example.add(logMessage);
        txtLogger.log(logMessage);
        var a = txtLogger.getLog();
        var aa = a.get(0);
        aa = aa.substring(aa.length() - sizeOfMessage, aa.length());
       assertTrue(example.get(0).equals(aa));
    }

    @Test
    public void canLogTwoMessages() {
        String[] logMes = {"message 1", "message 2"};

        txtLogger.log(logMes[0]);
        txtLogger.log(logMes[1]);

        List<String> getLog = txtLogger.getLog();

        for (Integer i = 0; i < getLog.size(); i++) {
            assertTrue(getLog.get(i).matches(".*" + logMes[i]  + ".*"));
        }
    }
}
