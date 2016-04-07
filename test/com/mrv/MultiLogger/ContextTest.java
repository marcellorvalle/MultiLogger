package com.mrv.MultiLogger;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class ContextTest {
    private final String MESSAGE;


    public ContextTest() {
        MESSAGE = "some message";
    }

    @Test
    public void testInstantiation() {
        String session = "some_session";
        LogLevel logLevel = LogLevel.DEBUG;

        Context context = new Context(MESSAGE, logLevel, session);

        assertEquals(MESSAGE, context.getMessage());
        assertEquals(logLevel.name(), context.getLogLevel());
        assertEquals(session, context.getSession());
    }

}