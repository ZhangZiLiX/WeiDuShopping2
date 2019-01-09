package com.bwie.weidushopping.homepage.fragmentwode.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.application.MyApplication;
import com.bwie.weidushopping.db.NewsAddressDao;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.view.DiZhiActivity;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao.QianBaoActivity;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.view.QuanZiActivity;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodezuji.view.ZuJiActivity;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.view.MyMessageActivity;
import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;
import com.bwie.weidushopping.loginandzhucepage.loginpage.presenter.LoginPresenter;
import com.bwie.weidushopping.loginandzhucepage.loginpage.view.IView;
import com.bwie.weidushopping.loginandzhucepage.loginpage.view.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.ByteArrayInputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  这是滑动的fragment  我的界面
 */

public class FragmentWoDe extends Fragment implements View.OnClickListener, IView {

    private TextView txtGerenziliaoWdf;
    private TextView txtWodequanziWdf;
    private TextView txtWodezujiWdf;
    private TextView txtWodeqianbaoWdf;
    private TextView txtWodeshouhuodizhiWdf;
    private SimpleDraweeView imgTouxiangWdf;
    private TextView txtUsernameWdf;
    private LoginPresenter mLoginPresenter;
    private SharedPreferences mUpdateTouXiangSP;
    private TextView txtTuichu;
    private SharedPreferences mSpStart;
    private SharedPreferences mRemenbersp;
    private SharedPreferences mJoinloginsp;
    private SharedPreferences mIsonelogin;
    private SharedPreferences mIsonelogintwo;
    private SharedPreferences mIsUserMessageSP;
    private NewsAddressDao mAddressDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, null);
        //1 初始化控件
        initView(view);
        //2 点击事件监听
        setOnClickListeners(view);
        //3 登录的p层的重新调用
        initPresenter();
        //4 从登录界面sp存储中得到密码等数据
        initSp();
        return view;
    }

    /**
     * 修改后 更新头像
     * <p>
     * 当从我的资料界面上传头像成功后
     * 再次放回到该界面  会重新走onStart（）方法
     */
    @Override
    public void onStart() {
        super.onStart();
        //5 从本地的SP中  得到上传时的头像  进行头像更新  在我的资料中上传头像修改资料的
        getBitmapFromSharedPreferences();
    }

    /**
     * //5 从本地的SP中  得到上传时的头像  进行头像更新
     */
    private void getBitmapFromSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("testSP", MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {//使用默认头像
            imgTouxiangWdf.setImageResource(R.mipmap.ic_launcher);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            imgTouxiangWdf.setImageBitmap(bitmap);//更新上传的头像
        }

    }

    /**
     * //3 登录的p层的重新调用
     */
    private void initPresenter() {
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attach(this);

    }

    /**
     * //2 从登录界面sp存储中得到密码等数据
     */
    private void initSp() {
        //闪屏页sp
        //用来存储  是否是第一次使用本软件  如果是就加载启动页  如果不是  就直接跳转登录界面
        mSpStart = getActivity().getSharedPreferences("isStart", MODE_PRIVATE);
        //登录的时候
        mRemenbersp = getActivity().getSharedPreferences("remenbersp", MODE_PRIVATE);//3.1 记住密码sp
        mJoinloginsp = getActivity().getSharedPreferences("joinlogin", MODE_PRIVATE);//3.2  自动登录
        //记录是否是第一次使用  并登录的
        mIsonelogin = getActivity().getSharedPreferences("isonelogin", MODE_PRIVATE);
        mIsonelogintwo = getActivity().getSharedPreferences("isonelogintwo", MODE_PRIVATE);
        //记住登录的信息
        mIsUserMessageSP = getActivity().getSharedPreferences("isUserMessageSP", MODE_PRIVATE);
        String phone = mIsUserMessageSP.getString("isUserMessagePhone", "");
        String password = mIsUserMessageSP.getString("isUserMessagePassword", "");
        //重新调用登录P层的方法
        mLoginPresenter.getLoginDataP(phone, password);
        ////添加地址的Dao层
        mAddressDao = MyApplication.getInstances().getDaoSession().getNewsAddressDao();

        //因为上传头像接口有问题  所以使用的是sp本地文件更换头像
        mUpdateTouXiangSP = getActivity().getSharedPreferences("testSP", MODE_PRIVATE);
        String imageString = mUpdateTouXiangSP.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
            imgTouxiangWdf.setImageResource(R.mipmap.ic_launcher);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            imgTouxiangWdf.setImageBitmap(bitmap);
        }
    }

    /**
     * //2 点击事件监听
     */
    private void setOnClickListeners(View view) {
        txtGerenziliaoWdf.setOnClickListener(this);//个人资料
        txtWodequanziWdf.setOnClickListener(this);//我的圈子
        txtWodezujiWdf.setOnClickListener(this);//我的足迹
        txtWodeqianbaoWdf.setOnClickListener(this);//我的钱包
        txtWodeshouhuodizhiWdf.setOnClickListener(this);//我的收货地址
        imgTouxiangWdf.setOnClickListener(this);//头像
        txtUsernameWdf.setOnClickListener(this);//昵称
        txtTuichu.setOnClickListener(this);//点击退出登录
    }


    /**
     * 1 初始化控件
     */
    private void initView(View view) {
        txtGerenziliaoWdf = (TextView) view.findViewById(R.id.txt_gerenziliao_wdf);//个人资料
        txtWodequanziWdf = (TextView) view.findViewById(R.id.txt_wodequanzi_wdf);//我的圈子
        txtWodezujiWdf = (TextView) view.findViewById(R.id.txt_wodezuji_wdf);//我的足迹
        txtWodeqianbaoWdf = (TextView) view.findViewById(R.id.txt_wodeqianbao_wdf);//我的钱包
        txtWodeshouhuodizhiWdf = (TextView) view.findViewById(R.id.txt_wodeshouhuodizhi_wdf);//我的收货地址
        imgTouxiangWdf = view.findViewById(R.id.img_touxiang_wdf);//头像
        txtUsernameWdf = (TextView) view.findViewById(R.id.txt_username_wdf);//昵称
        txtTuichu = (TextView) view.findViewById(R.id.txt_tuichu);//退出登录
        txtTuichu.setOnClickListener(this);
    }

    /**
     * 3 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_gerenziliao_wdf://个人资料
                //跳转到我的资料界面
                Intent intentwodemessage = new Intent(getActivity(), MyMessageActivity.class);
                startActivity(intentwodemessage);
                break;

            case R.id.txt_wodequanzi_wdf://我的圈子
                Intent intentwodequanze = new Intent(getActivity(), QuanZiActivity.class);
                startActivity(intentwodequanze);
                break;

            case R.id.txt_wodezuji_wdf://我的足迹
                Intent intentwodezuji = new Intent(getActivity(), ZuJiActivity.class);
                startActivity(intentwodezuji);
                break;

            case R.id.txt_wodeqianbao_wdf://我的钱包
                Intent intentwodeqianbao = new Intent(getActivity(), QianBaoActivity.class);
                startActivity(intentwodeqianbao);
                break;

            case R.id.txt_wodeshouhuodizhi_wdf://我的收货地址
                Intent intentwodeaddress = new Intent(getActivity(), DiZhiActivity.class);
                startActivity(intentwodeaddress);
                break;

            case R.id.txt_tuichu://退出登录
                //清空所有的sp存储  以及数据库的所有数据
                mSpStart.edit().clear().commit();//清除闪屏数据
                //登录界面
                mRemenbersp.edit().clear().commit();//清除记住密码
                mJoinloginsp.edit().clear().commit();//清除自动登录
                //是否是第一次登录
                mIsUserMessageSP.edit().clear().commit();//清除手机ID和 seeeionid
                // mAddressDao.deleteAll();//清除所有地址数据
                Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogin);
                break;

            case R.id.img_touxiang_wdf://头像  没有功能  只能在我的资料里面修改头像等
                break;
            case R.id.txt_username_wdf://昵称   没有功能  只能在我的资料里面修改头像等
                break;

        }
    }


    /**
     * 实现登录接口后  实现的方法
     */
    @Override
    public void LoginBean(LoginBean loginBean) {
        if (loginBean != null) {
            if (loginBean.getMessage().equals("登录成功")) {
                //得到头像  使用Fresco进行赋值
                Uri uri = Uri.parse(loginBean.getResult().getHeadPic());
                imgTouxiangWdf.setImageURI(uri);
            }
        }
    }

    @Override
    public void failder(Exception e) {
        //请求失败  进行提示
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
    }

    /**
     * 防止内存泄漏
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLoginPresenter != null) {
            mLoginPresenter.datach();
        }

    }

}
