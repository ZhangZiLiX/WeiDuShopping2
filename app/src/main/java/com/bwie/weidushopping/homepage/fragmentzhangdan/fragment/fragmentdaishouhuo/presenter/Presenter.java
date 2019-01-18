package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaishouhuo.presenter;


import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.bean.QuanBuDingDanBean;
import com.bwie.weidushopping.homepage.fragmentzhangdan.view.IView;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.model.Model;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  首页的  逻辑Presenter层
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

    /***
     * //用户根据关键字进行搜索的 数据请求方法
     *   需要用户传 关键字count  和  page
     *   status 要查询对应状态的订单数据
                0=查看全部  1=查看待付款  2=查看待收货  3=查看待评价  9=查看已完成
     *
     */
    //4 全部订单的数据请求方法
    public void getQuanBuDingDanDataP(int status,int page,int count,int userId,String sessionId){
        //http://172.17.8.100/small/order/verify/v1/findOrderListByStatus?status=
        //http://mobile.bwstudent.com
        String url = "http://mobile.bwstudent.com/small/order/verify/v1/findOrderListByStatus?status=";
        url = url+status+"&page="+page+"&count="+count;
        //定义一个泛型
        Type type = new TypeToken<QuanBuDingDanBean>(){}.getType();

        // 调用Post请求方式
        mModel.getGouWuCheDataM(url, userId, sessionId, new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                QuanBuDingDanBean quanBuDingDanBean = (QuanBuDingDanBean) o;
                if(quanBuDingDanBean!=null){
                    mIView.getQuanBuDingDanDataWDZDF(quanBuDingDanBean.getOrderList());
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //4 删除订单的数据请求方法
    public void getDeleteDingDanP(int userId,String sessionId,String orderId){
        //http://172.17.8.100/small/order/verify/v1/findOrderListByStatus?status=
        //http://mobile.bwstudent.com
        String url = "http://mobile.bwstudent.com/small/order/verify/v1/deleteOrder";
        url = url+orderId;
        //定义一个泛型
        Type type = new TypeToken<QuanBuDingDanBean>(){}.getType();

        // 调用Post请求方式
        mModel.deleteDataM(url, userId, sessionId, new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                QuanBuDingDanBean quanBuDingDanBean = (QuanBuDingDanBean) o;
                if(quanBuDingDanBean!=null){
                    mIView.getQuanBuDingDanDataWDZDF(quanBuDingDanBean.getOrderList());
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
