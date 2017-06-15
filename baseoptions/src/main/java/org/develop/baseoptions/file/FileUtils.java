package org.develop.baseoptions.file;

import android.os.Environment;

import java.io.File;

/**
 * className：FileUtils
 * creator：alan
 * create date：2017/6/15
 * descriptions：
 */
public class FileUtils {

    private FileUtils(){}

    /**
     *  创建人：alanXu
     *  创建时间：2017/6/15
     *  功能描述：
     *
     */
    public static String createFile(String dir) {
        String status = Environment.getExternalStorageState();
        String filePath;
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            filePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        File destDir = new File(filePath, dir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return destDir.getAbsolutePath();
    }

}
