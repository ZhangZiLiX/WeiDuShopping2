package com.bwie.weidushopping.ourcommon.model;


import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.utils.OKHttpUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * date:2018/11/14
 * author:张自力(DELL)
 * function:  实现网路请求的Model层
 */

public class Model {

    //1 定义一个Model层的get请求方法
    public void getDataM(String url, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getData(url,callBack,type);
    }

    //1 定义一个购物车Model层的get请求方法  传请求头的网络请求方法
    public void getGouWuCheDataM(String url,int userId,String sessionId, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getGouWuCheData(url,userId,sessionId,callBack,type);
    }

    //2 定义一个Model层的Post请求方法
    public void postDataM(String url, HashMap<String, String> map, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getDataPost(url,map,callBack,type);
    }

    //2 定义一个Model层的Post请求方法  加入头参数的
    public void postTouCanDataM(String url,HashMap<String,String> map,int userId,String sessionId, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getDataTouCanPostU(url,map,userId,sessionId,callBack,type);
    }

    //2 定义一个Model层的Post请求方法  加入头参数  上传头像
    public void postHeadImageM(String url, File img, int userId, String sessionId, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getHeadPostImage(url,img,userId,sessionId,callBack,type);
    }

    //3 定义一个Model层的Put请求方法
    public void putDataM(String url,int isuserid,String sessionId,HashMap<String, String> map, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getDataPut(url,isuserid,sessionId,map,callBack,type);
    }


}
