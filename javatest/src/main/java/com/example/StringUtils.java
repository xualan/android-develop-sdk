package com.example;

/**
 * 类名：StringUtils <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/26 <br/>
 * 功能描述：<br/>
 */
public class StringUtils {

    public static boolean isEmpty(String msg){
        return null==msg||msg.equals("");
    }

    public static boolean isZero(long msg){
        return msg==0;
    }


}
