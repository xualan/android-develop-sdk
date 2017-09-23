package com.example.test.foregroundservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.develop.baseoptions.log.MyLog;

/**
 * Created by 337965 on 2017/9/21.
 */

public class ServiceHandler extends Handler{
    private static final String INTERVAL_TIME="INTERVAL_TIME";

    private static final String TAG="ServiceHandler";
    private OnServiceHandlerListener handlerListener;
    private int minIntervalTime=1;

    public ServiceHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        MyLog.i(TAG,"---------handleMessage---------------"+msg.what);
        int interval=msg.getData().getInt(INTERVAL_TIME);
        if(interval>minIntervalTime){
            sendHandleMessageDelayed(msg.what,interval,interval);
        }
        if(handlerListener!=null){
            handlerListener.onReceive(msg.what);
        }
    }

    public void sendHandleMessageDelayed(int what,int interval,int delayTime){
        if(delayTime<1){
            return;
        }
        removeMessages(what);
        Message message=new Message();
        message.what=what;
        Bundle bundle=new Bundle();
        bundle.putInt(INTERVAL_TIME,interval);
        message.setData(bundle);
        sendMessageDelayed(message,delayTime);
    }

    public void sendHandleMessage(int what,int interval){
        removeMessages(what);
        Message message=new Message();
        message.what=what;
        Bundle bundle=new Bundle();
        bundle.putInt(INTERVAL_TIME,interval);
        message.setData(bundle);
        sendMessage(message);
    }

    public void setOnServiceHandlerListener(OnServiceHandlerListener handlerListener) {
        this.handlerListener = handlerListener;
    }
}
