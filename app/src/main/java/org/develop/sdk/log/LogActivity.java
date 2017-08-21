package org.develop.sdk.log;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.cachelog.LogPublishUtils;
import com.example.test.cachelog.LogUtils;
import com.example.test.cachelog.ZipLogListener;
import com.example.test.cachelog.config.LogConfig;
import com.example.test.cachelog.entity.LogPublishEntity;
import com.example.test.cachelog.print.LogPrintToFiles;

import org.develop.annotationoptions.ViewAnnotation.ClickType;
import org.develop.annotationoptions.ViewAnnotation.ViewInject;
import org.develop.sdk.BaseActivity;
import org.develop.sdk.R;


public class LogActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG="LogActivity";
    @ViewInject(id = R.id.log_start_test,clickType = ClickType.ON_CLICK)
    Button btnStart;
    @ViewInject(id = R.id.clear_log,clickType = ClickType.ON_CLICK)
    Button btnClear;
    @ViewInject(id = R.id.zip_log,clickType = ClickType.ON_CLICK)
    Button btnZip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_start_test:
                startToTest();
                break;
            case R.id.clear_log:
                clearFiles();
                break;
            case R.id.zip_log:
                zip();
                break;
            default:
                break;
        }
    }


    private void zip(){

        LogPrintToFiles logPrintToFiles=LogPrintToFiles.getInstance();
        logPrintToFiles.uploadLog(new ZipLogListener() {
            @Override
            public void onSuccess(String fileName) {
                Log.i(TAG,"zip success-----:"+fileName);
            }

            @Override
            public void onError(String errorMsg) {
                Log.e(TAG,"zip onError-----:"+errorMsg);
            }
        });


    }

    private void clearFiles(){
        LogUtils.clearFiles();
    }

    private void startToTest(){
        LogConfig.getInstance().setWriteDelaySeconds(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    LogPublishEntity logPublish= new LogPublishEntity();
                    logPublish.setStartTime(System.currentTimeMillis()+"");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logPublish.setFuncName("FIRST THREAD MSG"+i);
                    logPublish.setEndTime(System.currentTimeMillis()+"");
                    LogUtils.log(logPublish);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
//                    LogUtils.i(TAG+" SECOND THREAD","MSG"+i);
                    LogPublishEntity logPublish= new LogPublishEntity();
                    logPublish.setStartTime(System.currentTimeMillis()+"");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logPublish.setFuncName("SECOND THREAD MSG"+i);
                    logPublish.setEndTime(System.currentTimeMillis()+"");
                    LogUtils.log(logPublish);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    LogPublishEntity logPublish= new LogPublishEntity();
                    logPublish.setStartTime(System.currentTimeMillis()+"");
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logPublish.setFuncName("THIRD THREAD MSG"+i);
                    logPublish.setEndTime(System.currentTimeMillis()+"");
                    LogUtils.log(logPublish);
                }
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;i++){
//                    LogUtils.i(TAG+" FORTH THREAD","MSG"+i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;i++){
//                    LogUtils.i(TAG+" FIFTH THREAD","MSG"+i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;i++){
//                    LogUtils.i(TAG+" SIXTH THREAD","MSG"+i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;i++){
//                    LogUtils.i(TAG+" SEVENTH THREAD","MSG"+i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;i++){
//                    LogUtils.i(TAG+" EIGHTH THREAD","MSG"+i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;i++){
//                    LogUtils.i(TAG+" TENTH THREAD","MSG"+i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }

}
