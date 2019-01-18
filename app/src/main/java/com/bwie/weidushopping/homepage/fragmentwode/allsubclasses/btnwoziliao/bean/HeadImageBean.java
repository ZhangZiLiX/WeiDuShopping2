package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.bean;

/**
 * date:2019/1/11
 * author:张自力(DELL)
 * function:  我的资料中的上传头像  数据封装类
 *
 *
 * post  file  需要请求头
 * http://172.17.8.100/small/user/verify/v1/modifyHeadPic
 *
 */

public class HeadImageBean {


    /**
     * headPath : http://172.17.8.100/images/small/head_pic/2019-01-11/20190111200740.jpg
     * message : 上传成功
     * status : 0000
     */

    private String headPath;
    private String message;
    private String status;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
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
