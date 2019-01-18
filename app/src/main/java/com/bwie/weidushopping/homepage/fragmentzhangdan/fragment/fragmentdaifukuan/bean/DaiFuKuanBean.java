package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaifukuan.bean;

import java.util.List;

/**
 * date:2019/1/14
 * author:张自力(DELL)
 * function:  代付款
 */

public class DaiFuKuanBean {


    /**
     * orderList : []
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<?> orderList;

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

    public List<?> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<?> orderList) {
        this.orderList = orderList;
    }
}
