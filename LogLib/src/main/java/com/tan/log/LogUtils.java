package com.tan.log;


import com.tan.log.config.Log2FileConfig;
import com.tan.log.config.LogConfig;

/**
 * 日志管理器
 */
public final class LogUtils {

    private static Logger printer = new Logger();
    private static LogConfigImpl logConfig = LogConfigImpl.getInstance();
    private static Log2FileConfigImpl log2FileConfig = Log2FileConfigImpl.getInstance();

    /**
     * 选项配置
     *
     * @return LogConfig
     */
    public static LogConfig getLogConfig() {
        return logConfig;
    }

    /**
     * 日志写入文件相关配置
     *
     * @return LogConfig
     */
    public static Log2FileConfig getLog2FileConfig() {
        return log2FileConfig;
    }

    public static Printer tag(String tag) {
        return printer.setTag(tag);
    }

    /**
     * verbose输出
     *
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args) {
        printer.v(msg, args);
    }

    public static void v(Object object) {
        printer.v(object);
    }


    /**
     * debug输出
     *
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {
        printer.d(msg, args);
    }

    public static void d(Object object) {
        printer.d(object);
    }

    /**
     * info输出
     *
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args) {
        printer.i(msg, args);
    }

    public static void i(Object object) {
        printer.i(object);
    }

    /**
     * warn输出
     *
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args) {
        printer.w(msg, args);
    }

    public static void w(Object object) {
        printer.w(object);
    }

    public static void h(String msg,Object obj) {
        printer.h(msg,obj);
    }
    public static void h(Object obj) {
        printer.h(obj);
    }

    public static void a(Object obj) {
        printer.a(obj);
    }
    public static void a(String msg,Object obj) {
        printer.a(msg,obj);
    }

    /**
     * error输出
     *
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args) {
        printer.e(msg, args);
    }

    public static void e(Object object) {
        printer.e(object);
    }

    public static void crash(String msg, Object args) {
        printer.crash(msg, args);
    }

    public static void crash(Object object) {
        printer.crash(object);
    }

    /**
     * assert输出
     *
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args) {
        printer.wtf(msg, args);
    }

    public static void wtf(Object object) {
        printer.wtf(object);
    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        printer.json(json);
    }

    /**
     * 输出xml
     *
     * @param xml
     */
    public static void xml(String xml) {
        printer.xml(xml);
    }
}
