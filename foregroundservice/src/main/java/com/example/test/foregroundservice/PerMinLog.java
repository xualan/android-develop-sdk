package com.example.test.foregroundservice;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author &150981 |sunjiaomin@deppon.com
 * @ClassName: MyLog
 * @Description:
 * @date 2015年3月21日 下午3:02:50
 */
public class PerMinLog {

    private static Boolean MYLOG_SWITCH = true; // 日志记录总开关
    private static Boolean MYLOG_WRITE_TO_FILE = true;// 日志写入文件总开关
    private static RankStatus MYLOG_TYPE = RankStatus.v;// 输入日志类型，v代表输出所有信息
    private static String MYLOG_PATH_SDCARD_DIR = createFile("gpslog");// 日志文件在sdcard中的路径
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 7;// 日志文件的最多保存天数
    private static String MYLOGFILEName = "PerMinLog.txt";// 本类输出的日志文件名称
    private static String simpleDate ="yyyy-MM-dd HH:mm:ss";
    private static DateFormat myLogSdf = new SimpleDateFormat(simpleDate);// 日志的输出格式
    private static DateFormat logFile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式

    private static FileWriter filerWriter =null;
    private static  BufferedWriter bufWriter =null;

    /**原子计数*/
    private static AtomicInteger mOpenCounter =new AtomicInteger();

