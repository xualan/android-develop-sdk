package com.example.test.cachelog.entity;

import android.util.Log;

import com.example.test.cachelog.LogException;
import com.example.test.cachelog.LogPublishUtils;

import org.develop.baseoptions.StringUtils;

import java.io.Serializable;

/**
 * 类名：LogPublishEntity
 * 创建人：xubaolun
 * 工号：337965
 * 创建日期：2017/7/22
 * 功能描述：日志发布实体类
 * traceId=traceId'timeStamp=timeStamp'orgCode=orgCode'userNo=userNo'functionName=functionName'startTime=startTime'endTime=endTime'
 * timeCost=timeCost'deviceNumber=deviceNumber'deviceType=deviceType'appVersion=appVersion'status=status'errStack=errStack
 */
public class LogPublishEntity extends LogEntity implements Serializable{
    private static final String TAG="LogPublishEntity";

    /**
     * 追踪ID,全局唯一标识，建议使用UUID
     */
    private String traceId;
    /**
     * 生成日志数据的时间 YYYY-mm-dd HH:MM:SS:ms
     */
    private String timeStamp;
    /**
     *  PDA所属的组织编码，以W开头
     */
    private String orgCode;
    /**
     * Pda使用者的用户名，通常为工号
     */
    private String userNo;
    /**
     * 操作类型 功能名称（全局唯一）
     */
    private String functionName;
    /**
     * 请求开始的时间，精确到毫秒
     */
    private long startTime;
    /**
     * 请求结束的时间，精确到毫秒
     */
    private long endTime;
    /**
     *  从请求开始到请求结束所花费的时间，精确到毫秒
     */
    private long timeCost;
    /**
     * 设备号
     */
    private String deviceNumber;
    /**
     * 设备类型，细化到机型
     */
    private String deviceType;
    /**
     *  PDA模块的版本号
     */
    private String appVersion;
    /**
     * 服务器的响应状态
     */
    private String status;
    /**
     *  出错时详细的报错信息（出错时才需要） 非必须
     */
    private String errStack;


    @Override
    public String toString(){
        if(StringUtils.isEmpty(functionName)){
            Log.e(TAG,"functionName can not be null,please set it!");
            return null;
        }
        if(StringUtils.isZero(startTime)){
            Log.e(TAG,"startTime can not be null,please set it!");
            return null;
        }
        if(StringUtils.isZero(endTime)){
            Log.e(TAG,"endTime can not be null,please set it!");
            return null;
        }
        timeCost=endTime-startTime;
        if(StringUtils.isZero(timeCost)){
            Log.e(TAG,"timeCost can not be null,please set it!");
            return null;
        }
        LogPublishUtils logPublishUtils=LogPublishUtils.getInstance();
        traceId=logPublishUtils.getUUID();
        timeStamp=logPublishUtils.getTimeStamp();
        orgCode=logPublishUtils.getOrgCode();
        userNo=logPublishUtils.getUserNo();
        deviceNumber=logPublishUtils.getDeviceNumber();
        deviceType=logPublishUtils.getDeviceType();
        appVersion=logPublishUtils.getAppVersion();
//“traceId=traceId'timeStamp=timeStamp'orgCode=orgCode'userNo=userNo'functionName=functionName'startTime=startTime'endTime=endTime'timeCost=timeCost'
// deviceNumber=deviceNumber'deviceType=deviceType'appVersion=appVersion'status=status'errStack=errStack”
        StringBuilder builder=new StringBuilder();
//        builder.append("\"");
        builder.append("traceId=").append(traceId).append("'");
        builder.append("timeStamp=").append(timeStamp).append("'");
        builder.append("orgCode=").append(orgCode).append("'");
        builder.append("userNo=").append(userNo).append("'");
        builder.append("functionName=").append(functionName).append("'");
        builder.append("startTime=").append(startTime).append("'");
        builder.append("endTime=").append(endTime).append("'");
        builder.append("timeCost=").append(timeCost).append("'");
        builder.append("deviceNumber=").append(deviceNumber).append("'");
        builder.append("deviceType=").append(deviceType).append("'");
        builder.append("appVersion=").append(appVersion).append("'");
        builder.append("status=").append(status).append("'");
        builder.append("errStack=").append(errStack);
//        builder.append("\"");
        return builder.toString();
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(long timeCost) {
        this.timeCost = timeCost;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrStack() {
        return errStack;
    }

    public void setErrStack(String errStack) {
        this.errStack = errStack;
    }
}
