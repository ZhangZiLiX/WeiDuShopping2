package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.presenter;

import com.bwie.weidushopping.homepage.fragmentwode.modelwode.AllsubClassesModel;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.moneybean.QianBaoBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.viewqianbao.IViewQianBao;
import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * date:2019/1/9
 * author:张自力(DELL)
 * function:  钱包的Presenter层
 *
 */

public class Presenter {

    private IViewQianBao mIViewQianBao;
    private AllsubClassesModel mAllsubClassesModel;
    private Exception e;

    public void attach(IViewQianBao iViewQianBao){
        mIViewQianBao = iViewQianBao;
        mAllsubClassesModel = new AllsubClassesModel();
    }

    //定义一个请求方法
    public void getQianBaoDataP(String url, int userid,String sessionid){
        //String url = "http://172.17.8.100/small/user/verify/v1/findUserWallet?page=1&count=10";
        Type type = new TypeToken<QianBaoBean>(){}.getType();
        mAllsubClassesModel.getAllsubClassesDataM(url, userid, sessionid, new ICallBack() {
            @Override
            public void Success(Object o) {
                //强zhuan
                QianBaoBean qianBaoBean = (QianBaoBean) o;
                if(qianBaoBean.getResult()!=null){
                   mIViewQianBao.getMoneyDataI(qianBaoBean);
                }else{
                    mIViewQianBao.getFailder(e);
                }
            }

            @Override
            public void Failder(Exception e) {
                mIViewQianBao.getFailder(e);
            }
        },type);
    }


    public void datach(){
        if(mIViewQianBao !=null){
            mIViewQianBao = null;
        }
    }

}
