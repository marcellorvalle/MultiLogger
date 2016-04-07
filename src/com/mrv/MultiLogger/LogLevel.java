package com.mrv.MultiLogger;

/**
 * Created by Marcello on 06/04/2016.
 */
public enum LogLevel {
    FATAL(6),
    CRITICAL(5),
    ERROR(4),
    WARNING(3),
    MESSAGE(2),
    INFO(1),
    DEBUG(0);

    private int value;

    LogLevel(int value) {
        this.value = value;
    }
}