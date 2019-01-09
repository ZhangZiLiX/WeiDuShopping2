package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.presenter;

import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.bean.UpdatePasswordBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.view.IView;
import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.model.Model;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * date:2018/12/14
 * author:张自力(DELL)
 * function:  我的Fragment的 点击我的资料按钮  点击进行修改资料的Presenter层
 */

public class ZiLiaoPresenter {

    //初始化Iview  和  Model层对象
    private IView mIView;
    private Model mModel;

    //创建关联方法
    public void attach(IView iView){
        mIView = iView;
        mModel = new Model();
    }

    //创建一个方法，进行调用Model层的网络数据请求

    //登录的方法
    //http://172.17.8.100/small/user/verify/v1/modifyUserPwd
    public void getUpdatePassword(String oldPwd,String isuserid,String sessionId,String newPwd){
        String url = "http://172.17.8.100/small/user/verify/v1/modifyUserPwd";
        //得到数据进行map创建存储
        HashMap<String,String> map = new HashMap<>();
        map.put("oldPwd",oldPwd);
        map.put("newPwd",newPwd);
        //定义一个泛型
        Type type = new TypeToken<UpdatePasswordBean>(){}.getType();

        //注册  调用Post请求方式
        mModel.putDataM(url,isuserid,sessionId, map, new ICallBack() {
            @Override
            public void Success(final Object o) {
                //请求成功  使用Handler发送到子线程
                UpdatePasswordBean updatePasswordBean = (UpdatePasswordBean) o;
                mIView.updatePassword(updatePasswordBean);
            }

            @Override
            public void Failder(final Exception e) {
                //请求失败  将错误信息发送到子线程进行错误提示
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
