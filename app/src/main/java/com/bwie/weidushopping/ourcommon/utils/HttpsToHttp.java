package com.bwie.weidushopping.ourcommon.utils;

/**
 * date:2018/11/14
 * author:张自力(DELL)
 * function:  将Https  换为   http
 */

public class HttpsToHttp {

    //定义一个方法
    public static String myHttpsToHttp(String url){
       return url.replace("https","http");
    }

}
