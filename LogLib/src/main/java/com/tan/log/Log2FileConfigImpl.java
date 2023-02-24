package com.tan.log;


import android.text.TextUtils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tan.log.config.Log2FileConfig;
import com.tan.log.file.LogFileEngine;
import com.tan.log.file.LogFileFilter;
import com.tan.log.pattern.LogPattern;

import java.io.File;


class Log2FileConfigImpl implements Log2FileConfig {

    private static final String DEFAULT_LOG_NAME_FORMAT = "%d{yyyyMMdd}.txt";


    private LogFileEngine engine;

    private LogFileEngine httpEngine;

    private LogFileEngine actionEngine;

    private LogFileFilter fileFilter;
    private @LogLevel.LogLevelType int logLevel = LogLevel.TYPE_WARM;
    private boolean enable = false;
    private String logFormatName = DEFAULT_LOG_NAME_FORMAT;
    private String logHttpFormatName = DEFAULT_LOG_NAME_FORMAT;
    private String logActionFormatName = DEFAULT_LOG_NAME_FORMAT;
    private String logPath;
    private String httpLogPath;
    private String actionLogPath;
    private static Log2FileConfigImpl singleton;
    private String customFormatName;

    static Log2FileConfigImpl getInstance() {
        if (singleton == null) {
            synchronized (Log2FileConfigImpl.class) {
                if (singleton == null) {
                    singleton = new Log2FileConfigImpl();
                }
            }
        }
        return singleton;
    }

    @Override
    public Log2FileConfig configLog2FileEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    boolean isEnable() {
        return enable;
    }

    @Override
    public Log2FileConfig configLog2FilePath(String logPath) {
        this.logPath = logPath;
        return this;
    }

    @Override
    public Log2FileConfig configLog2HttpFilePath(String path) {
        if (TextUtils.isEmpty(path)) {
            this.httpLogPath = logPath;
        } else {
            this.httpLogPath = path;
        }

        return this;
    }

    @Override
    public Log2FileConfig configLog2ActionFilePath(String path) {
        if (TextUtils.isEmpty(path)) {
            this.actionLogPath = logPath;
        } else {
            this.actionLogPath = path;
        }
        return this;
    }

    /**
     * 获取日志路径
     *
     * @return 日志路径
     */
    @NonNull
    String getLogPath() {
        if (TextUtils.isEmpty(logPath)) {
            throw new RuntimeException("Log File Path must not be empty");
        }
        File file = new File(logPath);
        if (file.exists() || file.mkdirs()) {
            return logPath;
        }
        throw new RuntimeException("Log File Path is invalid or no sdcard permission");
    }

    String getHttpLogPath() {
        if (TextUtils.isEmpty(httpLogPath)) {
            throw new RuntimeException("httpLogPath  must not be empty");
        }
        File file = new File(httpLogPath);
        if (file.exists() || file.mkdirs()) {
            return httpLogPath;
        }
        throw new RuntimeException("Log File Path is invalid or no sdcard permission");
    }

    String getActionLogPath() {
        if (TextUtils.isEmpty(actionLogPath)) {
            throw new RuntimeException("actionLogPath  must not be empty");
        }
        File file = new File(actionLogPath);
        if (file.exists() || file.mkdirs()) {
            return actionLogPath;
        }
        throw new RuntimeException("Log File Path is invalid or no sdcard permission");
    }


    @Override
    public Log2FileConfig configLog2FileNameFormat(String formatName) {
        if (!TextUtils.isEmpty(formatName)) {
            this.logFormatName = formatName;
        }
        return this;
    }

    @Override
    public Log2FileConfig configLog2HttpFileNameFormat(String formatName) {
        if (!TextUtils.isEmpty(formatName)) {
            this.logHttpFormatName = formatName;
        }
        return this;
    }

    @Override
    public Log2FileConfig configLog2ActionFileNameFormat(String formatName) {
        if (!TextUtils.isEmpty(formatName)) {
            this.logActionFormatName = formatName;
        }
        return this;
    }

    /**
     * 此方法重置日期格式,在不关机的情况下，重新自动分割文件
     */
    public Log2FileConfig resetFormatName() {
        flushAsync();
        if (customFormatName != null) {
            customFormatName = null;
        }

        if (httpLogFormatName != null) {
            httpLogFormatName = null;
        }

        if (actionLogFormatName!= null) {
            actionLogFormatName = null;
        }
        return this;

    }

    String getLogFormatName() {
        if (customFormatName == null) {
            customFormatName = new LogPattern.Log2FileNamePattern(logFormatName).doApply();
        }
        return customFormatName;
    }

    private String httpLogFormatName;

    String getLogHttpFormatName() {
        if (httpLogFormatName == null) {
            httpLogFormatName = new LogPattern.Log2FileNamePattern(logHttpFormatName).doApply();
        }
        return httpLogFormatName;
    }

    private String actionLogFormatName;

    String getActionLogFormatName() {
        if (actionLogFormatName == null) {
            actionLogFormatName = new LogPattern.Log2FileNamePattern(logActionFormatName).doApply();
        }
        return actionLogFormatName;
    }


    @Override
    public Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level) {
        this.logLevel = level;
        return this;
    }

    int getLogLevel() {
        return logLevel;
    }

    @Override
    public Log2FileConfig configLogFileEngine(LogFileEngine engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public Log2FileConfig configHttpLogFileEngine(LogFileEngine engine) {
        this.httpEngine = engine;
        return this;
    }

    @Override
    public Log2FileConfig configActionLogFileEngine(LogFileEngine engine) {
        this.actionEngine = engine;
        return this;
    }


    @Override
    public Log2FileConfig configLogFileFilter(LogFileFilter fileFilter) {
        this.fileFilter = fileFilter;
        return this;
    }

    /**
     * 获取的是日志文件夹
     *
     * @param level
     * @return
     */
    @Override
    @Nullable
    public File getLogFile(@LogLevel.LogLevelType int level) {
        if (level <= LogLevel.TYPE_WTF) {
            String path = getLogPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(path);
            }
        } else if (level == LogLevel.TYPE_HTTP) {
            return new File(getHttpLogPath());
        } else {
            return new File(getActionLogPath());
        }
        return null;
    }

    @Override
    public void flushAsync() {
        if (engine != null) {
            engine.flushAsync();
        }
        if (httpEngine != null) {
            httpEngine.flushAsync();
        }
        if (actionEngine != null) {
            actionEngine.flushAsync();
        }
    }

    @Override
    public void release() {
        if (engine != null) {
            engine.release();
        }
        if (httpEngine != null) {
            httpEngine.release();
        }
        if (actionEngine != null) {
            actionEngine.release();
        }
    }

    LogFileFilter getFileFilter() {
        return fileFilter;
    }

    LogFileEngine getEngine() {
        return engine;
    }

    LogFileEngine getHttpEngine() {
        return httpEngine;
    }

    LogFileEngine getActionEngine() {
        return actionEngine;
    }

}
