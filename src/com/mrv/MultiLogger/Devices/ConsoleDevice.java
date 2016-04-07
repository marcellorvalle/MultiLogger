package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.Context;
import com.mrv.MultiLogger.LogLevel;

/**
 * Output logs to console
 */
public class ConsoleDevice extends BasicDevice {
    public ConsoleDevice(LogLevel minLevel) {
        super(minLevel);
    }

    @Override
    protected void doLog(Context context) {
        String out = String.format("[%s] %s %s %s",
                context.getDateTime(),
                context.getLogLevel(),
                context.getMessage(),
                context.getSession());
        System.out.println(out);
    }
}
