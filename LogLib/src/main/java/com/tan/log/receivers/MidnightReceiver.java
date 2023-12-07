package com.tan.log.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tan.log.Log2FileConfigImpl;
import com.tan.log.config.Log2FileConfig;

/**
 * create by Rui on 2023-12-07
 * desc:
 */
public class MidnightReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log2FileConfigImpl.getInstance().resetFormatName();
    }
}