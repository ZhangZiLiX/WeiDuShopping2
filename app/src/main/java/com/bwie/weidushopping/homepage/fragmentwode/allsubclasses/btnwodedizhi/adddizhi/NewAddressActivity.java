package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.adddizhi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.application.MyApplication;
import com.bwie.weidushopping.baseactivity.BaseActionBar;
import com.bwie.weidushopping.db.NewsAddressDao;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean.NewsAddress;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.view.DiZhiActivity;
import com.bwie.weidushopping.loginandzhucepage.loginpage.view.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 我的Fragment 的我的地址界面  点击添加新地址的    新增地址界面
 *
 * */
public class NewAddressActivity extends BaseActionBar implements View.OnClickListener {

    private EditText mEtNewUserName;
    private EditText mEtNewUserPhone;
    private EditText mEtNewUserDiQu;
    private EditText mEtNewUserXiangXiAdd;
    private EditText mEtNewUserYZBianMa;
    private Button mBtnBaoCunUser;
    private NewsAddressDao mAddressDao;
    int i = 1001;
    int j = 0;
    private SharedPreferences mIsuserid;
    private NewsAddress mNewsAddress;
    private NewsAddress mNewsAddress1;
    private long mInsert;
    private int mIsAddressDaoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        //1 初始化控件
        initView();
        //2 初始化Dao层
        initGreenDao();
    }

    /**
     * //2 初始化Dao层
     * */
    private void initGreenDao() {
        //添加地址的Dao层
        mAddressDao = MyApplication.getInstances().getDaoSession().getNewsAddressDao();

        //由于不能自增 就使用了一个变量来作为id标识存储
        mIsuserid = getSharedPreferences("isuserid", MODE_PRIVATE);
    }

    /**
     * //1 初始化控件
     * */
    private void initView() {
        mEtNewUserName = findViewById(R.id.et_newusername_btnwd_wdf);//收件人
        mEtNewUserPhone = findViewById(R.id.et_newuserphone_btnwd_wdf);//手机号
        mEtNewUserDiQu = findViewById(R.id.et_newuserdiqu_btnwd_wdf);//所在地区
        mEtNewUserXiangXiAdd = findViewById(R.id.et_newuserxiangxiadd_btnwd_wdf);//详细信息
        mEtNewUserYZBianMa = findViewById(R.id.et_newuseryzbianma_btnwd_wdf);//邮政编码
        mBtnBaoCunUser = findViewById(R.id.btn_baocunuser__btnwd_wdf);//保存按钮

        //点击监听
        mBtnBaoCunUser.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_baocunuser__btnwd_wdf://点击保存按钮
                //首先得到用户输入的数据
                String etNewUserName = mEtNewUserName.getText().toString().trim();//用户名
                String etNewUserPhone = mEtNewUserPhone.getText().toString().trim();//手机号
                String etNewUserDiQu = mEtNewUserDiQu.getText().toString().trim();//地区
                String etNewUserXiangXiAdd = mEtNewUserXiangXiAdd.getText().toString().trim();//详细地址
                String etNewUserYZBianMa = mEtNewUserYZBianMa.getText().toString().trim();//邮政编码
                //2 正则表达式判断手机号  //匹配正整数
                Pattern p = Pattern
                        .compile("^[1-9]\\d*");
                Matcher phonez = p.matcher(etNewUserPhone);
                boolean matchespho = phonez.matches();
                if(!matchespho){
                    Toast.makeText(NewAddressActivity.this,"手机号只能由数字组成！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //拿出存储的数据
                mIsAddressDaoid = mIsuserid.getInt("isAddressDaoid", 0);
                if(mIsAddressDaoid ==0){ //如果是第一次  就使用第一次i
                    // 使用数据库缓存
                    mIsAddressDaoid +=1;
                    mIsuserid.edit().putInt("isAddressDaoid",mIsAddressDaoid).commit();
                    mNewsAddress = new NewsAddress(i,etNewUserName, etNewUserPhone, etNewUserDiQu, etNewUserXiangXiAdd, etNewUserYZBianMa);
                    //加入数据库
                    mInsert = mAddressDao.insert(mNewsAddress);
                }else{
                    j = mIsAddressDaoid+1;
                    //将id存储起来
                    mIsuserid.edit().putInt("isAddressDaoid",j).commit();
                    mNewsAddress1 = new NewsAddress(j,etNewUserName, etNewUserPhone, etNewUserDiQu, etNewUserXiangXiAdd, etNewUserYZBianMa);
                    mInsert = mAddressDao.insert(mNewsAddress1);//加入数据库
                }
                if(mInsert!=0){
                    Toast.makeText(NewAddressActivity.this,"添加地址成功",Toast.LENGTH_LONG).show();
                    //添加数据成功或失败  都返回上一级
                    Intent intentreturn = new Intent(NewAddressActivity.this, DiZhiActivity.class);
                    startActivity(intentreturn);
                }else{
                    Toast.makeText(NewAddressActivity.this,"添加地址失败",Toast.LENGTH_LONG).show();
                    //添加数据成功或失败  都返回上一级
                    Intent intentreturn = new Intent(NewAddressActivity.this, DiZhiActivity.class);
                    startActivity(intentreturn);
                }
                break;
        }
    }
}
