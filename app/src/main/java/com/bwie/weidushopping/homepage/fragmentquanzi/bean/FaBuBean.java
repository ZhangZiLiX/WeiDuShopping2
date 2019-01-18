package com.bwie.weidushopping.homepage.fragmentquanzi.bean;

/**
 * date:2019/1/11
 * author:张自力(DELL)
 * function:  圈子发布的数据封装类
 *
 * post
 * http://172.17.8.100/small/circle/verify/v1/releaseCircle
 */

public class FaBuBean {


    /**
     * message : 发布成功
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