    /**
     * 日志等级
     */
    public enum RankStatus {
        e,w,i,d,v
    }
	/*
     * static {
	 * if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED
	 * )){ MYLOG_PATH_SDCARD_DIR = Environment.getExternalStorageDirectory()
	 * .getAbsolutePath()+File.separator; } else { MYLOG_PATH_SDCARD_DIR =
	 * "/sdcard/"; } }
	 */

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: v
     * @Description: 这里用一句话描述这个方法的作用
     * @author wanggang077@deppon.com/200939
     */
    public static void v(String tag, Object msg) { // 通用信息
        v(tag, msg.toString());
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: w
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void w(String tag, Object msg) {// 警告信息
        w(tag, msg.toString());
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: e
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void e(String tag, Object msg) {// 错误信息
        e(tag, msg.toString());
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: d
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void d(String tag, Object msg) {// 调试信息
        d(tag, msg.toString());
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: i
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void i(String tag, Object msg) {// 普通信息
        i(tag, msg.toString());
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: v
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void v(String tag, String msg) {
        log(tag, msg, RankStatus.v);
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: w
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void w(String tag, String msg) {
        log(tag, msg, RankStatus.w);
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: e
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void e(String tag, String msg) {
        log(tag, msg, RankStatus.e);
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: d
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void d(String tag, String msg) {
        log(tag, msg, RankStatus.d);
    }

    /**
     * @param tag
     * @param msg 设定文件
     * @return void 返回类型
     * @Title: i
     * @Description: 
     * @author wanggang077@deppon.com/200939
     */
    public static void i(String tag, String msg) {
        log(tag, msg, RankStatus.i);
    }

    /**
     * @param tag
     * @param msg
     * @param level 设定文件
     * @return void 返回类型
     * @Title: log
     * @Description: 根据tag, msg和等级，输出日志
     * @author： 150981 |sunjiaomin@deppon.com
     */
    private static void log(String tag, String msg, RankStatus level) {

        String subMsg = "异常具体信息为null";

        //避免msg为空 时出现空指针异常
        if (!TextUtils.isEmpty(msg)) {
            subMsg = msg;
        }

        if (MYLOG_SWITCH) {

            if (RankStatus.v == level && MYLOG_TYPE == RankStatus.v) { // 输出错误信息
                Log.v(tag, subMsg);
                writeLogtoFile(level.name(), tag, subMsg);
            } else if (RankStatus.d == level && (RankStatus.d == MYLOG_TYPE || RankStatus.v == MYLOG_TYPE)) {
                Log.d(tag, subMsg);
                writeLogtoFile(level.name(), tag, subMsg);
            } else if (RankStatus.i == level && (RankStatus.i == MYLOG_TYPE || RankStatus.d == MYLOG_TYPE || RankStatus.v == MYLOG_TYPE)) {
                Log.i(tag, subMsg);
                writeLogtoFile(level.name(), tag, subMsg);
            } else if (RankStatus.w == level && (RankStatus.w == MYLOG_TYPE || RankStatus.i == MYLOG_TYPE || RankStatus.d == MYLOG_TYPE || RankStatus.v == MYLOG_TYPE)) {
                Log.w(tag, subMsg);
                writeLogtoFile(level.name(), tag, subMsg);
            }else if (RankStatus.e == level){ // 输出错误信息
                Log.e(tag, subMsg);
                writeLogtoFile(level.name(), tag, subMsg);
            }


        }
    }

    /**
     * @param mylogtype
     * @param tag
     * @param text
     * @return void 返回类型
     * @throws
     * @Title: writeLogtoFile
     * @Description:
     * @author： 150981 |sunjiaomin@deppon.com
     */
    private synchronized static void writeLogtoFile(String mylogtype, String tag, String text) {
        if (!MYLOG_WRITE_TO_FILE) {
            return ;
        }

        Date nowtime = new Date();
        String needWriteMessage =String.format("[%s] [%s] [%s]  %s" ,myLogSdf.format(nowtime),mylogtype,tag,text);

        try {
            openFile(nowtime);
            if(null != bufWriter){
                bufWriter.write(needWriteMessage);
                bufWriter.newLine();
                bufWriter.flush();
            }else{
                Log.e("MyLog未输出",needWriteMessage);
            }


        } catch (IOException e) {
            Log.e("MyLog未输出",needWriteMessage);

            Log.e("MyLog","异常:打开失败!");
            Log.e("MyLog",e.getMessage());
        }finally {
            try {
                close();
            } catch (IOException e) {
                Log.e("MyLog","异常:关闭失败!");
                Log.e("MyLog",e.getMessage());
            }
        }
    }

    /**
     * 打开 文件读写流
     * @param nowtime
     * @throws IOException
     */
    private static void openFile(final Date nowtime) throws IOException{

        if(mOpenCounter.incrementAndGet() == 1){
            String needWriteFiel = logFile.format(nowtime);
            File file = new File(MYLOG_PATH_SDCARD_DIR, needWriteFiel + MYLOGFILEName);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            // 第二个参数表示是不是要接上文件中原来的数据，true不进行覆盖
            filerWriter = new FileWriter(file, true);
            bufWriter = new BufferedWriter(filerWriter);
        }
    }

    /**
     * 关闭 文件读写流
     * @throws IOException
     */
    private static void close() throws IOException{
        if(mOpenCounter.decrementAndGet() == 0) {
            if (bufWriter != null) {
                bufWriter.close();
            }
            if (filerWriter != null) {
                filerWriter.close();
            }
        }
    }

    /**
     * @return void 返回类型
     * @Title: delFile
     * @Description: 删除指定的日志文件，这里我们删除七天前的日志
     * @author： 150981 |sunjiaomin@deppon.com
     */
    public static void delFile() {
        String needDelFiel = logFile.format(getDateBefore());
        File file = new File(MYLOG_PATH_SDCARD_DIR, needDelFiel + MYLOGFILEName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * @return Date 返回类型
     * @Title: getDateBefore
     * @Description: 获取指定日期
     * @author： 150981 |sunjiaomin@deppon.com
     */
    private static Date getDateBefore() {

        Date nowTime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowTime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }


    /**
     * 获取当前日志输出等级
     * @return
     */
    public static RankStatus getMylogType(){
        return MYLOG_TYPE;
    }

    /**
     * userName  xubaolun
     * userCode 337965
     * date 2016/9/20 10:17
     * desc ：创建文件夹
     *
     * @param dir 文件夹
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
