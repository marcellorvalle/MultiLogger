package com.mrv.MultiLogger;

import com.mrv.MultiLogger.Devices.Device;

import java.util.*;
import java.util.function.Consumer;

/**
 *
 */
public class MultiLoggerImpl implements MultiLogger {
    private final List<Device> devices;
    private final String  session;

    private Consumer<Context> processor;

    public MultiLoggerImpl() {
        this("");
    }

    public MultiLoggerImpl(String session) {
        this.session = session;
        devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void setProcessor(Consumer<Context> processor) {
        this.processor = processor;
    }

    @Override
    public void logFatal(String message) {
        log(message, LogLevel.FATAL);
    }

    @Override
    public void logFatal(Throwable throwable) {
        logFatal(throwable.getMessage());
    }

    @Override
    public void logCritical(String message) {
        log(message, LogLevel.CRITICAL);
    }

    @Override
    public void logCritical(Throwable throwable) {
        logCritical(throwable.getMessage());
    }

    @Override
    public void logError(String message) {
        log(message, LogLevel.ERROR);
    }

    @Override
    public void logError(Throwable throwable) {
        logError(throwable.getMessage());
    }

    @Override
    public void logWarning(String message) {
        log(message, LogLevel.WARNING);
    }

    @Override
    public void logWarning(Throwable throwable) {
        logWarning(throwable.getMessage());
    }

    @Override
    public void logMessage(String message) {
        log(message, LogLevel.MESSAGE);
    }

    @Override
    public void logMessage(Throwable throwable) {
        logMessage(throwable.getMessage());
    }

    @Override
    public void logInfo(String message) {
        log(message, LogLevel.INFO);
    }

    @Override
    public void logInfo(Throwable throwable) {
        logInfo(throwable.getMessage());
    }

    @Override
    public void logDebug(String message) {
        log(message, LogLevel.DEBUG);
    }

    @Override
    public void logDebug(Throwable throwable) {
        logDebug(throwable.getMessage());
    }

    protected void log(String message, LogLevel logLevel){
        Context context = buildContext(message, logLevel);

        for (Device device : devices) {
            device.log(context, logLevel);
        }
    }

    protected Context buildContext(String message, LogLevel logLevel) {
        Context context = new Context(message, logLevel, session);
        process(context);
        return context;
    }

    protected void process(Context context) {
        if (this.processor != null) {
            this.processor.accept(context);
        }
    }

}
