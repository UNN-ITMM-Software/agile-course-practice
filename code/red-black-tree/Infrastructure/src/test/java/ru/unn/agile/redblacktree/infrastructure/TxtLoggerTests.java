package ru.unn.agile.redblacktree.infrastructure;

import org.junit.*;
import org.junit.Test;

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
}
