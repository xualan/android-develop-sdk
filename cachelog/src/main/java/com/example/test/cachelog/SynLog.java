package com.example.test.cachelog;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.test.cachelog.config.LogConfig;
import com.example.test.cachelog.entity.LogEntity;
import com.example.test.cachelog.print.LogPrintToFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类名：SynLog <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/24 <br/>
 * 功能描述：<br/>
 *
 */
public class SynLog {
    private static final int SYN_LOG_WRITE=1;
    /**
     * 最大缓存条数
     */
    private static final int SYN_LOG_MAX=2000;
    private SynLog(){
        loadLogConfig();
        checkState();
    }

    private static final SynLog instance=new SynLog();
    /**
     * 缓存列表
     */
    private List<Object> logList=new ArrayList<>();

    /**
     * 读写锁
     */
    ReadWriteLock lock=new ReentrantReadWriteLock();

    /**
     * 获取当前类的实例
     */
    public static SynLog getInstance(){
        return instance;
    }

    /**
     * log是否关闭
     */
    private boolean closeable=false;

    /**
     * 输出间隔时间，默认两秒
     */
    private int delaySeconds=2;

    /**
     * 是否写出到文件
     */
    private boolean writeToFiles=false;

    /**
     * 加载log配置文件
     */
    private void loadLogConfig(){
        //是否关闭
        closeable=LogConfig.getInstance().isCloseable();
        //文件输出间隔时间
        delaySeconds=LogConfig.getInstance().getWriteDelaySeconds();
        //是否输出到文件
        writeToFiles=LogConfig.getInstance().isWriteToFiles();
    }


    public void toLog(Object logEntity){
        lock.writeLock().lock();
        logList.add(logEntity);
        Log.i("toLog","----------"+logEntity.toString());
        lock.writeLock().unlock();
    }

    public void toLog(LogGrade grade,String tag,String msg){
        lock.writeLock().lock();
        LogEntity entity=new LogEntity();
        entity.setGrade(grade);
        entity.setTag(tag);
        entity.setMsg(msg);
        entity.setTime(System.currentTimeMillis()+"");
        log(entity);
        Log.i("toLog","----------"+entity.toString());
        logList.add(entity);
        lock.writeLock().unlock();
    }

    private LogThread logThread;
    /**
     * 检查线程当前状态
     */
    public void checkState(){
        Log.i("checkState","---------start-----");
        if(!writeToFiles){
            //不用写出到文件
            return;
        }
        if(logThread==null){
            logThread=new LogThread();
            logThread.start();
            return;
        }
        if(logThread.isInterrupted()||!logThread.isAlive()){
            logThread.start();
        }
    }


    private Handler logHandler;

    /**
     * log线程
     */
    class LogThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();
            logHandler=new Handler(Looper.myLooper()){
                @Override
                public void handleMessage(Message msg) {
                    //处理收到的信息
                    switch (msg.what){
                        case SYN_LOG_WRITE:
                            printLog();
                            logHandler.sendEmptyMessageDelayed(SYN_LOG_WRITE,delaySeconds*1000);
                            break;
                        default:
                            break;
                    }

                }
            };
            logHandler.sendEmptyMessageDelayed(SYN_LOG_WRITE,delaySeconds*1000);
            Looper.loop();
        }
    }

    /**
     * 打印日志
     */
    public void printLog(){
        lock.readLock().lock();
        List<Object> temp=logList;
        if(temp.size()==0){
            Log.i("print log","------temp---"+temp.size());
            return;
        }
        if(temp.size()>SYN_LOG_MAX){
            Log.e("print log","---缓存超过最大条数--清空缓存数据---");
            logList.clear();
            return;
        }
        if(LogPrintToFiles.getInstance().printToFiles(temp)){
            logList.removeAll(temp);
        }
        lock.readLock().unlock();
    }


    /**
     * 本地log
     */
    private void log(LogEntity log){

        if(closeable){
            //本地开关关闭
            return;
        }
        LogGrade grade=log.getGrade();
        String tag=log.getTag();
        String msg=log.getMsg();
        switch (grade){
            case VERBOSE:
                Log.v(tag,msg);
                break;
            case DEBUG:
                Log.d(tag,msg);
                break;
            case INFO:
                Log.i(tag,msg);
                break;
            case WARN:
                Log.w(tag,msg);
                break;
            case ERROR:
                Log.e(tag,msg);
                break;
            default:
                Log.v(tag,msg);
                break;
        }
    }

}
