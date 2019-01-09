package com.bwie.weidushopping.homepage.fragmentquanzi.presenter;


import android.net.Uri;

import com.bwie.weidushopping.homepage.fragmentquanzi.bean.DianZanBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.QuanZiBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.view.IView;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.BannerBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.ShopBean;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.model.Model;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  圈子的  逻辑Presenter层
 */

public class Presenter {

    //初始化Iview  和  Model层对象
    private IView mIView;
    private Model mModel;

    //创建关联方法
    public void attach(IView iView){
        mIView = iView;
        mModel = new Model();
    }

    //创建一个方法，进行调用Model层的网络数据请求

    //1 圈子
    //http://172.17.8.100/small/user/v1/login?phone=13833935348&pwd=123456
    public void getQuanZiDataP(int page){
        //拼接url
        String url = "http://172.17.8.100/small/circle/v1/findCircleList?count=5&page=";
        url = url+page;
        //定义一个泛型
        Type type = new TypeToken<QuanZiBean>(){}.getType();
        //注册  调用Post请求方式
        //详细写时  可以将page  和 count动态添加
        mModel.getDataM(url, new ICallBack() {
            @Override
            public void Success(Object o) {
               //成功返回数据
                QuanZiBean quanZiBean = (QuanZiBean) o;
                if(quanZiBean!=null){
                    mIView.QuanZi(quanZiBean.getResult());
                }
            }
            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //1 圈子 点赞
    //http://172.17.8.100/small/circle/verify/v1/addCircleGreat
    public void getQuanZiDianZanDataP(int circleid,String phone,String pwd,String userId,String sessionId){
        //得到数据进行map创建存储
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("pwd",pwd);
        //拼接url
        String url = "http://172.17.8.100/small/circle/verify/v1/addCircleGreat?circleId=";
        url = url+circleid;
        //定义一个泛型
        Type type = new TypeToken<DianZanBean>(){}.getType();
        //注册  调用Post请求方式
        //详细写时  可以将page  和 count动态添加
        mModel.postTouCanDataM(url,map,userId, sessionId, new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                DianZanBean dianZanBean = (DianZanBean) o;
                if(dianZanBean!=null){
                    mIView.DianZan(dianZanBean);
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //解除耦合
    public void datach(){
        //判断是否为空
        if(mIView!=null){
            mIView = null;
        }
    }

}
