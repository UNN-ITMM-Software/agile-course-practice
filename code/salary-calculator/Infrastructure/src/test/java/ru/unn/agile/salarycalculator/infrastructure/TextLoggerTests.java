package ru.unn.agile.salarycalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class TextLoggerTests {
    TextLogger textLogger;

    @Before
    public void setUp() {
        textLogger = new TextLogger();
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(textLogger);
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";
        textLogger.log(testMessage);

        List<String> actualMessages = textLogger.getLog();

        assertEquals(testMessage, actualMessages.get(0));
    }

    @Test
    public void cantWriteZeroLogMessage() {
        String testMessage = "";
        textLogger.log(testMessage);

        List<String> actualMessages = textLogger.getLog();

        assertEquals(0,actualMessages.size());
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        textLogger.log(messages[0]);
        textLogger.log(messages[1]);

        List<String> actualMessages = textLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertEquals(actualMessages.get(i), messages[i]);
        }
    }
}
