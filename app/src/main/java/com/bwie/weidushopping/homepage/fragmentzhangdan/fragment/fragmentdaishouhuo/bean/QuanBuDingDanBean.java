package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaishouhuo.bean;

import java.util.List;

/**
 * date:2018/12/18
 * author:张自力(DELL)
 * function:  订单Fragment  全部订单Bean
 *
 * http://172.17.8.100/small/order/verify/v1/findOrderListByStatus?status=0&page=1&count=5
 */

public class QuanBuDingDanBean {


    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":24,"commodityName":"百搭小白鞋 女款 时尚舒适板鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/bx/7/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/5.jpg","commodityPrice":149,"orderDetailId":397}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20181218095724597175","orderStatus":1,"payAmount":149,"payMethod":1,"userId":175}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

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

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":24,"commodityName":"百搭小白鞋 女款 时尚舒适板鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/bx/7/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/5.jpg","commodityPrice":149,"orderDetailId":397}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20181218095724597175
         * orderStatus : 1
         * payAmount : 149
         * payMethod : 1
         * userId : 175
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 24
             * commodityName : 百搭小白鞋 女款 时尚舒适板鞋
             * commodityPic : http://172.17.8.100/images/small/commodity/nx/bx/7/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/7/5.jpg
             * commodityPrice : 149
             * orderDetailId : 397
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

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

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
