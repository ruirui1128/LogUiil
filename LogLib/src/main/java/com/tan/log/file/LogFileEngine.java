package com.tan.log.file;

import com.tan.log.LogLevel;

import java.io.File;



public interface LogFileEngine {
    void writeToFile(File logFile, @LogLevel.LogLevelType int logLevel, String logContent, LogFileParam params);
    void flushAsync();
    void release();
}
