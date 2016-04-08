package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.Context;
import com.mrv.MultiLogger.LogLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Marcello on 06/04/2016.
 */
public class ConsoleDeviceTest {
    private final ConsoleDevice device = spy(new ConsoleDevice(LogLevel.INFO));
    private final Context context = mock(Context.class);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final HashMap<String,Object> EXTRAS = buildExtras();
    private final String MESSAGE = "someMessage";
    private final String SESSION = "someSession";
    private final String LOGLEVEL = "someLogLevel";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testDoLog() throws IOException {
        stubContext();
        device.doLog(context);

        String consoleOutput = outContent.toString();
        outContent.close();

        assertTrue(consoleOutput.contains(MESSAGE));
        assertTrue(consoleOutput.contains(SESSION));
        assertTrue(consoleOutput.contains(LOGLEVEL));
        assertExtrasOnString(consoleOutput);
    }

    private void stubContext() {
        when(context.getDateTime()).thenReturn(new Date());
        when(context.getLogLevel()).thenReturn(LOGLEVEL);
        when(context.getMessage()).thenReturn(MESSAGE);
        when(context.getSession()).thenReturn(SESSION);
        when(context.getExtraValues()).thenReturn(EXTRAS);
        when(context.hasExtras()).thenReturn(true);
    }

    @Test
    public void testSerializeExtraValues() {
        HashMap<String,Object> extras = buildExtras();
        String extraText = device.serializeExtraValues(extras);
        assertExtrasOnString(extraText);
    }

    private HashMap<String, Object> buildExtras() {
        HashMap<String,Object> extras = new LinkedHashMap<>();
        extras.put("key1", "value1");
        extras.put("key2", "value2");

        return extras;
    }

    private void assertExtrasOnString(String output) {
        assertTrue(output.contains("key1"));
        assertTrue(output.contains("value1"));
        assertTrue(output.contains("key2"));
        assertTrue(output.contains("value2"));
    }

}