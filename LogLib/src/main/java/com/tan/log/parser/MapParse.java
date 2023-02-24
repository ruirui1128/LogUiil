package com.tan.log.parser;


import androidx.annotation.NonNull;

import com.tan.log.Parser;
import com.tan.log.utils.ObjectUtil;

import java.util.Map;
import java.util.Set;



/**
 * Created by pengwei on 16/3/8.
 */
class MapParse implements Parser<Map> {
    @NonNull
    @Override
    public Class<Map> parseClassType() {
        return Map.class;
    }

    @Override
    public String parseString(@NonNull Map map) {
        StringBuilder msg = new StringBuilder(map.getClass().getName() + " [" + LINE_SEPARATOR);
        Set keys = map.keySet();
        for (Object key : keys) {
            String itemString = "%s -> %s" + LINE_SEPARATOR;
            Object value = map.get(key);
            if (value != null) {
                if (value instanceof String) {
                    value = "\"" + value + "\"";
                } else if (value instanceof Character) {
                    value = "\'" + value + "\'";
                }
            }
            msg.append(String.format(itemString, ObjectUtil.objectToString(key),
                    ObjectUtil.objectToString(value)));
        }
        return msg + "]";
    }
}
