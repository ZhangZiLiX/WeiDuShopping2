package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaishouhuo.bean;

/**
 * date:2018/12/18
 * author:张自力(DELL)
 * function:  我的订单Fragment   的  创建订单封装类
 *
 * http://172.17.8.100/small/order/verify/v1/createOrder
 *
 * orderInfo  [{"commodityId":24,"amount":1}]
 * totalPrice  149
 * addressId  265
 */

public class ChuangJianDingDanBean {


    /**
     * orderId : 20181218095724597175
     * message : 创建订单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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
