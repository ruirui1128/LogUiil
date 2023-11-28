package com.tan.log.utils;

import com.tan.log.Log2FileConfigImpl;
import com.tan.log.LogLevel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * create by Rui on 2023/2/28
 * desc:
 */
public class FileUtil {
    private static final int MAX_DAYS = 60;
    private static final int MIN_DAYS = 0;

    // 一天的秒数
    private static final int dayOfS = 86400;

    public static void checkLog() {

        int daysOfExpire = Log2FileConfigImpl.getInstance().getDaysOfExpire();
        if (daysOfExpire <= MIN_DAYS) return;
        if (daysOfExpire > MAX_DAYS) {
            daysOfExpire = MAX_DAYS;
        }

//        File logFile = Log2FileConfigImpl.getInstance().getLogFile(LogLevel.TYPE_WTF);
        File httpFile = Log2FileConfigImpl.getInstance().getLogFile(LogLevel.TYPE_HTTP);
        File actFile = Log2FileConfigImpl.getInstance().getLogFile(LogLevel.TYPE_ACTION);
//        filterFile(logFile, daysOfExpire);
        filterFile(actFile, daysOfExpire);
        filterFile(httpFile, daysOfExpire);

    }

    private static void filterFile(File file, int daysOfExpire) {
        if (file != null && file.exists() && file.isDirectory() && file.listFiles() != null
                && file.listFiles().length > 0) {

            Arrays.stream(file.listFiles())
                    .filter(f -> f.getName().endsWith(".txt"))
                    .forEach(f -> checkOut(f, daysOfExpire));

        }
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);

    private static void checkOut(File file, int daysOfExpire) {
        try {
            var fileTimeString = file.getName().replace(".txt", "");
            var fileTime = sdf.parse(fileTimeString).getTime() / 1000L;

            var today = sdf.format(System.currentTimeMillis());
            var todayTime = sdf.parse(today).getTime() / 1000L;

            // 删除过期文件
            if ((todayTime - fileTime) / dayOfS - daysOfExpire > 0) {
                file.delete();
            }

        } catch (Exception e) {
        }


    }


}
