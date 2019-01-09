package com.bwie.weidushopping.homepage.fragmentshouye.bean;

import java.util.List;

/**
 * date:2018/12/13
 * author:张自力(DELL)
 * function: 首页Fragment 搜索
 *
 *     用户通过关键字进行查询的封装类
 *
 *     http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=%E6%89%8B%E6%9C%BA&page=3&count=5
 */

public class KeySeacherBean {

    /**
     * result : [{"commodityId":103,"commodityName":"【送自拍杆】魅族 魅蓝 E3 64G 全面屏 全网通手机","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/4/1.jpg","price":1638,"saleNum":0},{"commodityId":114,"commodityName":"羽博 可悬挂无线蓝牙便携音响 布面工艺方形户外超重低音蓝牙音响车载手机迷你蓝牙音箱","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/yyyl/1/1.jpg","price":49,"saleNum":0},{"commodityId":110,"commodityName":"轻松小熊系列 苹果手机自拍杆 三级美颜补光灯 苹果华为便携自拍","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sjpj/4/1.jpg","price":98,"saleNum":0}]
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
         * commodityId : 103
         * commodityName : 【送自拍杆】魅族 魅蓝 E3 64G 全面屏 全网通手机
         * masterPic : http://172.17.8.100/images/small/commodity/sjsm/sj/4/1.jpg
         * price : 1638
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
