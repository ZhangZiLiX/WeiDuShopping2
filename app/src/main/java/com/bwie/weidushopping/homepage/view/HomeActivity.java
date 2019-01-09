package com.bwie.weidushopping.homepage.view;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentgouwuche.view.FragmentGouWuChe;
import com.bwie.weidushopping.homepage.fragmentquanzi.view.FragmentQuanZi;
import com.bwie.weidushopping.homepage.fragmentshouye.view.FragmentShouYe;
import com.bwie.weidushopping.homepage.fragmentwode.view.FragmentWoDe;
import com.bwie.weidushopping.homepage.fragmentzhangdan.view.FragmentZhangDan;
import com.bwie.weidushopping.baseactivity.BaseActionBar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActionBar implements View.OnClickListener {

    private ViewPager vpHomeFragment;
    private ImageView imgHometabShouye;
    private ImageView imgHometabQuanzi;
    private ImageView imgHometabGouwuche;
    private ImageView imgHometabZhangdan;
    private ImageView imgHometabWode;
    private List<Fragment> mFragmentList;
    private SharedPreferences mIsonelogintwo;
    private AlertDialog.Builder mBuilder;
    private SharedPreferences mIsyouhuiquan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //1 初始化控件
        initView();
        //2 初始化List  和  Fragment
        initFragment();
        //3 底部按钮点击事件监听
        setTabOnClickListener();
        //4 设置Vp
        setVP();
        //5 设置SP
        setSP();
        //6 第一次使用并登录成功的的AlertDialog弹窗设置
        setOneUserAlertDialog();
    }

    /**
     * //6 第一次使用并登录成功的的AlertDialog弹窗设置
     * */
    private void setOneUserAlertDialog() {
        //得到数据 第一次拿值为FALSE  第二次为true
        boolean isStartUser =  mIsonelogintwo.getBoolean("isuserone", false);
        if(!isStartUser) {
            //如果是第一次使用就弹窗 提示
            //创建弹框
            mBuilder = new AlertDialog.Builder(HomeActivity.this);
            mBuilder.setTitle("温馨提示:");
            mBuilder.setMessage("维度商城欢迎您！本商城将赠送您两张50元优惠券,请您签收!");
            mBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        //用户点击确定  荆优惠券存入钱包账户  否则
                        mIsyouhuiquan.edit().putBoolean("isyouhuitrue",true).commit();
                        Toast.makeText(HomeActivity.this, "好的！已存入您的钱包。祝您购物愉快!", Toast.LENGTH_SHORT)
                                .show();
                        dialog.dismiss();
                    }
                });
            mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //用户点击取消  荆优惠券存入钱包账户  否则为FALSE
                    mIsyouhuiquan.edit().putBoolean("isyouhuitrue",false).commit();
                    Toast.makeText(HomeActivity.this, "好的！祝您购物愉快!", Toast.LENGTH_SHORT)
                            .show();
                    dialog.dismiss();
                }
            });
            mBuilder.show();//一定要show 否则无法显示
        }
    }

    /**
     * //5 设置SP
     * */
    private void setSP() {
        //接收是否第一次使用 并登录成功 从startActivity存储的
        mIsonelogintwo = getSharedPreferences("isonelogintwo", MODE_PRIVATE);

        //存储优惠券
        mIsyouhuiquan = getSharedPreferences("isyouhuiquan", MODE_PRIVATE);
    }

    /**
     * //4 设置Vp
     * */
    private void setVP() {

        //设置vp的adapter
        vpHomeFragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        //设置监听
        vpHomeFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               //随着滑动 变化的方法
                getChangeBgImg(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * //随着滑动 变化的方法
     * */
    private void getChangeBgImg(int position) {

       imgHometabShouye.setImageResource(position==0?R.mipmap.tab_home_bottom_shouyes:R.mipmap.tab_home_bottom_shouye);
       imgHometabQuanzi.setImageResource(position==1?R.mipmap.tab_home_bottom_quanzis:R.mipmap.tab_home_bottom_quanzi);
       imgHometabGouwuche.setImageResource(position==2?R.mipmap.tab_home_bottom_gouwuche:R.mipmap.tab_home_bottom_gouwuche);
       imgHometabZhangdan.setImageResource(position==3?R.mipmap.tab_home_bottom_zhangdans:R.mipmap.tab_home_bottom_zhangdan);
       imgHometabWode.setImageResource(position==4?R.mipmap.tab_home_bottom_wodes:R.mipmap.tab_home_bottom_wode);

    }

    /**
     * //3 底部按钮点击事件监听
     * */
    private void setTabOnClickListener() {
        imgHometabShouye.setOnClickListener(this);
        imgHometabQuanzi.setOnClickListener(this);
        imgHometabGouwuche.setOnClickListener(this);
        imgHometabZhangdan.setOnClickListener(this);
        imgHometabWode.setOnClickListener(this);
    }

    //3.2 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_hometab_shouye://首页
                vpHomeFragment.setCurrentItem(0);
                getChangeBgImg(0);
                break;

            case R.id.img_hometab_quanzi://圈子
                vpHomeFragment.setCurrentItem(1);
                getChangeBgImg(1);
                break;

            case R.id.img_hometab_gouwuche://购物车
                vpHomeFragment.setCurrentItem(2);
                getChangeBgImg(2);
                break;

            case R.id.img_hometab_zhangdan://账单
                vpHomeFragment.setCurrentItem(3);
                getChangeBgImg(3);
                break;

            case R.id.img_hometab_wode://我的
                vpHomeFragment.setCurrentItem(4);
                getChangeBgImg(4);
                break;
        }
    }

    /**
     * //2 初始化List  和  Fragment
     */
    private void initFragment() {
        //存储碎片Fragment的List
        mFragmentList = new ArrayList<>();

        //Fragment初始化
        FragmentShouYe fragmentShouYe = new FragmentShouYe();
        FragmentQuanZi fragmentQuanZi = new FragmentQuanZi();
        FragmentGouWuChe fragmentGouWuChe = new FragmentGouWuChe();
        FragmentZhangDan fragmentZhangDan = new FragmentZhangDan();
        FragmentWoDe fragmentWoDe = new FragmentWoDe();

        //加入List
        mFragmentList.add(fragmentShouYe);
        mFragmentList.add(fragmentQuanZi);
        mFragmentList.add(fragmentGouWuChe);
        mFragmentList.add(fragmentZhangDan);
        mFragmentList.add(fragmentWoDe);

    }

    /**
     *  //1 初始化控件
     * */
    private void initView() {
        vpHomeFragment = (ViewPager) findViewById(R.id.vp_home_fragment);
        imgHometabShouye = (ImageView) findViewById(R.id.img_hometab_shouye);
        imgHometabQuanzi = (ImageView) findViewById(R.id.img_hometab_quanzi);
        imgHometabGouwuche = (ImageView) findViewById(R.id.img_hometab_gouwuche);
        imgHometabZhangdan = (ImageView) findViewById(R.id.img_hometab_zhangdan);
        imgHometabWode = (ImageView) findViewById(R.id.img_hometab_wode);

        //设置默认初始化底部按钮
        imgHometabShouye.setImageResource(R.mipmap.tab_home_bottom_shouyes);
        imgHometabQuanzi.setImageResource(R.mipmap.tab_home_bottom_quanzi);
        imgHometabGouwuche.setImageResource(R.mipmap.tab_home_bottom_gouwuche);
        imgHometabZhangdan.setImageResource(R.mipmap.tab_home_bottom_zhangdan);
        imgHometabWode.setImageResource(R.mipmap.tab_home_bottom_wode);

        //预加载
        vpHomeFragment.setOffscreenPageLimit(4);
    }
}
