package com.bwie.weidushopping.homepage.fragmentgouwuche.view;

import com.bwie.weidushopping.homepage.fragmentgouwuche.bean.GouWuCheBean;

import java.util.List;

/**
 * date:2018/12/15
 * author:张自力(DELL)
 * function:  这是滑动的fragment  购物车界面的IView
 */

public interface IView {
    //购物车查询
    void getSelectShoppingData(List<GouWuCheBean.ResultBean> list);
    void failder(Exception e);

}
