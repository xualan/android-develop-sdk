package com.example.test.cachelog.service;

/**
 * 类名：LogService <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/26 <br/>
 * 功能描述：<br/>
 */
public class LogService {


    private LogService(){}

    private static final LogService instance=new LogService();

    public static LogService getInstance(){
        return instance;
    }

    /**
     * 日志上传请求时间策略
     */
    public void requestTimeManner(){

    }



}
