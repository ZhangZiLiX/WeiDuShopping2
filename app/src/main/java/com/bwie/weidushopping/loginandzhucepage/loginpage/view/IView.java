package com.bwie.weidushopping.loginandzhucepage.loginpage.view;

import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  登录界面使用的IView接口
 */

public interface IView {

    //定义用登录成功使用的方法
    void LoginBean(LoginBean loginBean);
    //登录失败进行提示
    void failder(Exception e);

}
