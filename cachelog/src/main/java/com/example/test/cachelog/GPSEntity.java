package com.example.test.cachelog;

/**
 * 类名：GPSEntity
 * 创建人：xubaolun
 * 工号：337965
 * 创建日期：2017/6/15
 * 功能描述：
 */
public class GPSEntity {
    private double longitude;
    private double latitude;
    private double altitude;
    private boolean gpsEnable;
    private String errorMsg;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public boolean isGpsEnable() {
        return gpsEnable;
    }

    public void setGpsEnable(boolean gpsEnable) {
        this.gpsEnable = gpsEnable;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
