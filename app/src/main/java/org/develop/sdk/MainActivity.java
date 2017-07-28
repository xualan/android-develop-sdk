package org.develop.sdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.develop.annotationoptions.Exception.ViewInjectException;
import org.develop.annotationoptions.ViewAnnotation.ClickType;
import org.develop.annotationoptions.ViewAnnotation.ViewInject;
import org.develop.annotationoptions.ViewAnnotation.ViewInjectManager;
import org.develop.baseoptions.log.MyLog;
import org.develop.baseoptions.toast.MyToast;
import org.develop.sdk.annotation.AnnotationActivity;
import org.develop.sdk.dialog.DialogActivity;
import org.develop.sdk.log.LogActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener{


    @ViewInject(id = R.id.btn_annotation_test,clickType = ClickType.ON_CLICK)
    Button btnAnnote;
    @ViewInject(id = R.id.btn_dialog_test,clickType = ClickType.ON_CLICK)
    Button btnDialog;

    @ViewInject(id = R.id.btn_toast_test,clickType = ClickType.ON_CLICK)
    Button btnToast;

    @ViewInject(id = R.id.btn_log_test,clickType = ClickType.ON_CLICK)
    Button btnLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                MyLog.i("tag","-------msg-------");
                startActivity(new Intent(this, AnnotationActivity.class));
                break;
            case R.id.btn_dialog_test:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.btn_toast_test:
                MyToast.show(this,"toast test");
                Log.i("TAG","-----------123123123");
                Log.v("TAG","-----------123123123");
                Log.d("TAG","-----------123123123");
                Log.e("TAG","-----------123123123");
                Log.w("TAG","-----------123123123");
                break;
            case R.id.btn_log_test:
                startActivity(new Intent(this, LogActivity.class));
                break;

        }
    }
}
