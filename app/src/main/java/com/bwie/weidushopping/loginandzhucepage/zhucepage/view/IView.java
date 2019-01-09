package com.bwie.weidushopping.loginandzhucepage.zhucepage.view;

import com.bwie.weidushopping.loginandzhucepage.zhucepage.bean.ZhuCeBean;

import java.util.List;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  注册界面使用的IView接口
 */

public interface IView {

    //定义用户注册成功使用的方法
    void zhuCeBean(ZhuCeBean zhuCeBean);
    //注册失败进行提示
    void failder(Exception e);

}
