package ru.unn.agile.numberstowords.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLoggerTests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " was not found!");
        }
    }

    @Test
    public void logFileIsEmptyInTheBeginning() {
        List<String> log = txtLogger.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void canAddLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void logMessageContainsDateAndTimeInformation() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} :\t.*"));
    }

    @Test
    public void canAddSeveralLogMessages() {
        String[] testMessages = {"Test message 1", "Test message 2"};

        txtLogger.log(testMessages[0]);
        txtLogger.log(testMessages[1]);

        List<String> log = txtLogger.getLog();
        for (int i = 0; i < log.size(); i++) {
            assertTrue(log.get(i).matches(".*" + testMessages[i] + ".*"));
        }
    }
}
