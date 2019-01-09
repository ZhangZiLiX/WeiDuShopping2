package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.application.MyApplication;
import com.bwie.weidushopping.baseactivity.BaseActionBar;
import com.bwie.weidushopping.db.NewsAddressDao;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean.NewsAddress;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.adddizhi.NewAddressActivity;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.xlvaddressadapter.AddNewAddressXLVAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的Fragment 的我的收货地址界面
 */
public class DiZhiActivity extends BaseActionBar implements View.OnClickListener {

    private XRecyclerView xlvAddressWdf;
    private Button btnNewaddaddressWdf;
    private NewsAddressDao mAddressDao;
    private List<NewsAddress> mList;
    private AddNewAddressXLVAdapter mAddNewAddressXLVAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_zhi);
        //1 初始化控件
        initView();
        //2 初始化Dao层
        initGreenDao();
        //3 xlv的 List  和 adapter设置
        setListAndAdapter();
        //4 删除数据
        setDeleteUserAddress();
        //5 开始查询数据库 展示地址信息
        setSelectUderAddress();
    }

    /**
     * //5 开始查询数据库 展示地址信息
     * */
    private void setSelectUderAddress() {
        List<NewsAddress> newsAddresses = mAddressDao.loadAll();//得到用户添加的地址
        if(newsAddresses!=null){
            mList.clear();
            mList.addAll(newsAddresses);
            mAddNewAddressXLVAdapter.notifyDataSetChanged();
        }
    }

    /**
     * //4 删除数据
     * */
    private void setDeleteUserAddress() {
        mAddNewAddressXLVAdapter.setDZOnClickListener(new AddNewAddressXLVAdapter.DZOnClickListener() {
            @Override
            public void onChanger(int id) {
                mAddressDao.deleteByKey((long) id);
                mAddressDao.deleteByKey((long) id);
                Toast.makeText(DiZhiActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * //3 xlv的 List  和 adapter设置
     * */
    private void setListAndAdapter() {
        //地址的list
        mList = new ArrayList<>();
        mAddNewAddressXLVAdapter = new AddNewAddressXLVAdapter(DiZhiActivity.this, mList);
        xlvAddressWdf.setAdapter(mAddNewAddressXLVAdapter);

        //设置刷新
        //设置监听 刷新
        xlvAddressWdf.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSelectUderAddress();
                        xlvAddressWdf.refreshComplete();
                    }
                }, 2000);
            }
            //停止加载
            @Override
            public void onLoadMore() {
                //加载
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvAddressWdf.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 3 添加地址成功后  返回到该界面
     *
     *    重新查询地址  并添加展示
     * */
    @Override
    protected void onStart() {
        super.onStart();
        List<NewsAddress> newsAddresses = mAddressDao.loadAll();//得到用户添加的地址
        if(newsAddresses!=null && newsAddresses.equals("")){
            mList.clear();
            mList.addAll(newsAddresses);
            mAddNewAddressXLVAdapter.notifyDataSetChanged();
        }

    }

    /**
     * //2 初始化Dao层
     * */
    private void initGreenDao() {
        //添加地址的Dao层
        mAddressDao = MyApplication.getInstances().getDaoSession().getNewsAddressDao();
    }

    /**
     * //1 初始化控件
     * */
    private void initView() {
        xlvAddressWdf = (XRecyclerView) findViewById(R.id.xlv_address_wdf);//地址XLV
        btnNewaddaddressWdf = (Button) findViewById(R.id.btn_newaddaddress_wdf);//添加地址按钮

        //地址存储XLV布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xlvAddressWdf.setLayoutManager(layoutManager);

        //事件监听
        btnNewaddaddressWdf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_newaddaddress_wdf://点击新增地址进行添加
                Intent intentAddAddress = new Intent(DiZhiActivity.this, NewAddressActivity.class);
                startActivity(intentAddAddress);
                break;
        }
    }
}
