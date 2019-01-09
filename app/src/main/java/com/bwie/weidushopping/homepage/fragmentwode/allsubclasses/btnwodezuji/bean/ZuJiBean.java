package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodezuji.bean;

import java.util.List;

/**
 * date:2018/12/12
 * author:张自力(DELL)
 * function:
 *     我的Fragment的 我的足迹封装类
 *
 *     使用接口：http://172.17.8.100/small/commodity/verify/v1/browseList?page=5&count=5
 *        别忘了加上请求头才能访问到  在请求工具类中
 */

public class ZuJiBean {

    /**
     * result : []
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<?> result;

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

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
