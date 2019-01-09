package com.bwie.weidushopping.loginandzhucepage.zhucepage.bean;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  用户注册的数据封装类
 *
 *     注册接口使用：
 *         http://172.17.8.100/small/user/v1/register?phone=13833935348&pwd=123456
 *
 */

public class ZhuCeBean {

    /**
     * message : 注册成功
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
