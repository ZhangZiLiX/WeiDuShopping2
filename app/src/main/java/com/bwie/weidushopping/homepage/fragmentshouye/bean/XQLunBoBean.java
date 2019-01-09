package com.bwie.weidushopping.homepage.fragmentshouye.bean;

import java.util.List;

/**
 * date:2019/1/8
 * author:张自力(DELL)
 * function:
 */

public class XQLunBoBean {
    public XQLunBoBean() {

    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    private List<String> result;


    private static class LunBoBean{
        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private String imageUrl;
        public LunBoBean(String imageUrl) {
            this.imageUrl = imageUrl;
        }




    }

}
