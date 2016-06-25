# BaseOptions #
### 基础操作类库 ###

### 功能支持 ###
- log日志

	调用示例
	
	```
	MyLog.d("tag","-------msg-------");
	```

	若要进行日志输出：

	```
	MyLog.initContextResource(getApplication());
    MyLog.setOnMyLogListener(new OnMyLogListener() {
        @Override
        public void onLogPrint(String tag, MyLog.MyLogGrade grade, String msg) {
            //TODO 打印输出
        }
    });
	```

	关闭log输出

	```
	MyLog.closeLog();
	```

