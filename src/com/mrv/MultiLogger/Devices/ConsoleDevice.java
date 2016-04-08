package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.Context;
import com.mrv.MultiLogger.LogLevel;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Output logs to console
 */
public class ConsoleDevice extends Device {
    public ConsoleDevice(LogLevel minLevel) {
        super(minLevel);
    }

    @Override
    protected void doLog(Context context) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String out = String.format("%s %-8s\t%-45s\t%s",
                formatter.format(context.getDateTime()),
                context.getLogLevel(),
                context.getMessage(),
                context.getSession());
        System.out.println(out);

        if(context.hasExtras()) {
            System.out.println("\tExtras: " + serializeExtraValues(context.getExtraValues()));
        }
    }

    protected String serializeExtraValues(HashMap<String, Object> extras) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String,Object> extra : extras.entrySet()) {
            sb.append(
                String.format("%s = %s | ", extra.getKey(), extra.getValue()));
        }

        return sb.substring(0, sb.length() - 2);
    }
}
