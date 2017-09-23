package org.develop.baseoptions.log;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;

import org.develop.baseoptions.file.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

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
 * <br/>
 * 4.日志文件写入，需将writeToFiles=true
 * <br/>
 * 5.日志显示开关 close = false
 */
public abstract class BaseLog {
    /**
     * log关闭开关
     */
    private static boolean close = false;
    /**
     * 是否写入文件
     */
    private static boolean writeToFiles=true;
    private static String logDirs="mylog";
    /**
     * 日志文件目录
     */
    private static String logFileDir= FileUtils.createFile(getLogDirs());

    private static String suffixName="Log.txt";
    /**
     * 日志时间格式化格式
     */
    private static String simpleDate ="yyyy-MM-dd HH:mm:ss";
    /**
     *  日志的输出格式
     */
    private static DateFormat myLogSdf = new SimpleDateFormat(getSimpleDate());
    /**
     * 原子计数,处理多线程打印信息
     */
    private static AtomicInteger mOpenCounter =new AtomicInteger();

    private static DateFormat logFile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式


    /**
     * Log级别
     */
    public enum BaseLogGrade {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
    }

    public static void v(String tag, String msg) {
        printLog(tag, BaseLogGrade.VERBOSE, msg);
    }

    public static void v(String tag, int msgId) {
        printLog(tag, BaseLogGrade.VERBOSE, msgId);
    }

    public static void d(String tag, String msg) {
        printLog(tag, BaseLogGrade.DEBUG, msg);
    }

    public static void d(String tag, int msgId) {
        printLog(tag, BaseLogGrade.DEBUG, msgId);
    }

    public static void i(String tag, String msg) {
        printLog(tag, BaseLogGrade.INFO, msg);
    }

    public static void i(String tag, int msgId) {
        printLog(tag, BaseLogGrade.INFO, msgId);
    }

    public static void w(String tag, String msg) {
        printLog(tag, BaseLogGrade.WARN, msg);
    }

    public static void w(String tag, int msgId) {
        printLog(tag, BaseLogGrade.WARN, msgId);
    }

    public static void e(String tag, String msg) {
        printLog(tag, BaseLogGrade.ERROR, msg);
    }

    public static void e(String tag, int msgId) {
        printLog(tag, BaseLogGrade.ERROR, msgId);
    }

    private static void printLog(String tag, BaseLogGrade grade, String msg) {
        if (!isClose()) {
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
        }
        if(isWriteToFiles()){
            writeToFiles(tag, grade, msg);
        }

    }

    private synchronized static void writeToFiles(String tag, BaseLogGrade grade, String msg){
        Date nowtime = new Date();
        //日志显示格式化
        String needWriteMessage =String.format("[%s] [%s] [%s]  %s" ,myLogSdf.format(nowtime),grade.name(),tag,msg);
        FileWriter filerWriter = null;
        BufferedWriter bufWriter = null;
        try {
            if(mOpenCounter.incrementAndGet() == 1){
                String needWriteFiel = logFile.format(nowtime);
                File file = new File(logFileDir, needWriteFiel + getSuffixName());

                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                // 第二个参数表示是不是要接上文件中原来的数据，true不进行覆盖
                filerWriter = new FileWriter(file, true);
                bufWriter = new BufferedWriter(filerWriter);
                bufWriter.write(needWriteMessage);
                bufWriter.newLine();
                bufWriter.flush();
            }
        }catch (IOException e){
            Log.e("BASELog未输出",needWriteMessage);
            Log.e("BASELog","异常:打开失败!");
            Log.e("BASELog",e.getMessage());
        }finally {
            try {
                if(mOpenCounter.decrementAndGet() == 0) {
                    if (bufWriter != null) {
                        bufWriter.close();
                    }
                    if (filerWriter != null) {
                        filerWriter.close();
                    }
                }
            }catch (IOException e){
                Log.e("BASELog","异常:关闭失败!");
                Log.e("BASELog",e.getMessage());
            }
        }
    }


    private static void printLog(String tag, BaseLogGrade grade, int msgId) {
        if (resources == null) {
            Log.e(tag, "if you use msgId,please set MyLog resources context by using MyLog.initContextResource(Application application)");
            return;
        }
        String msg = resources.getString(msgId);
        printLog(tag, grade, msg);
    }

    private static Resources resources;

    public static void initContextResource(Application application) {
        resources = application.getResources();
    }

    public static boolean isClose() {
        return close;
    }
    /**
     * 设置是否关闭
     */
    public abstract void setLogCloseable(boolean closeable);

    public static void setClose(boolean close) {
        BaseLog.close = close;
    }

    public static boolean isWriteToFiles() {
        return writeToFiles;
    }

    /**
     * 设置是否要写入到文件中 
     */
    public abstract void setWriteToFilesable(boolean writeToFilesable);

    public static void setWriteToFiles(boolean writeToFiles) {
        BaseLog.writeToFiles = writeToFiles;
    }

    public static String getSimpleDate() {
        return simpleDate;
    }

    public static void setSimpleDate(String simpleDate) {
        BaseLog.simpleDate = simpleDate;
    }

    public static String getLogDirs() {
        return logDirs;
    }

    public static void setLogDirs(String logDirs) {
        BaseLog.logDirs = logDirs;
    }

    public static String getSuffixName() {
        return suffixName;
    }

    public static void setSuffixName(String suffixName) {
        BaseLog.suffixName = suffixName;
    }

}