package com.bwie.weidushopping.ourcommon.inter;

/**
 * date:2018/11/14
 * author:张自力(DELL)
 * function:接口回调
 */

public interface ICallBack {

    //成功失败的抽象方法
    void Success(Object o);
    void Failder(Exception e);

}
