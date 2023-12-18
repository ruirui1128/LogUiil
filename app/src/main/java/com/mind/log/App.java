package com.mind.log;

import android.app.Application;
import android.util.Log;

/**
 * create by Rui on 2023-12-14
 * desc:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("LogUtil", "===================Application================");
    }
}
