package com.example.test.cachelog;

import android.app.Application;

import com.example.test.GPSCheckStateTask;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名：LogPublishUtils <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/26 <br/>
 * 功能描述：<br/>
 */
public class LogPublishUtils {

    private LogPublishUtils(){}
    private int count=1;
    private static final LogPublishUtils instance=new LogPublishUtils();

    public static LogPublishUtils getInstance(){
        return instance;
    }

    /**
     * 获取traceId
     *
     * ①traceID:
     * 两位（16进制随机数） + 6位（工号，如果以字母开头，替换成a） +
     * 13（时间戳） + 4（顺序数，全局计数器，如果达到9999，从0001开始） +
     * 1位（标记位，固定为d） + 4位（进程号，随机生成4位16进制数）；
     */
    public String getTraceId(){
        StringBuilder builder=new StringBuilder();
        builder.append(getRandomNumber());
        builder.append(getRandomNumber());
        String userNo=getUserNo();

        if(userNo.length()>6){
            //截取最后六位
            userNo=userNo.substring(userNo.length()-6);
        }
        String s = "^[A-Za-z]";
        Pattern compile = Pattern.compile(s);
        Matcher matcher = compile.matcher(userNo);
        if(matcher.lookingAt()){
            //开头是字母，替换为a
            StringBuffer stringBuffer=new StringBuffer(userNo);
            stringBuffer.setCharAt(0,'a');
            userNo=stringBuffer.toString();
        }
        builder.append(userNo);
        builder.append(System.currentTimeMillis());
        builder.append(getCount());
        builder.append("d");
        builder.append(getRandomNumber());
        builder.append(getRandomNumber());
        builder.append(getRandomNumber());
        builder.append(getRandomNumber());
        return builder.toString();
    }
    private String getCount(){
        if(count>9999){
            count=1;
        }
        String str=new DecimalFormat("0000").format(count);
        count++;
        return str;
    }

    /**
     * 获取随机十六进制数
     */
    private String getRandomNumber(){

        Random random=new Random();
        int temp=random.nextInt(16);
        String result=Integer.toHexString(temp);
        return result;
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


    /**
     * 获取经纬度
     */
    public GPSEntity getLocation(){
//        GPSEntity gpsEntity= GPSCheckStateTask.getInstance().checkGPSState();
        GPSEntity gpsEntity=new GPSEntity();
        gpsEntity.setLatitude(123);
        gpsEntity.setLongitude(123);
        return gpsEntity;
    }


}
