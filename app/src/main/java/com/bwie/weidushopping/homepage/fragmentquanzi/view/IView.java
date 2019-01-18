package com.bwie.weidushopping.homepage.fragmentquanzi.view;

import com.bwie.weidushopping.homepage.fragmentquanzi.bean.DianZanBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.FaBuBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.QuanZiBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.bean.WoDeQuanZiBean;

import java.util.List;

/**
 * date:2018/12/11
 * author:张自力(DELL)
 * function:  圈子的接口
 */

public interface IView {

    //圈子查询
    void QuanZi(List<QuanZiBean.ResultBean> list);
    void WoDeQuanZi(List<WoDeQuanZiBean.ResultBean> list);
    //圈子点赞
    void DianZan(DianZanBean dianZanBean);
    //发布
    void FaBu(FaBuBean faBuBean);


    void failder(Exception e);

}
