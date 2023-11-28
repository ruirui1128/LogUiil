package com.tan.log.config;


import com.tan.log.LogLevel;
import com.tan.log.file.LogFileEngine;
import com.tan.log.file.LogFileFilter;

import java.io.File;

/**
 * Created by pengwei on 2017/3/30.
 */

public interface Log2FileConfig {
    Log2FileConfig configLog2FileEnable(boolean enable);

    Log2FileConfig configLog2FilePath(String logPath);

    Log2FileConfig configLog2HttpFilePath(String logPath);

   // Log2FileConfig configLog2ActionFilePath(String logPath);



    Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level);

    Log2FileConfig configLogFileEngine(LogFileEngine engine);

    Log2FileConfig configHttpLogFileEngine(LogFileEngine engine);

    //Log2FileConfig configActionLogFileEngine(LogFileEngine engine);

    Log2FileConfig configLogFileFilter(LogFileFilter fileFilter);

    File getLogFile(@LogLevel.LogLevelType int level);

    // 设置过期天数  默认0天 既 不设置日期过期时间
    Log2FileConfig configDaysOfExpire(int daysOfExpire);

    void flushAsync();

    void release();
}
