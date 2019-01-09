package com.bwie.weidushopping.homepage.fragmentzhangdan.view;

import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.bean.QuanBuDingDanBean;

import java.util.List;

/**
 * date:2018/12/18
 * author:张自力(DELL)
 * function:  我的订单界面Fragment  的 IView接口
 */

public interface IView {

    //全部订单查询
    void getQuanBuDingDanDataWDZDF(List<QuanBuDingDanBean.OrderListBean> listBeans);

    void failder(Exception e);

}
