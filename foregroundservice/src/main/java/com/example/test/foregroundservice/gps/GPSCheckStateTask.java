package com.example.test.foregroundservice.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import org.develop.baseoptions.log.MyLog;


/**
 * 类名：GPSCheckStateTask
 * 创建人：xubaolun
 * 工号：337965
 * 创建日期：2017/5/25
 * 功能描述：
 */
public class GPSCheckStateTask {

    private static final String TAG = "GPSCheckStateTask";
    private static final GPSCheckStateTask instance = new GPSCheckStateTask();

    private GPSCheckStateTask() {
    }

    public static GPSCheckStateTask getInstance() {
        return instance;
    }

    public GPSEntity checkGPSState(Context context) {
        MyLog.d(TAG, "checkGPSState-----------");
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        GPSEntity gpsEntity=new GPSEntity();

        //判断GPS是否正常启动
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            MyLog.d(TAG, "checkGPSState---------ERROR--");
            gpsEntity.setErrorMsg("gps 未开启或不可用");
            gpsEntity.setGpsEnable(false);
            return gpsEntity;
        }
        gpsEntity.setGpsEnable(true);
        //获取GPS位置信息
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            MyLog.d(TAG,"gps 权限未受理");
            gpsEntity.setErrorMsg("gps 权限未受理");
            gpsEntity.setGpsEnable(false);
            return gpsEntity;
        }
        //为获取地理位置信息时设置查询条件
        String bestProvider = lm.getBestProvider(getCriteria(), true);
        //获取位置信息
        //如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
        Location location= lm.getLastKnownLocation(bestProvider);
        if(location!=null){
            //经度
            double longitude=location.getLongitude();
            //高度
            double altitude=location.getAltitude();
            //纬度
            double latitude=location.getLatitude();

            gpsEntity.setLongitude(longitude);
            gpsEntity.setAltitude(altitude);
            gpsEntity.setLatitude(latitude);
        }else {
            gpsEntity.setErrorMsg("GPS信息不能获取");
            gpsEntity.setGpsEnable(false);
        }
        return gpsEntity;
    }

    /**
     * 返回查询条件
     * @return
     */
    private Criteria getCriteria(){
        Criteria criteria=new Criteria();
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        //设置是否需要方位信息
        criteria.setBearingRequired(false);
        //设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }



}
