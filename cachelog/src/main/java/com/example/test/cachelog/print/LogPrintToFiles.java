package com.example.test.cachelog.print;

import android.os.Environment;
import android.util.Log;

import com.example.test.cachelog.ZipLogListener;
import com.example.test.cachelog.ZipTools;
import com.example.test.cachelog.config.LogConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;

/**
 * 类名：LogPrintToFiles <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/25 <br/>
 * 功能描述：<br/>
 */
public class LogPrintToFiles<T> {

    private static final String TAG="LogPrintToFiles";

    private LogPrintToFiles(){
        loadLogConfig();
    }
    private static final LogPrintToFiles instance=new LogPrintToFiles();
    /**
     * 文件名称
     */
    private volatile String fileName="log.txt";
    /**
     * 文件目录
     */
    private String fileDirs="log";


    public static LogPrintToFiles getInstance(){
        return instance;
    }
    /**
     * 加载log配置
     */
    private void loadLogConfig(){
        LogConfig instance=LogConfig.getInstance();
        fileDirs= instance.getFilePath();
        fileName=instance.getFileSuffixName()+instance.getFileName();
        fileDirs=createFile(fileDirs);
    }

    /**
     * 清除日志文件
     */
    public void clearLog(){
        File file = new File(fileDirs, fileName);
        file.deleteOnExit();
    }


    /**
     * 输出日志文件，传入的T必须重写toString方法
     */
    public boolean printToFiles(List<T> list) {
        File file = new File(fileDirs, fileName);
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        boolean result=false;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            randomAccessFile=new RandomAccessFile(file,"rws");
            fileChannel=randomAccessFile.getChannel();
            FileLock fl = fileChannel.tryLock();
            if (fl.isValid()) {
                long fileLength = file.length();// 获取文件的长度即字节数
                randomAccessFile.seek(fileLength);
                for (T t : list) {
                    randomAccessFile.write(t.toString().getBytes());
                    String separator="\r\n";
                    randomAccessFile.write(separator.getBytes());
                }
                //写出文件成功
                result=true;
            }else{
                //其他线程正在调用该文件，稍后获取
                Log.i(TAG,"其他线程正在调用该文件，稍后获取");
                result=false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"WRITE TO FILES ERROR:"+e.getMessage());
            result=false;
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG,"WRITE TO FILES ERROR:"+e.getMessage());
            }
        }
        return result;
    }

    /**
     * 创建文件夹
     */
    private String createFile(String dir) {
        String status = Environment.getExternalStorageState();
        String filePath;
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            filePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        Log.i(TAG, "file path:" + filePath);
        File destDir = new File(filePath, dir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return destDir.getAbsolutePath();
    }

    public void uploadLog(final ZipLogListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //判断文件是否正在写入
                File file = new File(fileDirs, fileName);
                if (file.exists()) {
                    RandomAccessFile randomAccessFile = null;
                    FileChannel fileChannel = null;
                    FileLock fl = null;
                    try {
                        randomAccessFile=new RandomAccessFile(file,"rw");
                        fileChannel=randomAccessFile.getChannel();
                        fl = fileChannel.tryLock();
                        if (fl.isValid()) {
                            //以获取文件锁
                            //刷新文件名称
                            String zipFileName=LogConfig.getInstance().getFileSuffixName()+"Log.zip";
                            refreshFileName();
                            //文件压缩
                            ZipTools.zip(file.getAbsolutePath(),file.getParent(),zipFileName);
                            listener.onSuccess(zipFileName);
                        }else{
                            //隔一段时间，请求
                            Log.i(TAG,"文件锁正在被占用");
                            listener.onError("文件锁正在被占用");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        listener.onError(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        listener.onError(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                        listener.onError(e.getMessage());
                    }finally {
                        if(fileChannel!=null){
                            try {
                                fileChannel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(randomAccessFile!=null){
                            try {
                                randomAccessFile.close();
                                fileChannel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }).start();
    }
    private void refreshFileName(){
        LogConfig.getInstance().setFileSuffixName(System.currentTimeMillis()+"");
        loadLogConfig();
    }




}
