package org.develop.dialogoptions;

import android.app.Activity;
import android.app.DialogFragment;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:MyDialog
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月25日13:43
 * <br/>
 * 功能描述：
 * <br/>
 */
public class MyDialog {

    static DialogFragment newFragment;

    public static MyCustomDialog showDialog(Activity activity,DialogInjector injector){
        MyCustomDialog dialog=new MyCustomDialog(injector);
        dialog.getDialog().show(activity.getFragmentManager(),"dialog");
        return dialog;
    }
}