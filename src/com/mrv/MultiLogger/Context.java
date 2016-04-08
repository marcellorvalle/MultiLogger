package com.mrv.MultiLogger;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Encapsulate context information to be sent to the devices.
 */
public class Context {
    private final Date dateTime;
    private final String message;
    private final String logLevel;
    private final String session;
    private final HashMap<String, Object> extras;

    protected Context(String message, LogLevel logLevel, String session) {
        dateTime = new Date();
        this.message = message;
        this.logLevel = logLevel.name();
        this.session = session;
        extras = new LinkedHashMap<>();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getSession() {
        return session;
    }

    public HashMap<String, Object> getExtraValues() {
        return (HashMap<String, Object>) extras.clone();
    }

    public void addExtraValue(String name, Object value) {
        extras.put(name, value);
    }

    public boolean hasExtras() {
        return !extras.isEmpty();
    }


}
