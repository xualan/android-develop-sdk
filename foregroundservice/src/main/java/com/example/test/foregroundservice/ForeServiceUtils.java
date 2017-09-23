package com.example.test.foregroundservice;

import android.content.Context;
import android.content.Intent;

/**
 * Created by 337965 on 2017/9/21.
 */

public class ForeServiceUtils {

    private ForeServiceUtils(){}


    public static void startService(Context context){
        Intent serviceIntent=new Intent(context,ForeService.class);
        context.startService(serviceIntent);
    }

    public static void stopService(Context context){
        Intent serviceIntent=new Intent(context,ForeService.class);
        context.stopService(serviceIntent);
    }
}
