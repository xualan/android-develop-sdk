package com.example.test.cachelog;

import com.example.test.cachelog.entity.LogPublishEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 337965 on 2017/7/26.
 */

public class Test {

    public static void main(String[] args){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateString = formatter.format(new Date());
        System.out.printf(dateString);


        StringBuilder builder=new StringBuilder();
        builder.append("\"");
        builder.append("traceId=").append("123123").append("'");
        System.out.println(builder.toString());

        LogB b=new LogB();
        b.toString();


    }

    static class LogA{
        @Override
        public String toString() {
            System.out.println("LogA");
            return "LogA";
        }
    }

    static class LogB extends LogA{
        @Override
        public String toString() {
            System.out.println("LogB");
            return "LogB";
        }
    }


}
