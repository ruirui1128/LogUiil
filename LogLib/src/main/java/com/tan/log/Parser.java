package com.tan.log;


import androidx.annotation.NonNull;

public interface Parser<T> {

    // 换行符
    String LINE_SEPARATOR = System.getProperty("line.separator");

    @NonNull
    Class<T> parseClassType();

    String parseString(@NonNull T t);
}
