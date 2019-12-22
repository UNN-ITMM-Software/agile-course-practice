package ru.unn.agile.salarycalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
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

        assertTrue(true);
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

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        textLogger.log(testMessage);

        String message = textLogger.getLog().get(0);
        assertEquals(message, "");
    }
}
