package com.example.test.cachelog.entity;


import com.alibaba.fastjson.JSONObject;
import com.example.test.cachelog.GPSEntity;
import com.example.test.cachelog.LogPublishUtils;


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

    public LogPublishEntity() {
        LogPublishUtils logPublishUtils= LogPublishUtils.getInstance();
        traceId=logPublishUtils.getTraceId();
        timeStamp=logPublishUtils.getTimeStamp();
        orgCode=logPublishUtils.getOrgCode();
        userNo=logPublishUtils.getUserNo();
        decNumber=logPublishUtils.getDeviceNumber();
        decType=logPublishUtils.getDeviceType();
        version=logPublishUtils.getAppVersion();
        startTime=logPublishUtils.getTimeStamp();
        GPSEntity gpsEntity=logPublishUtils.getLocation();
        latitude=gpsEntity.getLatitude();
        longitude=gpsEntity.getLongitude();
    }

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
    private String funcName;
    /**
     * 请求开始的时间，精确到毫秒
     */
    private String startTime;
    /**
     * 请求结束的时间，精确到毫秒
     */
    private String endTime;
    /**
     *  从请求开始到请求结束所花费的时间，精确到毫秒
     */
    private String timeCost;
    /**
     * 设备号
     */
    private String decNumber;
    /**
     * 设备类型，细化到机型
     */
    private String decType;
    /**
     *  PDA模块的版本号
     */
    private String version;
    /**
     * 服务器的响应状态
     */
    private String status;
    /**
     *  出错时详细的报错信息（出错时才需要） 非必须
     */
    private String errStack;
    /**
     * 纬度
     */
    private double latitude;

    /**
     * 经度
     */
    private double longitude;


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
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

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(String timeCost) {
        this.timeCost = timeCost;
    }

    public String getDecNumber() {
        return decNumber;
    }

    public void setDecNumber(String decNumber) {
        this.decNumber = decNumber;
    }

    public String getDecType() {
        return decType;
    }

    public void setDecType(String decType) {
        this.decType = decType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
