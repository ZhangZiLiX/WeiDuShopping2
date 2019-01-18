package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean;

import java.util.List;

/**
 * date:2019/1/11
 * author:张自力(DELL)
 * function:  请求网址查询我的收货地址的数据封装类
 *
 * Get
 * http://172.17.8.100/small/user/verify/v1/receiveAddressList
 */

public class MyAddressBean {


    /**
     * result : [{"address":"北京市海淀区","createTime":1545145455000,"id":263,"phone":"15910975491","realName":"星空","userId":175,"whetherDefault":2,"zipCode":"101010"},{"address":"北京市海淀区马连洼北路","createTime":1545147832000,"id":265,"phone":"15910975491","realName":"星空2","userId":175,"whetherDefault":1,"zipCode":"101010"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 北京市海淀区
         * createTime : 1545145455000
         * id : 263
         * phone : 15910975491
         * realName : 星空
         * userId : 175
         * whetherDefault : 2
         * zipCode : 101010
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
