package com.tan.log.parser;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.tan.log.Parser;
import com.tan.log.utils.ObjectUtil;


/**
 * Created by pengwei on 16/3/8.
 */
class BundleParse implements Parser<Bundle> {

    @NonNull
    @Override
    public Class<Bundle> parseClassType() {
        return Bundle.class;
    }

    @Override
    public String parseString(@NonNull Bundle bundle) {
        StringBuilder builder = new StringBuilder(bundle.getClass().getName());
        builder.append(" [");
        builder.append(LINE_SEPARATOR);
        for (String key : bundle.keySet()) {
            builder.append(String.format("'%s' => %s " + LINE_SEPARATOR,
                    key, ObjectUtil.objectToString(bundle.get(key))));
        }
        builder.append("]");
        return builder.toString();
    }
}
