package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentquanzi.adapter.QuanZiAdapter;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.DianZanBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.QuanZiBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.presenter.Presenter;
import com.bwie.weidushopping.homepage.fragmentquanzi.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuanZiActivity extends AppCompatActivity implements IView {

    private ImageView imgDeleteWdqzWdf;
    private XRecyclerView xlvQzWdf;

    private List<QuanZiBean.ResultBean> mList;
    private QuanZiAdapter mQuanZiAdapter;
    private Presenter mPresenter;
    private Handler handler = new Handler();//加载刷新handler
    private int page=1;//刷新页数
    private boolean isloding;//是否加载

    /**
     * 从我的Fragment  点击到我的圈子
     * 介绍:
     *     只需要调用一下我的圈子的P层 以及用一下他的adapter 即可
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_zi);
        //1 初始化控件
        initView();
        //2 点击事件监听
        setOnClickListeners();
        //3 list  和 Adapter
        initListAndAdapter();
        //4 为XRecyclerView设置上拉加载  下拉刷新
        setXRVPushAndLoding();
        //5 初始化Presenter层
        initPresenter(page);
        //6 点击删除事件  接口回调
        setDeleteItemXlv();
    }

    /**
     * //6 点击删除事件  接口回调
     * */
    private void setDeleteItemXlv() {

        //通过adapter回调自定义接口方法进行删除
        mQuanZiAdapter.setQZOnClickListener(new QuanZiAdapter.QZOnClickListener() {
            @Override
            public void onChanger(int id) {
                Toast.makeText(QuanZiActivity.this,"成功删除条目"+id,Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * //2 list  和 Adapter
     * */
    private void initListAndAdapter() {
        mList = new ArrayList<>();
        mQuanZiAdapter = new QuanZiAdapter(QuanZiActivity.this, mList);
        xlvQzWdf.setAdapter(mQuanZiAdapter);
    }

    /**
     * //3 为XRecyclerView设置上拉加载  下拉刷新
     * */
    private void setXRVPushAndLoding() {
        //设置可以加载刷新
        xlvQzWdf.setPullRefreshEnabled(true);
        xlvQzWdf.setLoadingMoreEnabled(true);
        //刷新监听
        xlvQzWdf.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新  重新到第一页
                page = 1;
                initPresenter(page);
                isloding = false;
                //设置定制刷新时间
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvQzWdf.refreshComplete();//停止刷新
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                //上拉加载  让page加加
                page++;
                initPresenter(page);
                isloding = true;//是上拉加载
                //设置定制刷新时间
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvQzWdf.loadMoreComplete();//停止加载
                    }
                },2000);
            }
        });

    }

    /**
     * //5 初始化Presenter层
     * */
    private void initPresenter(int page) {
        mPresenter = new Presenter();
        mPresenter.attach(this);
        //mPresenter.getQuanZiDataP();
        mPresenter.getQuanZiDataP(page);
    }

    /**
     * //2 点击事件监听
     * */
    private void setOnClickListeners() {
        //点击删除监听
       // imgDeleteWdqzWdf.setOnClickListener(this);
    }

    /**
     * //1 初始化控件
     * */
    private void initView() {
        imgDeleteWdqzWdf = (ImageView) findViewById(R.id.img_delete_wdqz_wdf);//删除按钮
        xlvQzWdf = (XRecyclerView) findViewById(R.id.xlv_qz_wdf);//下面列表

        //添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xlvQzWdf.setLayoutManager(layoutManager);

    }

    /**
     * 实现圈子的接口后  实现方法
     * */
    @Override
    public void QuanZi(List<QuanZiBean.ResultBean> list) {
        if(list!=null){
            if(!isloding){
                //如果不是加载更多  就让他刷新
                mList.clear();
            }
            mList.addAll(list);
            mQuanZiAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 点赞
     * */
    @Override
    public void DianZan(DianZanBean dianZanBean) {
        if(dianZanBean!=null){
            Toast.makeText(this,""+dianZanBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failder(Exception e) {
        Toast.makeText(QuanZiActivity.this,""+e,Toast.LENGTH_SHORT).show();
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.datach();
        }

        //handler销毁
        handler.removeCallbacksAndMessages(null);
    }
}
