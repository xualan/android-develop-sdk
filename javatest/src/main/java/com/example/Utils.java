package com.example;

/**
 * Created by 337965 on 2017/7/26.
 */

public class Utils {

    private Utils(){}

    private static final Utils instance=new Utils();

    public static Utils getInstance(){
        return instance;
    }

    public void toLog(Object object){
        object.toString();
    }


}
