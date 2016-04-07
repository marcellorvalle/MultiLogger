package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.Context;
import com.mrv.MultiLogger.LogLevel;

/**
 * Basic device with log leve evaluation hability.
 */
abstract class BasicDevice implements Device {
    private final LogLevel minLevel;

    public BasicDevice(LogLevel minLevel) {
        this.minLevel = minLevel;
    }

    @Override
    public final void log(Context context, LogLevel level) {
        int compare = level.compareTo(minLevel);

        if (minLevel.compareTo(level) >= 0) {
            doLog(context);
        }
    }

    protected abstract void doLog(Context context);
}
