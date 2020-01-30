package ru.unn.agile.vectorsdistancescalculator.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String NAME_OF_FILE = "./TxtLoggerTests.log";
    private static final String TEST_MESSAGE = "Test message";
    private TxtLogger logger;

    @Before
    public void setUp() {
        logger = new TxtLogger(NAME_OF_FILE);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void canCreateLoggerWithNameOfFile() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(NAME_OF_FILE));
        } catch (FileNotFoundException e) {
            fail("File " + NAME_OF_FILE + " wasn't found!");
        }
    }

    @Test
    public void canLogMessage() {
        logger.log(TEST_MESSAGE);

        String logMessage = logger.getLog().get(0);

        assertTrue(logMessage.matches(".*" + TEST_MESSAGE + "$"));
    }

    @Test
    public void canLogMessages() {
        String[] messages = {TEST_MESSAGE, TEST_MESSAGE, TEST_MESSAGE};
        for (int i = 0; i < messages.length; i++) {
            logger.log(messages[i]);
        }

        List<String> loggedMessages = logger.getLog();

        for (int i = 0; i < loggedMessages.size(); i++) {
            String loggedMessage = loggedMessages.get(i);
            assertTrue(loggedMessage.matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void logContainsDateAndTime() {
        String testMessage = "Test message for check";

        logger.log(testMessage);

        String logMessage = logger.getLog().get(0);
        assertTrue(logMessage.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

}
