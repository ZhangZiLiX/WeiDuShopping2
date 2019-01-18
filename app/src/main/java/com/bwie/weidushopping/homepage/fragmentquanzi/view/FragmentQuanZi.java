package com.bwie.weidushopping.homepage.fragmentquanzi.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.myutilsclass.MyUtils;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentquanzi.adapter.QuanZiAdapter;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.DianZanBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.FaBuBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.QuanZiBean;
import com.bwie.weidushopping.homepage.fragmentquanzi.presenter.Presenter;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.bean.WoDeQuanZiBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  这是滑动的fragment  圈子界面
 */

public class FragmentQuanZi extends Fragment implements IView, View.OnClickListener {

    private XRecyclerView xlvQzf;
    private List<QuanZiBean.ResultBean> mList;
    private QuanZiAdapter mQuanZiAdapter;
    private Presenter mPresenter;
    private Handler handler = new Handler();//加载刷新handler
    private int page = 1;//刷新页数
    private boolean isloding;//是否加载
    private SharedPreferences mRemenbersp;
    private String mIsremenberphone;
    private String mIsremenberpwd;
    private int mUserid;
    private String mSessionid;
    private ImageView imgFabuQzf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanzi, null);
        //1 初始化控件
        initView(view);
        //2 list  和 Adapter
        initListAndAdapter(view);
        //3 为XRecyclerView设置上拉加载  下拉刷新
        setXRVPushAndLoding(view);
        //4 初始化Presenter层
        initPresenter(view, page);
        //5 设置SP
        initSP(view);
        //6 adapter删除  点赞等功能设置
        setAdapterOnClickListener(view);
        return view;
    }

    /**
     * 跳转过后  返回过来接收值的回调方法
     *
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data!=null && !data.equals("")){
            //得到返回的发布数据
            String commodityid = data.getExtras().getString("commodityid");//发布条数
            String content = data.getExtras().getString("content");//发布内容

            //正确得到数据
            mPresenter.getQuanZiFaBuDataP(commodityid,content,mUserid,mSessionid);
        }else{
            Toast.makeText(getActivity(),"上传数据不能为空",Toast.LENGTH_SHORT).show();
        }




    }

    /**
     * //5 设置SP
     */
    private void initSP(View view) {
        //1 通过登录时记录的userid 和 sessionid
        //从登陆几面拿到登陆后的seeeionid 他是不断变化的
        //通过工具类  得到存储的userid  和  sessionid
        mUserid = (int) MyUtils.getData(getActivity(), "userid", 0);
        mSessionid = (String) MyUtils.getData(getActivity(), "sessionid", "");

        //2 拿到密码和手机号
        mRemenbersp = getActivity().getSharedPreferences("remenbersp", Context.MODE_PRIVATE);//3.1 记住密码sp
        mIsremenberphone = mRemenbersp.getString("isremenberphone", "");
        mIsremenberpwd = mRemenbersp.getString("isremenberpwd", "");
    }

    /**
     * //6 点赞等功能设置
     */
    private void setAdapterOnClickListener(View view) {
        //点赞
        mQuanZiAdapter.setQZOnClickListener(new QuanZiAdapter.QZOnClickListener() {
            @Override
            public void onChanger(int id) {
                //调用P层的接口
                mPresenter.getQuanZiDianZanDataP(id, mIsremenberphone, mIsremenberpwd, mUserid, mSessionid);

            }
        });
        mQuanZiAdapter.notifyDataSetChanged();//刷新

    }

    /**
     * //3 为XRecyclerView设置上拉加载  下拉刷新
     */
    private void setXRVPushAndLoding(final View view) {
        //设置可以加载刷新
        xlvQzf.setPullRefreshEnabled(true);
        xlvQzf.setLoadingMoreEnabled(true);
        //刷新监听
        xlvQzf.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新  重新到第一页
                page = 1;
                initPresenter(view, page);
                isloding = false;
                //设置定制刷新时间
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvQzf.refreshComplete();//停止刷新
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //上拉加载  让page加加
                page++;
                initPresenter(view, page);
                isloding = true;//是上拉加载
                //设置定制刷新时间
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlvQzf.loadMoreComplete();//停止加载
                    }
                }, 2000);
            }
        });

    }

    /**
     * //4 初始化Presenter层
     */
    private void initPresenter(View view, int page) {
        mPresenter = new Presenter();
        mPresenter.attach(this);
        mPresenter.getQuanZiDataP(page);

    }

    /**
     * //2 list  和 Adapter
     */
    private void initListAndAdapter(View view) {
        mList = new ArrayList<>();
        mQuanZiAdapter = new QuanZiAdapter(getActivity(), mList);
        xlvQzf.setAdapter(mQuanZiAdapter);
    }

    /**
     * //1 初始化控件
     */
    private void initView(View view) {
        xlvQzf = (XRecyclerView) view.findViewById(R.id.xlv_qzf);

        //设置布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        xlvQzf.setLayoutManager(layoutManager);
        //设置初始化时的page  和 是否是加载  通过调用Presenter层的方法  传过去两个参数

        isloding = false;//一开始不是加载更多

        imgFabuQzf = (ImageView) view.findViewById(R.id.img_fabu_qzf);
        imgFabuQzf.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_fabu_qzf://点击发布圈子
                //点击之后进行展示跳转
                startActivityForResult(new Intent(getActivity(), FaBuActivity.class),1);
                break;
        }
    }

    /**
     * 实现圈子的IView接口后  实现的方法
     */
    @Override
    public void QuanZi(List<QuanZiBean.ResultBean> list) {
        if (list != null) {
            if (!isloding) {
                //如果不是加载更多  就让他刷新
                mList.clear();
            }
            mList.addAll(list);
            mQuanZiAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void WoDeQuanZi(List<WoDeQuanZiBean.ResultBean> list) {
        //无用  这是我的圈子的方法
    }

    /**
     * 圈子点赞
     */
    @Override
    public void DianZan(DianZanBean dianZanBean) {
        if (dianZanBean != null) {
            Toast.makeText(getActivity(), "" + dianZanBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发布
     * */
    @Override
    public void FaBu(FaBuBean faBuBean) {
        String message = faBuBean.getMessage();
        MyUtils.toastShow(getActivity(),message+"");
    }

    @Override
    public void failder(Exception e) {
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.datach();
        }

        //handler销毁
        handler.removeCallbacksAndMessages(null);
    }

}
