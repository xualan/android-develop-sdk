package com.example.test.cachelog;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 类名：LogPublishUtils <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/26 <br/>
 * 功能描述：<br/>
 */
public class LogPublishUtils {

    private LogPublishUtils(){}

    private static final LogPublishUtils instance=new LogPublishUtils();

    public static LogPublishUtils getInstance(){
        return instance;
    }

    /**
     * 获取UUID
     */
    public String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 获取当前系统时间戳
     */
    public String getTimeStamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取PDA所属组织部门编码
     */
    public String getOrgCode(){
        return "WQWE123123123";
    }

    /**
     * 获取用户工号
     */
    public String getUserNo(){
        return "213131";
    }

    /**
     * 获取设备号
     */
    public String getDeviceNumber(){
        return "45612314554878545";
    }
    /**
     * 获取设备型号
     */
    public String getDeviceType(){
        return "huawei";
    }

    public String getAppVersion(){
        return "1.21.122.1";
    }





}
