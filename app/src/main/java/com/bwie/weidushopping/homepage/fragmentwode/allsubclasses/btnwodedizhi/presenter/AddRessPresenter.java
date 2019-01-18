package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.presenter;

import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean.MyAddressBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.view.IView;
import com.bwie.weidushopping.homepage.fragmentwode.modelwode.AllsubClassesModel;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * date:2019/1/11
 * author:张自力(DELL)
 * function:  我的地址的Presenter层
 */

public class AddRessPresenter {

    private IView mIView;
    private AllsubClassesModel mAllsubClassesModel;

    public void attach(IView iView){
        mIView = iView;
        mAllsubClassesModel = new AllsubClassesModel();
    }

    //创建一个方法
    public void getAddressData(int userid,String sessionid ){

        Type type = new TypeToken<MyAddressBean>(){}.getType();
        //http://172.17.8.100/small/user/verify/v1/receiveAddressList
        //http://mobile.bwstudent.com
        mAllsubClassesModel.getAllsubClassesDataM("http://mobile.bwstudent.com/small/user/verify/v1/receiveAddressList", userid, sessionid, new ICallBack() {
            @Override
            public void Success(Object o) {
                //强zhuan数据类型
                MyAddressBean myAddressBean = (MyAddressBean) o;
                if(myAddressBean.getResult()!=null){
                    mIView.myAddress(myAddressBean.getResult());
                }
            }

            @Override
            public void Failder(Exception e) {
                mIView.onFailder(e);
            }
        },type);

    }

    public void datach(){
        if(mIView!=null){
            mIView = null;
        }
    }

}
