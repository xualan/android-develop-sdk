package com.example.test.cachelog;

import android.util.Log;

import com.example.test.cachelog.entity.LogEntity;
import com.example.test.cachelog.entity.LogPublishEntity;
import com.example.test.cachelog.print.LogPrintToFiles;

import org.develop.baseoptions.StringUtils;

/**
 * 类名：LogUtils <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/24 <br/>
 * 功能描述：<br/>
 * 在某些手机上的v d级别的log不显示
 * 建议使用i w e
 *
 */
public class LogUtils {

    private static final String TAG="LogUtils";

    private LogUtils(){}
    public static void v(String tag,String msg){
        SynLog.getInstance().toLog(LogGrade.VERBOSE,tag,msg);
    }
    public static void d(String tag,String msg){
        SynLog.getInstance().toLog(LogGrade.DEBUG,tag,msg);
    }
    public static void i(String tag,String msg){
        SynLog.getInstance().toLog(LogGrade.INFO,tag,msg);
    }
    public static void w(String tag,String msg){
        SynLog.getInstance().toLog(LogGrade.WARN,tag,msg);
    }
    public static void e(String tag,String msg){
        SynLog.getInstance().toLog(LogGrade.ERROR,tag,msg);
    }
    /**
     * log打印
     */
    public static void log(Object logEntity){
        if(logEntity==null){
            Log.e(TAG,"please set logEntity,logEntity can not be null");
            return;
        }
        SynLog.getInstance().toLog(logEntity);
    }


    public static void clearFiles(){
        LogPrintToFiles.getInstance().clearLog();
    }





}
