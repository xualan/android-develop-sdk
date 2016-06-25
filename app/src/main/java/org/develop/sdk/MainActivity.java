package org.develop.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.develop.annotationoptions.Exception.ViewInjectException;
import org.develop.annotationoptions.ViewAnnotation.ClickType;
import org.develop.annotationoptions.ViewAnnotation.ViewInject;
import org.develop.annotationoptions.ViewAnnotation.ViewInjectManager;
import org.develop.baseoptions.log.MyLog;
import org.develop.baseoptions.log.OnMyLogListener;
import org.develop.sdk.annotation.AnnotationActivity;


public class MainActivity extends Activity implements View.OnClickListener{


    @ViewInject(id = R.id.btn_annotation_test,clickType = ClickType.ON_CLICK)
    Button btnAnnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ViewInjectManager.initViewInject(this);
        } catch (ViewInjectException e) {
            e.printStackTrace();
        }

        MyLog.initContextResource(getApplication());
        MyLog.setOnMyLogListener(new OnMyLogListener() {
            @Override
            public void onLogPrint(String tag, MyLog.MyLogGrade grade, String msg) {
                //TODO 打印输出
            }
        });

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        try {
            ViewInjectManager.initViewInject(this);
        } catch (ViewInjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_annotation_test:
                MyLog.d("tag","-------msg-------");
                startActivity(new Intent(this, AnnotationActivity.class));
                break;
        }
    }
}
