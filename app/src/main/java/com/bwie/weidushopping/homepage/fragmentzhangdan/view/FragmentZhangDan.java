package com.bwie.weidushopping.homepage.fragmentzhangdan.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaifukuan.FragmentDDFDaiFuKuanF;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaipingjia.FragmentDDFDaiPingJiaF;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaishouhuo.FragmentDDFDaiShouHuoF;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.view.FragmentDDFQuanBuDingDanF;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentyiwancheng.FragmentDDFYiWanChengF;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function: 这是滑动的fragment  账单界面
 */

public class FragmentZhangDan extends Fragment implements View.OnClickListener {

    private ImageView imgQuanbudingdanDdf;
    private ImageView imgDaifukuanDdf;
    private ImageView imgDaishouhuoDdf;
    private ImageView imgDaipingjiaDdf;
    private ImageView imgYiwanchengDdf;
    private ViewPager vpZhangdanFragment;
    private List<Fragment> mFragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhangdan, null);
        //1 初始化控件
        initView(view);
        //2 初始化listFragmetn 和 fragment
        initListFragmentAndFragment(view);
        //3 设置VP
        setVP(view);
        return view;
    }

    /**
     * //3 设置VP
     * */
    private void setVP(View view) {
        //vp账单的Fragment设置
        vpZhangdanFragment.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });



    }

    /**
     * //2 初始化listFragmetn 和 fragment
     * */
    private void initListFragmentAndFragment(View view) {
        mFragmentList = new ArrayList<>();
        FragmentDDFQuanBuDingDanF fragmentDDFQuanBuDingDanF = new FragmentDDFQuanBuDingDanF();
        FragmentDDFDaiFuKuanF fragmentDDFDaiFuKuanF = new FragmentDDFDaiFuKuanF();
        FragmentDDFDaiShouHuoF fragmentDDFDaiShouHuoF = new FragmentDDFDaiShouHuoF();
        FragmentDDFDaiPingJiaF fragmentDDFDaiPingJiaF = new FragmentDDFDaiPingJiaF();
        FragmentDDFYiWanChengF fragmentDDFYiWanChengF = new FragmentDDFYiWanChengF();
        mFragmentList.add(fragmentDDFQuanBuDingDanF);//全部订单
        mFragmentList.add(fragmentDDFDaiFuKuanF);//待付款
        mFragmentList.add(fragmentDDFDaiShouHuoF);//待收货
        mFragmentList.add(fragmentDDFDaiPingJiaF);//待评价
        mFragmentList.add(fragmentDDFYiWanChengF);//已完成

    }

    /**
     * //1 初始化控件
     */
    private void initView(View view) {
        imgQuanbudingdanDdf = (ImageView) view.findViewById(R.id.img_quanbudingdan_ddf);//全部订单
        imgDaifukuanDdf = (ImageView) view.findViewById(R.id.img_daifukuan_ddf);//待付款
        imgDaishouhuoDdf = (ImageView) view.findViewById(R.id.img_daishouhuo_ddf);//待收货
        imgDaipingjiaDdf = (ImageView) view.findViewById(R.id.img_daipingjia_ddf);//待评价
        imgYiwanchengDdf = (ImageView) view.findViewById(R.id.img_yiwancheng_ddf);//已完成
        vpZhangdanFragment = (ViewPager) view.findViewById(R.id.vp_zhangdan_fragment);//切换的vp

        //监听事件
        imgQuanbudingdanDdf.setOnClickListener(this);
        imgDaifukuanDdf.setOnClickListener(this);
        imgDaishouhuoDdf.setOnClickListener(this);
        imgDaipingjiaDdf.setOnClickListener(this);
        imgYiwanchengDdf.setOnClickListener(this);

        //预加载
        vpZhangdanFragment.setOffscreenPageLimit(4);
    }


    //点击事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_quanbudingdan_ddf://全部订单
                vpZhangdanFragment.setCurrentItem(0);
                break;

            case R.id.img_daifukuan_ddf://待付款
                vpZhangdanFragment.setCurrentItem(1);
                break;

            case R.id.img_daishouhuo_ddf://待收货
                vpZhangdanFragment.setCurrentItem(2);
                break;

            case R.id.img_daipingjia_ddf://待评价
                vpZhangdanFragment.setCurrentItem(3);
                break;

            case R.id.img_yiwancheng_ddf://已完成
                vpZhangdanFragment.setCurrentItem(4);
                break;
        }
    }
}
