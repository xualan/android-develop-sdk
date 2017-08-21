package com.example.test.cachelog.config;

/**
 * 类名：LogConfig <br/>
 * 创建人：alanXu <br/>
 * 创建时间：2017/7/25 <br/>
 * 功能描述：<br/>
 */
public class LogConfig {

    private LogConfig(){}

    private static final LogConfig instance=new LogConfig();

    public static LogConfig getInstance(){
        return instance;
    }
    /**
     * log是否关闭 ，默认不关闭
     */
    private boolean closeable=false;

    /**
     *  写出间隔时间，默认两秒
     */
    private int writeDelaySeconds=2;

    /**
     * 是否写出到文件
     */
    private boolean writeToFiles=true;

    /**
     * 日志文件名 ，默认 Log.txt
     */
    private String fileName="Log.txt";

    /**
     * 日志路径目录 ,默认 cacheLog
     */
    private String filePath="cacheLog";

    /**
     * 文件前缀名 ,默认无
     */
    private String fileSuffixName=System.currentTimeMillis()+"";

    /**
     * 文件最大限制
     */
    private int fileMaxSize=1024*1024*10;

    /**
     * 压缩文件目录
     */
    private String zipPath="zipLog";




    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }


    public int getWriteDelaySeconds() {
        return writeDelaySeconds;
    }

    public void setWriteDelaySeconds(int writeDelaySeconds) {
        this.writeDelaySeconds = writeDelaySeconds;
    }

    public boolean isWriteToFiles() {
        return writeToFiles;
    }

    public void setWriteToFiles(boolean writeToFiles) {
        this.writeToFiles = writeToFiles;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSuffixName() {
        return fileSuffixName;
    }

    public void setFileSuffixName(String fileSuffixName) {
        this.fileSuffixName = fileSuffixName;
    }

    public int getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(int fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
    }


    public String getZipPath() {
        return zipPath;
    }

    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }
}
