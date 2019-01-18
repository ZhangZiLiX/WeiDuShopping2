package com.bwie.weidushopping.homepage.fragmentwode.modelwode;

import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.utils.OKHttpUtils;

import java.lang.reflect.Type;

/**
 * date:2019/1/10
 * author:张自力(DELL)
 * function:  我的这一层的数据网址请求层
 *
 */

public class AllsubClassesModel {

    //1 定义一个购物车Model层的get请求方法  传请求头的网络请求方法
    public void getAllsubClassesDataM(String url, int userId, String sessionId, ICallBack callBack, Type type){
        //调用网络请求工具类
        OKHttpUtils.getInstance().getGouWuCheData(url,userId,sessionId,callBack,type);
    }

}
