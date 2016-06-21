package org.develop.baseoptions.log;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:OnMyLogListener
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月24日13:40
 * <br/>
 * 功能描述：MyLog打印监听
 * <br/>
 */
public interface OnMyLogListener {
    void onLogPrint(String tag, MyLog.MyLogGrade grade,String msg);

}