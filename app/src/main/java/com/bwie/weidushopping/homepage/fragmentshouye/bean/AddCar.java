package com.bwie.weidushopping.homepage.fragmentshouye.bean;

/**
 * date:2019/1/14
 * author:张自力(DELL)
 * function:  加入购物车  同步
 *
 * http://172.17.8.100/small/order/verify/v1/syncShoppingCart
 * put请求  sessionid  userid
 *
 *
 */

public class AddCar  {


    /**
     * message : 同步成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
