package ru.unn.agile.redblacktree.infrastructure;

import org.junit.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.addLog(testMessage);
    }

    @Test
    public void canGetLogMessages() {
        assertNotNull(txtLogger.getLogMessages());
    }

    @Test
    public void onDefaultLogMessagesEmpty() {
        assertEquals(txtLogger.getLogMessages().size(), 0);
    }
}
