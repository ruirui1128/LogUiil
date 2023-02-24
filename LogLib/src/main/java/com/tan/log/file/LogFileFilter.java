package com.tan.log.file;


import com.tan.log.LogLevel;



public interface LogFileFilter {
    boolean accept(@LogLevel.LogLevelType int level, String tag, String logContent);
}