package com.bwie.weidushopping.ourcommon.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * date:2018/12/15
 * author:张自力(DELL)
 * function:  判断网络是否可用的工具类
 */

public class NetWorkUtils {

    //判断网络是否正常的方法  点击登录  和  注册 的时候判断网络是否正常
    public static boolean isNetWorkAvailable(Context context){
        boolean available = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo!=null){
            available = networkInfo.isAvailable();
        }

        return available;
    }


}
