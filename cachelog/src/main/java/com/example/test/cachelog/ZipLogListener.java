package com.example.test.cachelog;

/**
 * 类名：ZipLogListener <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/27 <br/>
 * 功能描述：<br/>
 */
public interface ZipLogListener {
    void onSuccess(String fileName);
    void onError(String errorMsg);
}
