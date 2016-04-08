package com.mrv.MultiLogger;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 *
 */
public class ContextTest {
    private final String MESSAGE;
    private Context context;

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

    @Test
    public void testCanNotModifyExtraValues() {
        Context context = new Context(MESSAGE, LogLevel.DEBUG, "someSession");

        context.addExtraValue("value1", "one");
        context.addExtraValue("value2", "two");
        context.addExtraValue("value3", 3);

        HashMap<String, Object> extras = context.getExtraValues();
        extras.clear();
        assertTrue(extras.isEmpty());
        assertTrue(context.hasExtras());
        assertFalse(context.getExtraValues().isEmpty());
    }
}