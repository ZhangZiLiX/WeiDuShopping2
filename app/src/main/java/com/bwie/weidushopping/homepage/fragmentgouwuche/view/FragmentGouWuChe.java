package com.bwie.weidushopping.homepage.fragmentgouwuche.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myutilsclass.MyUtils;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentgouwuche.adapter.GouWuCheAdapter;
import com.bwie.weidushopping.homepage.fragmentgouwuche.bean.GouWuCheBean;
import com.bwie.weidushopping.homepage.fragmentgouwuche.presenter.GouWuChePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  这是滑动的fragment  购物车界面
 */

public class FragmentGouWuChe extends Fragment implements IView, View.OnClickListener {

    private TextView txtBianji;
    private CheckBox cbTotalSelect;
    private TextView txtTotalPrice;
    private Button btnJiesuan;
    private RecyclerView rvShopper;
    private GouWuChePresenter mGouWuChePresenter;
    private SharedPreferences mIsonelogin;
    private String mIsuserid;
    private String mIsSessionId;
    private XRecyclerView mXlvGWCShopper;
    private List<GouWuCheBean.ResultBean> mList;
    private GouWuCheAdapter mGouWuCheAdapter;
    private Handler handler = new Handler();
    private double mtotalPrice;
    private int mUserid;
    private String mSessionid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gouwuche, null);
        //1 初始化布局
        initView(view);
        //2 初始化sp
        initSP();
        //3 初始化Presenter层
        initPresenter();
        //4 初始化list 和 adapter
        initListAndAdapter(view);

        //5 所有监听事件
        setOnClickListeners(view);
        return view;
    }

    /**
     * //5 所有监听事件
     * */
    private void setOnClickListeners(View view) {
        //1 底部全选监听设置

        //单选框回调
        mGouWuCheAdapter.setOnItemClickListener(new GouWuCheAdapter.OnItemClickListener() {
            @Override
            public void onCheckedChange(boolean ischecked,double totalPrice) {
                mtotalPrice+=totalPrice;
                cbTotalSelect.setChecked(ischecked);
                txtTotalPrice.setText(mtotalPrice+"");
            }

            @Override
            public void onNumChange(double price) {
                mtotalPrice+=price;
                txtTotalPrice.setText(mtotalPrice+"");
            }
        });

        //全选按钮监听
        cbTotalSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGouWuCheAdapter.updateChecked(cbTotalSelect.isChecked());
            }
        });

    }

    //当再次切换到该页面  再次进行读取展示
    @Override
    public void onStart() {
        super.onStart();
        mGouWuChePresenter.getSelectShoppingDataP(mUserid,mSessionid);//调用查询购物车方法
        mGouWuCheAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mGouWuChePresenter.getSelectShoppingDataP(mUserid,mSessionid);//调用查询购物车方法
        mGouWuCheAdapter.notifyDataSetChanged();
    }

    /**
     * //4 初始化list 和 adapter
     * */
    private void initListAndAdapter(View view) {
        mList = new ArrayList<>();
        mGouWuCheAdapter = new GouWuCheAdapter(getActivity(), mList);
        mXlvGWCShopper.setAdapter(mGouWuCheAdapter);
    }

    /**
     * //2 初始化sp
     * */
    private void initSP() {
        //1 从登陆几面拿到登陆后的seeeionid 他是不断变化的
        mIsonelogin = getActivity().getSharedPreferences("isonelogin", MODE_PRIVATE);
        mIsuserid = mIsonelogin.getString("isuserid", "");
        mIsSessionId = mIsonelogin.getString("isSessionId", "");//得到不断变化的sessionid

        //通过工具类  得到存储的userid  和  sessionid
        mUserid = (int) MyUtils.getData(getActivity(), "userid", 0);
        mSessionid = (String) MyUtils.getData(getActivity(), "sessionid", "");
    }

    /**
     * //2 初始化Presenter层
     * */
    private void initPresenter() {
        mGouWuChePresenter = new GouWuChePresenter();
        mGouWuChePresenter.attach(this);
        mGouWuChePresenter.getSelectShoppingDataP(mUserid,mSessionid);//调用查询购物车方法
    }

    /**
     * //1 初始化布局
     * */
    private void initView(View view) {
        txtBianji = (TextView) view.findViewById(R.id.txt_bianji);//编辑
        cbTotalSelect = (CheckBox) view.findViewById(R.id.cb_total_select);//全选
        txtTotalPrice = (TextView) view.findViewById(R.id.txt_total_price);//总价格
        btnJiesuan = (Button) view.findViewById(R.id.btn_jiesuan);//结算
        mXlvGWCShopper = view.findViewById(R.id.xlv_gwf_shopper);//商品展示
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mXlvGWCShopper.setLayoutManager(layoutManager);

        //刷新监听
        mXlvGWCShopper.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mGouWuChePresenter.getSelectShoppingDataP(mUserid,mSessionid);//调用查询购物车方法
                mGouWuCheAdapter.notifyDataSetChanged();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvGWCShopper.refreshComplete();//停止刷新
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                mGouWuChePresenter.getSelectShoppingDataP(mUserid,mSessionid);//调用查询购物车方法
                mGouWuCheAdapter.notifyDataSetChanged();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvGWCShopper.loadMoreComplete();//停止加载
                    }
                },2000);
            }
        });

        //点击事件监听
        btnJiesuan.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jiesuan://点击结算

                break;
        }
    }


    /**
     * 实现购物车IView
     * */
    @Override
    public void getSelectShoppingData(List<GouWuCheBean.ResultBean> list) {
       if(list!=null){
           mList.clear();
           mList.addAll(list);
           mGouWuCheAdapter.notifyDataSetChanged();
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
        if(mGouWuChePresenter!=null){//购物车
            mGouWuChePresenter.datach();
        }
    }
}
