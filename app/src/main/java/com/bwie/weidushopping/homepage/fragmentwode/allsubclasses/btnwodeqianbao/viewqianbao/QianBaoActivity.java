package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.viewqianbao;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.myutilsclass.MyUtils;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.moneybean.QianBaoBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.presenter.Presenter;

/**
 * 我的Fragment  界面的  我的钱包
 */

public class QianBaoActivity extends AppCompatActivity implements IViewQianBao {
    //http://mobile.bwstudent.com
    //http://172.17.8.100/small/user/verify/v1/findUserWallet?page=1&count=10
    private String url = "http://mobile.bwstudent.com/small/user/verify/v1/findUserWallet?page=1&count=10";
    private TextView txtYueQianbaoWdf;
    private SharedPreferences mIsyouhuiquan;
    private Presenter mPresenter;
    private int mUserid;
    private String mSessionid;
    private int mBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_bao);
        //1 初始化控件
        initView();
        //2 初始化sp
        initSP();
        //4 初始化list 和  adapter
        initListAndAdapter();
        //3 初始化Presenter
        initPresenter();

    }

    /**
     * //4 初始化list 和  adapter
     *
     *  detailList  展示用户的消费记录
     *
     * */
    private void initListAndAdapter() {

    }

    /**
     * //3 初始化Presenter
     * */
    private void initPresenter() {
        mPresenter = new Presenter();
        mPresenter.attach(this);

        //用户使用Intent得到穿过来的userid 和 sessionid  通过p对象传递
        mPresenter.getQianBaoDataP(url,mUserid,mSessionid);
    }

    /**
     * //2 初始化sp
     * */
    private void initSP() {
        //优惠券是否领取
        mIsyouhuiquan = getSharedPreferences("isyouhuiquan", MODE_PRIVATE);
        if(mIsyouhuiquan.getBoolean("isyouhuitrue",false)){
            //如果为true  则表明用户领取了  就赋值
            mBalance+=100;
            txtYueQianbaoWdf.setText(mBalance+"");
        }


        //通过工具类  得到存储的userid  和  sessionid
        mUserid = (int) MyUtils.getData(this, "userid", 0);
        mSessionid = (String) MyUtils.getData(this, "sessionid", "");

    }

    /**
     * 1 初始化控件
     * */
    private void initView() {
        txtYueQianbaoWdf = (TextView) findViewById(R.id.txt_yue_qianbao_wdf);//钱包的余额
    }

    /**
     * 通过实现接口  实现的抽象方法
     *
     * */
    @Override
    public void getMoneyDataI(QianBaoBean qianBaoBean) {
        if(qianBaoBean.getResult()!=null){
            mBalance = qianBaoBean.getResult().getBalance();
            txtYueQianbaoWdf.setText(mBalance +"");
        }
    }

    @Override
    public void getFailder(Exception e)
    {
        MyUtils.toastShow(this,e.getMessage());
    }

    /**
     * 销毁
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.datach();
        }
    }
}
