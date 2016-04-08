package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.Context;
import com.mrv.MultiLogger.LogLevel;

/**
 * Basic device with log level evaluation hability.
 */
public abstract class Device {
    private final LogLevel minLevel;

    public Device(LogLevel minLevel) {
        this.minLevel = minLevel;
    }

    public final void log(Context context, LogLevel level) {
        if (minLevel.compareTo(level) >= 0) {
            doLog(context);
        }
    }

    protected abstract void doLog(Context context);
}
