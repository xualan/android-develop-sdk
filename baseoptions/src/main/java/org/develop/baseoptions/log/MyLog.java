package org.develop.baseoptions.log;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:MyLog
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月23日16:02
 * <br/>
 * 功能描述：日志打印类
 * <br/>
 * 1.调用MyLog.setOnMyLogListener(OnMyLogListener listener)可以对日志设置监听
 * <br/>
 * 2.所有log方法，支持两种调用方式，x(String tag, String msg)和x(String tag, int msgId)
 * <br/>
 * 3.如果调用打印x(String tag, int msgId)，需设置MyLog.initContextResource(Application application)
 */
public class MyLog {

    private MyLog() {
    }

    /**
     * Log级别
     */
    public enum MyLogGrade {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
    }

    public static void v(String tag, String msg) {
        printLog(tag, MyLogGrade.VERBOSE, msg);
    }

    public static void v(String tag, int msgId) {
        printLog(tag, MyLogGrade.VERBOSE, msgId);
    }

    public static void d(String tag, String msg) {
        printLog(tag, MyLogGrade.DEBUG, msg);
    }

    public static void d(String tag, int msgId) {
        printLog(tag, MyLogGrade.DEBUG, msgId);
    }

    public static void i(String tag, String msg) {
        printLog(tag, MyLogGrade.INFO, msg);
    }

    public static void i(String tag, int msgId) {
        printLog(tag, MyLogGrade.INFO, msgId);
    }

    public static void w(String tag, String msg) {
        printLog(tag, MyLogGrade.WARN, msg);
    }

    public static void w(String tag, int msgId) {
        printLog(tag, MyLogGrade.WARN, msgId);
    }

    public static void e(String tag, String msg) {
        printLog(tag, MyLogGrade.ERROR, msg);
    }

    public static void e(String tag, int msgId) {
        printLog(tag, MyLogGrade.ERROR, msgId);
    }

    private static void printLog(String tag, MyLogGrade grade, String msg) {
        if (isClose()) {
            return;
        }
        switch (grade) {
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            default:
                break;
        }
        if(myLogListener!=null){
            myLogListener.onLogPrint(tag, grade, msg);
        }


    }

    private static void printLog(String tag, MyLogGrade grade, int msgId) {
        if (resources == null) {
            Log.e(tag, "if you use msgId,please set MyLog resources context using MyLog.initContextResource(Application application)");
            return;
        }
        String msg = resources.getString(msgId);
        printLog(tag, grade, msg);
    }

    private static Resources resources;

    public static void initContextResource(Application application) {
        resources = application.getResources();
    }

    private static boolean close = false;

    public static void closeLog() {
        close = true;
    }

    public static boolean isClose() {
        return close;
    }

    private static OnMyLogListener myLogListener;
    public static void setOnMyLogListener(OnMyLogListener listener) {
        myLogListener=listener;
    }


}