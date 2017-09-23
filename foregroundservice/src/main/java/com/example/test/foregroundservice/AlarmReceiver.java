package com.example.test.foregroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.develop.baseoptions.log.MyLog;

/**
 * Created by 337965 on 2017/9/21.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public static final String FORE_ALARM_ACTION="FORE_ALARM_ACTION";
    private static final String TAG="AlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLog.i(TAG,"-----AlarmReceiver---onReceive------");
        String action=intent.getAction();
        if(action==null){
            return;
        }else if(action.equals(FORE_ALARM_ACTION)){
            MyLog.i(TAG,"-----AlarmReceiver---onReceive---START SERVICE---");
            Intent serviceIntent=new Intent(context,ForeService.class);
            context.startService(serviceIntent);
        }
    }
}
