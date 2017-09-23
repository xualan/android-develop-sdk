package com.example.test.foregroundservice;

import android.os.Looper;

import org.develop.baseoptions.log.MyLog;

/**
 * Created by 337965 on 2017/9/21.
 */

public class AlarmThread extends Thread {
    private Looper looper;
    private OnAlarmThreadListener alarmThreadListener;
    private static final String TAG="AlarmThread";
    @Override
    public void run() {
        MyLog.i(TAG,"-----AlarmThread---RUN------");
        Looper.prepare();
        looper=Looper.myLooper();
        if(alarmThreadListener!=null){
            MyLog.i(TAG,"-----AlarmThread---onThreadCreated------");
            alarmThreadListener.onThreadCreated(looper);
        }
        Looper.loop();
    }
    public void setAlarmThreadListener(OnAlarmThreadListener alarmThreadListener) {
        MyLog.i(TAG,"-----AlarmThread---setAlarmThreadListener------");
        this.alarmThreadListener = alarmThreadListener;
    }
}
