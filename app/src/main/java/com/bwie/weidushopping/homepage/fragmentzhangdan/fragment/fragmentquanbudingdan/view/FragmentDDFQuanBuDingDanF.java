package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentgouwuche.adapter.GouWuCheAdapter;
import com.bwie.weidushopping.homepage.fragmentgouwuche.bean.GouWuCheBean;
import com.bwie.weidushopping.homepage.fragmentgouwuche.presenter.GouWuChePresenter;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.adapter.QuanBuDingDanAdapter;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.bean.QuanBuDingDanBean;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.presenter.Presenter;
import com.bwie.weidushopping.homepage.fragmentzhangdan.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * date:2018/12/16
 * author:张自力(DELL)
 * function:  我的订单界面Fragment  的全部订单Fragment
 */

public class FragmentDDFQuanBuDingDanF extends Fragment implements IView {

    private XRecyclerView xlvQuanbudingdanWdddf;
    private Presenter mPresenter;
    private List<QuanBuDingDanBean.OrderListBean> mListBeans;
    private QuanBuDingDanAdapter mQuanBuDingDanAdapter;
    private SharedPreferences mIsonelogin;
    private String mIsuserid;
    private String mIsSessionId;
    private Handler handler = new Handler();//加载刷新handler
    private int page=0;//刷新页数
    private boolean isloding;//是否加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_zdf_qbddf, null);
        //1 初始化控件
        initView(view);
        //SP设置
        initSP(view);
        //2 创建list 和  adapter
        initListAndAdapter(view);
        // 为XRecyclerView设置上拉加载  下拉刷新
        setXRVPushAndLoding(view);
        //3 初始化Presenter层
        initPresenter(view,page);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getQuanBuDingDanDataP(0,page,5,mIsuserid,mIsSessionId);
    }

    /**
     * // 为XRecyclerView设置上拉加载  下拉刷新
     * */
    private void setXRVPushAndLoding(final View view) {
        //设置可以加载刷新
        xlvQuanbudingdanWdddf.setPullRefreshEnabled(true);
        xlvQuanbudingdanWdddf.setLoadingMoreEnabled(true);
        //刷新监听
        xlvQuanbudingdanWdddf.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新  重新到第一页
                page = 1;
                initPresenter(view,page);
                isloding = false;
                //设置定制刷新时间
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvQuanbudingdanWdddf.refreshComplete();//停止刷新
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                //上拉加载  让page加加
                page++;
                initPresenter(view,page);
                isloding = true;//是上拉加载
                //设置定制刷新时间
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvQuanbudingdanWdddf.loadMoreComplete();//停止加载
                    }
                },2000);
            }
        });
    }

    /**
     * //SP设置
     * */
    private void initSP(View view) {
        //1 从登陆几面拿到登陆后的seeeionid 他是不断变化的
        mIsonelogin = getActivity().getSharedPreferences("isonelogin", MODE_PRIVATE);
        mIsuserid = mIsonelogin.getString("isuserid", "");
        mIsSessionId = mIsonelogin.getString("isSessionId", "");//得到不断变化的sessionid
    }

    /**
     * //3 初始化Presenter层
     * */
    private void initPresenter(View view,int page) {
        mPresenter = new Presenter();
        mPresenter.attach(this);
        //调用全部订单方法 status 0=查看全部  1=查看待付款  2=查看待收货  3=查看待评价  9=查看已完成
        mPresenter.getQuanBuDingDanDataP(0,page,5,mIsuserid,mIsSessionId);
    }

    /**
     * //2 创建list 和  adapter
     * */
    private void initListAndAdapter(View view) {
        mListBeans = new ArrayList<>();
        mQuanBuDingDanAdapter = new QuanBuDingDanAdapter(getActivity(), mListBeans);
        xlvQuanbudingdanWdddf.setAdapter(mQuanBuDingDanAdapter);
    }

    /**
     * //1 初始化控件
     * */
    private void initView(View view) {
        //展示订单的xlv
        xlvQuanbudingdanWdddf = (XRecyclerView) view.findViewById(R.id.xlv_quanbudingdan_wdddf);
        //布局管理器设置
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        xlvQuanbudingdanWdddf.setLayoutManager(layoutManager);
        isloding = false;//一开始不是加载更多
    }

    /**
     * 实现接口后  需要实现的方法
     * */
    @Override
    public void getQuanBuDingDanDataWDZDF(List<QuanBuDingDanBean.OrderListBean> listBeans) {
        //查询全部订单的方法
        if(listBeans!=null){
            if(!isloding){
                //如果不是加载更多  就让他刷新
                mListBeans.clear();
            }
            mListBeans.addAll(listBeans);
            mQuanBuDingDanAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void failder(Exception e) {
        Toast.makeText(getActivity(),""+e,Toast.LENGTH_SHORT).show();
    }

    //防止内存泄漏
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
