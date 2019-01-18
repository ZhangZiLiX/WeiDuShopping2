package com.bwie.weidushopping.homepage.fragmentshouye.presenter;


import com.bwie.weidushopping.homepage.fragmentshouye.bean.AddCar;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.BannerBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.KeySeacherBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.ShopBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.XiangQingBean;
import com.bwie.weidushopping.homepage.fragmentshouye.view.IView;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.model.Model;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  首页的  逻辑Presenter层
 */

public class Presenter {

    //初始化Iview  和  Model层对象
    private IView mIView;
    private Model mModel;

    //创建关联方法
    public void attach(IView iView){
        mIView = iView;
        mModel = new Model();
    }

    //创建一个方法，进行调用Model层的网络数据请求

    /***
     * //用户根据关键字进行搜索的 数据请求方法
     *   需要用户传 关键字keyword  和  page
     * http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=%E6%89%8B%E6%9C%BA&page=3&count=5
     */
    public void getKeySearchDataP(String keyword,int page){
        //http://mobile.bwstudent.com
        //http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?count=5&page
        String url = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword?count=5&page=";
        //拼接
        url = url+page+"&keyword="+keyword;
        //定义一个泛型
        Type type = new TypeToken<KeySeacherBean>(){}.getType();

        //注册  调用Post请求方式
        mModel.getDataM(url, new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                KeySeacherBean keySeacherBean = (KeySeacherBean) o;
                if(keySeacherBean!=null){
                    List<KeySeacherBean.ResultBean> result = keySeacherBean.getResult();
                    mIView.KeySearchData(result);
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }


    //1 Banner轮播图
    //http://172.17.8.100/small/user/v1/login?phone=13833935348&pwd=123456
    public void getBannerDataP(){
        //定义一个泛型
        Type type = new TypeToken<BannerBean>(){}.getType();
        //注册  调用Post请求方式
        //http://mobile.bwstudent.com
        //http://172.17.8.100/small/commodity/v1/bannerShow
        mModel.getDataM("http://mobile.bwstudent.com/small/commodity/v1/bannerShow", new ICallBack() {
            @Override
            public void Success(Object o) {
               //成功返回数据
                BannerBean bannerBean = (BannerBean) o;
                mIView.BannerData(bannerBean);
            }
            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //2热销新品的数据请求方法
    public void getRXXPDataP(){
        //定义一个泛型
        Type type = new TypeToken<ShopBean>(){}.getType();
        //注册  调用Post请求方式
        //http://mobile.bwstudent.com
        //http://172.17.8.100/small/commodity/v1/commodityList
        mModel.getDataM("http://mobile.bwstudent.com/small/commodity/v1/commodityList", new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                ShopBean shopBean = (ShopBean) o;
                if(shopBean!=null){
                    ShopBean.ResultBean resultBean = shopBean.getResult();
                    List<ShopBean.ResultBean.RxxpBean> rxxp = resultBean.getRxxp();
                    mIView.ShopDataRXXP(rxxp.get(0).getCommodityList());
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //3魔力时尚的数据请求方法
    public void getMLSSDataP(){
        //定义一个泛型
        Type type = new TypeToken<ShopBean>(){}.getType();
        //注册  调用Post请求方式
        //http://mobile.bwstudent.com
        //http://172.17.8.100/small/commodity/v1/commodityList
        mModel.getDataM("http://mobile.bwstudent.com/small/commodity/v1/commodityList", new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                ShopBean shopBean = (ShopBean) o;
                if(shopBean!=null){
                    ShopBean.ResultBean resultBean = shopBean.getResult();
                    mIView.ShopDataMLSS(resultBean.getMlss().get(0).getCommodityList());
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //4品质生活的数据请求方法
    public void getPZSHDataP(){
        //定义一个泛型
        Type type = new TypeToken<ShopBean>(){}.getType();
         //http://mobile.bwstudent.com
        //http://172.17.8.100/small/commodity/v1/commodityList
        //注册  调用Post请求方式
        mModel.getDataM("http://mobile.bwstudent.com/small/commodity/v1/commodityList", new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                ShopBean shopBean = (ShopBean) o;
                if(shopBean!=null){
                    ShopBean.ResultBean resultBean = shopBean.getResult();
                    mIView.ShopDataPZSH(resultBean.getPzsh().get(0).getCommodityList());
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }

    //4品质生活的数据请求方法
    public void getXiangQingDataP(int commodityId,int userId,String sessionId){
        //String url = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=";
        //http://mobile.bwstudent.com
        String url = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityDetailsById?commodityId=";
        url = url+commodityId;
        //定义一个泛型
        Type type = new TypeToken<XiangQingBean>(){}.getType();

        //注册  调用Post请求方式
        mModel.getGouWuCheDataM(url, userId, sessionId, new ICallBack() {
            @Override
            public void Success(Object o) {
                //成功返回数据
                XiangQingBean xiangQingBean = (XiangQingBean) o;
                if(xiangQingBean!=null){
                    mIView.XiangQingData(xiangQingBean);
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }


    //3同步购物车
    public void getAddCarP(String sessionid,int userid,Map<String,String> map){
        //只加入一条
        String url ="http://mobile.bwstudent.com/small/order/verify/v1/syncShoppingCart";
       /* String str="[";
        str+="{\"commodityId\":"+commodityid+",\"count\":"+1+"},";
        String substring = str.substring(0, str.length() - 1);
        substring+="]";
        Map<String,String> map = new HashMap<>();
        map.put("data",substring);
*/

        //得到数据进行map创建存储
        /*HashMap<String,String> map = new HashMap<>();
        map.put("commodityId",commodityid+"");
        map.put("count",count+"");*/

        //定义一个泛型
        Type type = new TypeToken<AddCar>(){}.getType();
        //注册  调用Post请求方式
        //http://172.17.8.100/small/order/verify/v1/syncShoppingCart
        //http://mobile.bwstudent.com
        mModel.putDataM(url, userid, sessionid, map, new ICallBack() {
            @Override
            public void Success(Object o) {
                AddCar addCar = (AddCar) o;
                if(addCar!=null){
                    mIView.AddCar(addCar);
                }
            }

            @Override
            public void Failder(Exception e) {
                //请求失败
                mIView.failder(e);
            }
        },type);
    }
    //解除耦合
    public void datach(){
        //判断是否为空
        if(mIView!=null){
            mIView = null;
        }
    }

}
