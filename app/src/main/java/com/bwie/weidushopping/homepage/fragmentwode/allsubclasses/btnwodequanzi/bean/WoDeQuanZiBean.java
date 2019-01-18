package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.bean;

import java.util.List;

/**
 * date:2019/1/17
 * author:张自力(DELL)
 * function:  我的圈子封装类
 *
 * http://172.17.8.100/small/circle/verify/v1/findMyCircleById?page=1&count=5
 *
 * sessionid  userid
 */

public class WoDeQuanZiBean {


    /**
     * result : [{"commodityId":2,"content":"测试","createTime":1547241851000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-01-11/20190111200740.jpg","id":287,"image":"","nickName":"a3_8Q08V","userId":175},{"commodityId":2,"content":"测试","createTime":1547228951000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-01-11/20190111200740.jpg","id":280,"image":"","nickName":"a3_8Q08V","userId":175},{"commodityId":5,"content":"测试","createTime":1544984503000,"greatNum":4,"headPic":"http://172.17.8.100/images/small/head_pic/2019-01-11/20190111200740.jpg","id":209,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-16/7642320181216122143.png","nickName":"a3_8Q08V","userId":175}]
     * message : 查詢成功
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
         * commodityId : 2
         * content : 测试
         * createTime : 1547241851000
         * greatNum : 0
         * headPic : http://172.17.8.100/images/small/head_pic/2019-01-11/20190111200740.jpg
         * id : 287
         * image :
         * nickName : a3_8Q08V
         * userId : 175
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
