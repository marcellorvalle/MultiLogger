package com.mrv.MultiLogger;

import java.util.*;
import com.mrv.MultiLogger.Devices.Device;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Marcello on 06/04/2016.
 */
public class MultiLoggerImplTest {
    private final MultiLoggerImpl logger;
    private final List<Device> devices;

    private final String MESSAGE;
    private final Throwable THROWABLE;

    public MultiLoggerImplTest() {
        logger = spy(new MultiLoggerImpl());
        MESSAGE = "Some message";
        THROWABLE = new Throwable(MESSAGE);
        devices = new ArrayList<>();
    }

    private void initializeDevices() {
        final int QUANT = 5;
        for (int i = 0; i < QUANT; i++) {
            Device device = mock(Device.class);
            logger.addDevice(device);
            devices.add(device);
        }
    }

    @Test
    public void testLogFatalMessage() {
        logger.logFatal(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.FATAL);
    }

    @Test
    public void testLogFatalThrowable() {
        logger.logFatal(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.FATAL);
    }

    @Test
    public void testLogCriticalMessage() {
        logger.logCritical(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.CRITICAL);
    }

    @Test
    public void testLogCriticalThrowable() {
        logger.logCritical(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.CRITICAL);
    }

    @Test
    public void testLogErrorMessage() {
        logger.logError(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.ERROR);
    }

    @Test
    public void testLogErrorThrowable() {
        logger.logError(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.ERROR);
    }

    @Test
    public void testLogWarningMessage() {
        logger.logWarning(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.WARNING);
    }

    @Test
    public void testLogWarningThrowable() {
        logger.logWarning(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.WARNING);
    }

    @Test
    public void testLogMessageMessage() {
        logger.logMessage(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.MESSAGE);
    }

    @Test
    public void testLogMessageThrowable() {
        logger.logMessage(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.MESSAGE);
    }

    @Test
    public void testLogInfoMessage() {
        logger.logInfo(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.INFO);
    }

    @Test
    public void testLogInfoThrowable() {
        logger.logInfo(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.INFO);
    }

    @Test
    public void testLogDebugMessage() {
        logger.logDebug(MESSAGE);
        verify(logger).log(MESSAGE, LogLevel.DEBUG);
    }

    @Test
    public void testLogDebugThrowable() {
        logger.logDebug(THROWABLE);
        verify(logger).log(MESSAGE, LogLevel.DEBUG);
    }

    @Test
    public void testLogCallBuildContext() {
        logger.logDebug(THROWABLE);
        verify(logger).buildContext(MESSAGE, LogLevel.DEBUG);
    }


    @Test
    public void testLogCallsDevices() {
        initializeDevices();
        assertAllDevicesHasBeenCalled(LogLevel.FATAL);
        assertAllDevicesHasBeenCalled(LogLevel.CRITICAL);
        assertAllDevicesHasBeenCalled(LogLevel.ERROR);
        assertAllDevicesHasBeenCalled(LogLevel.WARNING);
        assertAllDevicesHasBeenCalled(LogLevel.MESSAGE);
        assertAllDevicesHasBeenCalled(LogLevel.INFO);
        assertAllDevicesHasBeenCalled(LogLevel.DEBUG);
    }

    private void assertAllDevicesHasBeenCalled(LogLevel logLevel) {
        Context context = createContext();
        when(logger.buildContext(MESSAGE, logLevel)).thenReturn(context);

        logger.log(MESSAGE, logLevel);
        verifyAllDevices(context, logLevel);
    }

    private void verifyAllDevices(Context context, LogLevel logLevel) {
        for (Device device : devices) {
            verify(device).log(context, logLevel);
        }
    }

    private Context createContext() {
        return new Context(MESSAGE, LogLevel.DEBUG, "session_some");
    }

}