package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.view;

import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.bean.UpdatePasswordBean;
import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;

/**
 * date:2018/12/14
 * author:张自力(DELL)
 * function:  我的Fragment的 点击我的资料按钮  点击进行修改资料的Presenter层
 */

public interface IView {

    //修改密码的接口
    void updatePassword(UpdatePasswordBean updatePasswordBean);
    void failder(Exception e);

}
