package org.develop.dialogoptions;

import android.app.DialogFragment;
import android.view.View;

/**
 * ClassName:DialogInjector
 * 作者：xubaolun
 * 工号：337965
 * 创建时间：2016年 06月16日11:33
 * 功能描述：
 */
public interface DialogInjector {
    int getDialogStyle();

    int getDialogView();

    void initDialogView(View view, DialogFragment dialogFragment);
}
