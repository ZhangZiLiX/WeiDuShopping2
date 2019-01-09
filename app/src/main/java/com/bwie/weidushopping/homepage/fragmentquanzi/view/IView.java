package com.bwie.weidushopping.homepage.fragmentquanzi.view;

import com.bwie.weidushopping.homepage.fragmentquanzi.bean.DianZanBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.QuanZiBean;

import java.util.List;

/**
 * date:2018/12/11
 * author:张自力(DELL)
 * function:  圈子的接口
 */

public interface IView {

    //圈子查询
    void QuanZi(List<QuanZiBean.ResultBean> list);
    //圈子点赞
    void DianZan(DianZanBean dianZanBean);

    void failder(Exception e);

}
