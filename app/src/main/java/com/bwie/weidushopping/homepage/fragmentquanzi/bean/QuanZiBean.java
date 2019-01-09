package com.bwie.weidushopping.homepage.fragmentquanzi.bean;

import java.util.List;

/**
 * date:2018/12/11
 * author:张自力(DELL)
 * function:  圈子
 *
 *   //别忘了  在后面加上头参数
 *
 *   http://172.17.8.100/small/circle/v1/findCircleList?page=1&count=5
 */

public class QuanZiBean {

    /**
     * message : 查询成功
     * result : [{"commodityId":1,"content":"美丽的风景","createTime":1544579715000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":22,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-11/7906120181211195515.jpg","nickName":"38_i7T84","userId":43,"whetherGreat":2},{"commodityId":1,"content":"美丽的风景","createTime":1544579683000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":21,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-11/1968720181211195443.jpg","nickName":"38_i7T84","userId":43,"whetherGreat":2},{"commodityId":1,"content":"美丽的风景","createTime":1544579510000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":20,"image":"","nickName":"38_i7T84","userId":43,"whetherGreat":2},{"commodityId":1,"content":"小浣熊","createTime":1544579478000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":19,"image":"","nickName":"38_i7T84","userId":43,"whetherGreat":2},{"commodityId":1,"content":"xn发布的新圈子","createTime":1544577950000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":18,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-11/3962120181211192550.jpg","nickName":"fh_8iT60","userId":21,"whetherGreat":2}]
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
         * commodityId : 1
         * content : 美丽的风景
         * createTime : 1544579715000
         * greatNum : 0
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 22
         * image : http://172.17.8.100/images/small/circle_pic/2018-12-11/7906120181211195515.jpg
         * nickName : 38_i7T84
         * userId : 43
         * whetherGreat : 2
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
        private int whetherGreat;

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

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
