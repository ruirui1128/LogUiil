package com.tan.log.utils;

import com.tan.log.Log2FileConfigImpl;
import com.tan.log.LogLevel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * create by Rui on 2023/2/28
 * desc:
 */
public class FileUtil {
    private static final int MAX_DAYS = 60;
    private static final int MIN_DAYS = 0;
    private static final long LAST_UPDATE_TIME = 24 * 60 * 60 * 1000;


    public static void checkLog() {

        int daysOfExpire = Log2FileConfigImpl.getInstance().getDaysOfExpire();
        if (daysOfExpire <= MIN_DAYS) return;
        if (daysOfExpire > MAX_DAYS) {
            daysOfExpire = MAX_DAYS;
        }

        File httpFile = Log2FileConfigImpl.getInstance().getLogFile(LogLevel.TYPE_HTTP);
        File actFile = Log2FileConfigImpl.getInstance().getLogFile(LogLevel.TYPE_ACTION);
        deleteFile(actFile, daysOfExpire);
        deleteFile(httpFile, daysOfExpire);

    }

    private static void deleteFile(File directory, int daysOfExpire) {
        if (directory == null) return;
        new Thread(() -> {
            try {
                if (directory.exists() && directory.isDirectory()) {
                    File[] files = directory.listFiles();
                    if (files != null && files.length > 0) {
                        cleanupExpiredFiles(files, daysOfExpire);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void cleanupExpiredFiles(File[] files, int daysOfExpire) {
        long currentTime = System.currentTimeMillis();
        long outTime = daysOfExpire * LAST_UPDATE_TIME;
        for (File file : files) {
            long lastModified = file.lastModified();
            long diff = currentTime - lastModified;
            if (diff > outTime) {
                file.delete();
            }
        }
    }


}
