package org.develop.annotationoptions.Exception;

import android.support.annotation.Nullable;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:ViewInjectException
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月23日15:29
 * <br/>
 * 功能描述：
 * <br/>
 */
public class ViewInjectException extends BaseException {

    private String msg;

    public ViewInjectException(@Nullable String msg) {
        super();
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}