package ru.unn.agile.gameoflife.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class TextLoggerTests {
    private static final String FILENAME = "./TxtLogger_gameoflife.log";
    private TextLogger textLogger;

    @Before
    public void setUp() {
        textLogger = new TextLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(textLogger);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " not found!");
        }
    }

    @Test
        public void canWriteLogMessage() {
        String testMessage = "Test msg";

        textLogger.log(testMessage);

        String message = textLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] messages = {"Test msg 1", "Test msg 2"};

        textLogger.log(messages[0]);
        textLogger.log(messages[1]);

        List<String> currentMessages = textLogger.getLog();
        for (int i = 0; i < currentMessages.size(); i++) {
            assertTrue(currentMessages.get(i).matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void areLogContainDateAndTime() {
        String testMessage = "Test msg";

        textLogger.log(testMessage);

        String message = textLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} >>> .*"));
    }
}
