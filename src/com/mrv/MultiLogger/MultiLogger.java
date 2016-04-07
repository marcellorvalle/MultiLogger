package com.mrv.MultiLogger;


/**
 * Created by Marcello on 06/04/2016.
 */
public interface MultiLogger {
    void logFatal(String message);
    void logFatal(Throwable throwable);
    void logCritical(String message);
    void logCritical(Throwable throwable);
    void logError(String message);
    void logError(Throwable throwable);
    void logWarning(String message);
    void logWarning(Throwable throwable);
    void logMessage(String message);
    void logMessage(Throwable throwable);
    void logInfo(String message);
    void logInfo(Throwable throwable);
    void logDebug(String message);
    void logDebug(Throwable throwable);
}
