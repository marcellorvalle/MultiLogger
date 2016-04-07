package com.mrv.MultiLogger.Devices;

import com.mrv.MultiLogger.Context;
import com.mrv.MultiLogger.LogLevel;

/**
 * Created by Marcello on 06/04/2016.
 */
public interface Device {
    void log(Context context, LogLevel level);
}
