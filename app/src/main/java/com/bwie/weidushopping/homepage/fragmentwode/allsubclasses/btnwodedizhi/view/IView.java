package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.view;

import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean.MyAddressBean;

import java.util.List;

/**
 * date:2019/1/11
 * author:张自力(DELL)
 * function:  查询地址接口
 */

public interface IView {

    void myAddress(List<MyAddressBean.ResultBean> list);
    void onFailder(Exception e);

}
