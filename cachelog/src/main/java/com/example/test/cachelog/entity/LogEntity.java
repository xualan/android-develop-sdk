package com.example.test.cachelog.entity;

import com.example.test.cachelog.LogGrade;

import java.io.Serializable;

/**
 * 类名：LogEntity <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/24 <br/>
 * 功能描述：<br/>
 * Log实体类，Log基础类
 */
public class LogEntity implements Serializable{

    private String tag;
    private String msg;
    private LogGrade grade;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LogGrade getGrade() {
        return grade;
    }

    public void setGrade(LogGrade grade) {
        this.grade = grade;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("grade:");
        builder.append(getGrade().name());
        builder.append(" tag:");
        builder.append(getTag());
        builder.append(" msg:");
        builder.append(getMsg());
        builder.append(" time:");
        builder.append(getTime());
        return builder.toString();
    }
}
