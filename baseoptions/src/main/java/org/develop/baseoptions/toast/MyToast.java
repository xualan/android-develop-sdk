package org.develop.baseoptions.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.develop.baseoptions.R;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:MyToast
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月25日18:13
 * <br/>
 * 功能描述：
 * <br/>
 */
public class MyToast {

    public static void show(Context context,String msg){
        Toast toast=new Toast(context);
        View view= LayoutInflater.from(context).inflate(R.layout.toast_default,null);
        TextView textView= (TextView) view.findViewById(R.id.toast_default_text);
        textView.setText(msg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}