package com.example.test.foregroundservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.foregroundservice.gps.GPSCheckStateTask;
import com.example.test.foregroundservice.gps.GPSEntity;

import org.develop.baseoptions.log.MyLog;

public class MainServiceActivity extends Activity {

    private static final String TAG="MainServiceActivity";
    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);
        btnStart= (Button) findViewById(R.id.start_service);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForegroundService();
            }
        });
        btnStop= (Button) findViewById(R.id.stop_service);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopForegroundService();
            }
        });

    }

    private void startForegroundService(){
        ForeServiceUtils.startService(this);
        Toast.makeText(this,"已启动",Toast.LENGTH_SHORT).show();
    }
    private void stopForegroundService(){
        ForeServiceUtils.stopService(this);
        Toast.makeText(this,"已停止",Toast.LENGTH_SHORT).show();
    }
}
