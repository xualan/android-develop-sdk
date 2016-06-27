package org.develop.sdk;

import android.app.Activity;
import android.os.Bundle;

import org.develop.annotationoptions.Exception.ViewInjectException;
import org.develop.annotationoptions.ViewAnnotation.ViewInjectManager;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
