package org.develop.sdk.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.develop.annotationoptions.ViewAnnotation.ClickType;
import org.develop.annotationoptions.ViewAnnotation.ViewInject;
import org.develop.baseoptions.toast.MyToast;
import org.develop.dialogoptions.DialogInjector;
import org.develop.dialogoptions.MyDialog;
import org.develop.sdk.BaseActivity;
import org.develop.sdk.R;

public class DialogActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.dialog_btn_test,clickType = ClickType.ON_CLICK)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_btn_test:
                MyDialog.showDialog(this, new DialogInjector() {
                    @Override
                    public int getDialogStyle() {
                        return R.style.My_Dialog_Fullscreen;
                    }

                    @Override
                    public int getDialogView() {
                        return R.layout.dialog_default;
                    }

                    @Override
                    public void initDialogView(View view, final DialogFragment dialogFragment) {

                        TextView content= (TextView) view.findViewById(R.id.dialog_default_content);
                        TextView left= (TextView) view.findViewById(R.id.dialog_default_btn_left);
                        TextView right= (TextView) view.findViewById(R.id.dialog_default_btn_right);

                        left.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyToast.show(DialogActivity.this,"left");
                                dialogFragment.dismiss();
                            }
                        });
                        right.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyToast.show(DialogActivity.this,"right");
                                dialogFragment.dismiss();
                            }
                        });



                    }
                });
                break;
        }
    }
}
