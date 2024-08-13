package com.tan.log;


public interface Printer {

    void d(String message, Object... args);
    void d(Object object);

    void e(String message, Object... args);
    void e(Object object);

    void w(String message, Object... args);
    void w(Object object);

    void i(String message, Object... args);
    void i(Object object);

    void v(String message, Object... args);
    void v(Object object);

    void wtf(String message, Object... args);
    void wtf(Object object);

    void json(String json);
    void xml(String xml);

    void h(String message,Object msg);
    void h(Object msg);
    void a(Object msg);
    void a(String message,Object msg);

    void crash(Object msg);
    void crash(String message,Object msg);
}
