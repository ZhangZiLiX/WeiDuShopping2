package com.bwie.weidushopping.loginandzhucepage.loginpage.presenter;

import android.os.Handler;

import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;
import com.bwie.weidushopping.loginandzhucepage.loginpage.view.IView;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.model.Model;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  登录的  逻辑Presenter层
 */

public class LoginPresenter {

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
    //http://172.17.8.100/small/user/v1/login?phone=13833935348&pwd=123456
    public void getLoginDataP(String phone,String pwd){
        //String url = "http://172.17.8.100/small/user/v1/login";
        //http://mobile.bwstudent.com
        String url = "http://mobile.bwstudent.com/small/user/v1/login";
        //得到数据进行map创建存储
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("pwd",pwd);
        //定义一个泛型
        Type type = new TypeToken<LoginBean>(){}.getType();

        //注册  调用Post请求方式
        mModel.postDataM(url, map, new ICallBack() {
            @Override
            public void Success(final Object o) {
                //请求成功  使用Handler发送到子线程
                LoginBean loginBean = (LoginBean) o;
                mIView.LoginBean(loginBean);
            }

            @Override
            public void Failder(final Exception e) {
                //请求失败  将错误信息发送到子线程进行错误提示
                mIView.failder(e);
            }
        },type);
    }


    //上传头像的方法  上传头像接口有问题
    //http://172.17.8.100/small/user/v1/login?phone=13833935348&pwd=123456
    /*public void getWoDeTouXiangP(String imageString){
        String url = "http://172.17.8.100/small/user/verify/v1/modifyHeadPic";
        //得到数据进行map创建存储
        HashMap<String,String> map = new HashMap<>();
        map.put("image",imageString);
        //定义一个泛型
        Type type = new TypeToken<LoginBean>(){}.getType();

        //注册  调用Post请求方式
        mModel.postDataM(url, map, new ICallBack() {
            @Override
            public void Success(final Object o) {
                //请求成功  使用Handler发送到子线程
                LoginBean loginBean = (LoginBean) o;
                mIView.LoginBean(loginBean);
            }

            @Override
            public void Failder(final Exception e) {
                //请求失败  将错误信息发送到子线程进行错误提示
                mIView.failder(e);
            }
        },type);

    }*/

    //解除耦合
    public void datach(){
        //判断是否为空
        if(mIView!=null){
            mIView = null;
        }
    }

}
