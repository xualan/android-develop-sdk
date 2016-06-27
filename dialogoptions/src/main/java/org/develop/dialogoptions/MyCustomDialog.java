package org.develop.dialogoptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * ClassName:MyCustomDialog
 * 作者：xubaolun
 * 工号：337965
 * 创建时间：2016年 06月16日10:41
 * 功能描述：
 */
public class MyCustomDialog {
    static DialogInjector inster;
    public MyCustomDialog(DialogInjector inster){
        this.inster=inster;
        dialog=new HHCustomDialog();
    }
    private HHCustomDialog dialog;
    public HHCustomDialog getDialog(){
        return dialog;
    }

    public void dismiss(){
        getDialog().dismiss();
    }

    public static class HHCustomDialog extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final AlertDialog dialog=new AlertDialog.Builder(getActivity(), inster.getDialogStyle()).create();
            LayoutInflater inflater=getActivity().getLayoutInflater();
            View view=inflater.inflate(inster.getDialogView(),null);
            inster.initDialogView(view,this);
            dialog.setView(view);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.i("XBL","onAttach----------------------------------------");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.i("XBL","onDetach----------------------------------------");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i("XBL","onCreate----------------------------------------");
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            super.onCancel(dialog);
            Log.i("XBL","onCancel----------------------------------------");
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            Log.i("XBL","onDismiss----------------------------------------");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.i("XBL","onStart----------------------------------------");
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.i("XBL","onActivityCreated----------------------------------------");
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Log.i("XBL","onSaveInstanceState----------------------------------------");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.i("XBL","onStop----------------------------------------");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.i("XBL","onDestroyView----------------------------------------");
        }
    }


}
