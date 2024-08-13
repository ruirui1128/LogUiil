package com.tan.log;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class LogLevel {
    public static final int TYPE_VERBOSE = 1;
    public static final int TYPE_DEBUG = 2;
    public static final int TYPE_INFO = 3;
    public static final int TYPE_WARM = 4;
    public static final int TYPE_ERROR = 5;
    public static final int TYPE_WTF = 6;
    public static final int TYPE_ACTION = 7;  // 用户操作 日志
    public static final int TYPE_HTTP = 8;  // Http 日志
    public static final int TYPE_CRASH = 9;  // Crash 日志


    @IntDef({TYPE_VERBOSE, TYPE_DEBUG, TYPE_INFO, TYPE_WARM, TYPE_ERROR, TYPE_WTF, TYPE_HTTP, TYPE_ACTION, TYPE_CRASH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevelType {
    }
}
