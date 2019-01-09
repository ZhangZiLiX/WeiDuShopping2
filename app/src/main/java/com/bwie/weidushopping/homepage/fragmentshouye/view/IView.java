package com.bwie.weidushopping.homepage.fragmentshouye.view;

import com.bwie.weidushopping.homepage.fragmentshouye.bean.BannerBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.KeySeacherBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.ShopBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.XiangQingBean;

import java.util.List;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  首页Fragment界面   使用的IView接口
 */

public interface IView {

    // 定义轮播图请求数据使用的方法
    void BannerData(BannerBean bannerBean);

    //用户根据关键字进行查询商品
    void KeySearchData(List<KeySeacherBean.ResultBean> list);

    //定义商品类 热销新品  请求数据使用的方法
    void ShopDataRXXP(List<ShopBean.ResultBean.RxxpBean.CommodityListBeanXX> list);
    //定义商品类 品质生活 请求数据使用的方法
    void ShopDataPZSH(List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> list);
    //定义商品类 魔力时尚  请求数据使用的方法
    void ShopDataMLSS(List<ShopBean.ResultBean.MlssBean.CommodityListBean> list);

    //详情  http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=5
    void XiangQingData(XiangQingBean xiangQingBean);

    //登录失败进行提示
    void failder(Exception e);

}
