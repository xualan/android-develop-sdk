package org.develop.baseoptions.log;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;

import org.develop.baseoptions.file.FileUtils;

/**
 *  创建人：alanXu
 *  创建时间：2017/6/15
 *  功能描述：
 *
 */
public class MyLog extends BaseLog{


    @Override
    public void setLogCloseable(boolean closeable) {
        setClose(false);
    }

    @Override
    public void setWriteToFilesable(boolean writeToFilesable) {
        setWriteToFiles(true);
    }
}