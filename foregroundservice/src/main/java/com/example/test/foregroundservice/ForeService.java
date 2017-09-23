package com.example.test.foregroundservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.example.test.foregroundservice.gps.GPSCheckStateTask;
import com.example.test.foregroundservice.gps.GPSEntity;

import org.develop.baseoptions.log.MyLog;

/**
 * Created by 337965 on 2017/9/19.
 */

public class ForeService extends Service implements OnAlarmThreadListener,OnServiceHandlerListener{
    private static final String TAG="ForeService";
    private ServiceHandler mHandler;
    private AlarmThread alarmThread;
    private long triggerAtMills=1*60*1000;
    private int id=12112;
    private PowerManager.WakeLock wakeLock;
    private boolean hasReleased=false;
    private MediaPlayer mediaPlayer;

    private int key=45;
    private int interval=30*1000;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        hasReleased=false;
        playMusic();
        checkThreadState();
        setWakeMode(this,PowerManager.PARTIAL_WAKE_LOCK);
        startAlarmManager();
        startForeService();
        return START_STICKY;
    }

    private void playMusic(){
        if(mediaPlayer==null){
            //将需要播放的资源与之绑定
            mediaPlayer=MediaPlayer.create(this,R.raw.scan_ok);
            mediaPlayer.setVolume(0.1f,0.1f);
            //开始播放
            mediaPlayer.start();
            //是否循环播放
            mediaPlayer.setLooping(true);
            MyLog.i(TAG,"--------playMusic------");
        }
    }

    private void cancelMediaPlay(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            MyLog.i(TAG,"--------cancelMediaPlay------");
        }
    }


    private void checkThreadState(){
        MyLog.i(TAG,"--------checkThreadState------");
        if(null==alarmThread||alarmThread.isInterrupted()){
            MyLog.i(TAG,"--------startAlarmThread------");
            alarmThread=new AlarmThread();
            alarmThread.setAlarmThreadListener(this);
            alarmThread.start();
        }
    }

    private void setWakeMode(Context context, int mode) {
        MyLog.i(TAG,"--------setWakeMode------");
        boolean wasHeld = false;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                wasHeld = true;
                wakeLock.release();
            }
            wakeLock = null;
        }

        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(mode|PowerManager.ON_AFTER_RELEASE, ForeService.class.getName());
        wakeLock.setReferenceCounted(false);
        if (wasHeld) {
            wakeLock.acquire();
        }
    }

    private void stayAwake(boolean awake) {
        MyLog.i(TAG,"--------stayAwake------"+awake);
        if (wakeLock != null) {
            if (awake && !wakeLock.isHeld()) {
                wakeLock.acquire();
            } else if (!awake && wakeLock.isHeld()) {
                wakeLock.release();
            }
        }
    }


    private void startAlarmManager(){
        MyLog.i(TAG,"--------startAlarmManager------");
        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmBroadcast=new Intent(this,AlarmReceiver.class);
        alarmBroadcast.setAction(AlarmReceiver.FORE_ALARM_ACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,alarmBroadcast,0);
        long mills= SystemClock.elapsedRealtime();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            MyLog.i(TAG,"-----ForeService---SDK>=19---START-----");
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtMills+mills,pendingIntent);
        }else {
            MyLog.i(TAG,"-----ForeService---SDK<19---START-----");
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtMills+mills,pendingIntent);
        }
    }

    private void cancelAlarmManager(){
        MyLog.i(TAG,"--------cancelAlarmManager------");
        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmBroadcast=new Intent(this,AlarmReceiver.class);
        alarmBroadcast.setAction(AlarmReceiver.FORE_ALARM_ACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,alarmBroadcast,0);
        alarmManager.cancel(pendingIntent);
    }

    private void startForeService(){
        MyLog.i(TAG,"--------startForeService------");
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("hello");
        builder.setContentText("world");
        builder.setSmallIcon(R.drawable.hello);
        startForeground(id,builder.getNotification());
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        MyLog.i(TAG,"--------onDestroy------");
        hasReleased=true;
        stayAwake(false);
        cancelMediaPlay();
//        cancelAlarmManager();
        releaseResources();
        stopForeground(true);
        super.onDestroy();
    }

    private void releaseResources(){
        MyLog.i(TAG,"--------releaseResources------");
        if(alarmThread!=null){
            alarmThread.interrupt();
            alarmThread=null;
        }

        if(mHandler!=null){
            mHandler.removeMessages(key);
            mHandler=null;
        }

    }

    @Override
    public void onThreadCreated(Looper looper) {
        MyLog.i(TAG,"--------onThreadCreated------");
        if(looper!=null){
            mHandler=new ServiceHandler(looper);
            mHandler.setOnServiceHandlerListener(this);
            mHandler.sendHandleMessage(key,interval);
            MyLog.i(TAG,"--------createHandler------");
        }
    }

    @Override
    public void onReceive(int what) {
        MyLog.i(TAG,"--------onReceive------");
        if(hasReleased){
            return;
        }
        checkThreadState();
        stayAwake(true);
        GPSEntity gpsEntity= GPSCheckStateTask.getInstance().checkGPSState(ForeService.this);
        if(gpsEntity.isGpsEnable()){
            PerMinLog.d(TAG,"-----检测GPS信息------间隔时间："+interval+" -----GPS信息---"+"-----经度："+gpsEntity.getLongitude()+"---高度："+gpsEntity.getAltitude()+"---纬度："+gpsEntity.getLatitude());
        }else {
            PerMinLog.d(TAG,"-----GPS信息获取失败----原因："+gpsEntity.getErrorMsg());
        }
    }


}
