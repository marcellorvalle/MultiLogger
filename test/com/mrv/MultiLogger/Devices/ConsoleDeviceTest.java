package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.LogLevel;
import com.mrv.MultiLogger.MultiLogger;
import com.mrv.MultiLogger.MultiLoggerImpl;
import com.sun.javaws.exceptions.ExitException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcello on 06/04/2016.
 */
public class ConsoleDeviceTest {

    @Test
    public void debug() {
        ConsoleDevice cd = new ConsoleDevice(LogLevel.INFO);
        MultiLoggerImpl logger = new MultiLoggerImpl("teste_session");

        logger.addDevice(cd);
        logger.logDebug("eh debug");
        logger.logInfo("eh info");
        logger.logCritical("eh critical");
        logger.logFatal("eh fatal");
        logger.logFatal(new Exception("eh exception fatal"));
    }

}