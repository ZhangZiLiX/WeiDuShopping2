package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.moneybean;

import java.util.List;

/**
 * date:2019/1/9
 * author:张自力(DELL)
 * function: 我的钱包  封装类
 *
 *http://172.17.8.100/small/user/verify/v1/findUserWallet?page=1&count=10
 * get登录   需要userid   和  sessionid
 */

public class QianBaoBean {


    /**
     * message : 查询成功
     * result : {"balance":99999999,"detailList":[]}
     * status : 0000
     */

    private String message;
    private ResultBean result;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * balance : 99999999
         * detailList : []
         */

        private int balance;
        private List<?> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<?> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<?> detailList) {
            this.detailList = detailList;
        }
    }
}
