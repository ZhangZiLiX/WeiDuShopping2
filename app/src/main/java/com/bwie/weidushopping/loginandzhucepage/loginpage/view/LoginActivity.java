package com.bwie.weidushopping.loginandzhucepage.loginpage.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myutilsclass.MyUtils;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.view.HomeActivity;
import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;
import com.bwie.weidushopping.loginandzhucepage.loginpage.presenter.LoginPresenter;
import com.bwie.weidushopping.loginandzhucepage.zhucepage.view.ZhuCeActivity;
import com.bwie.weidushopping.baseactivity.BaseActionBar;
import com.bwie.weidushopping.ourcommon.utils.NetWorkUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActionBar implements View.OnClickListener,IView {

    private EditText etUsername;
    private EditText etUserpassword;
    private ImageView imgStartLoginPasswordeye;
    private CheckBox checkRemenber;
    private CheckBox checkJoinlogin;
    private Button btnLogin;
    private TextView txtLoginKuaisuzhuce;
    private LoginPresenter mLoginPresenter;
    private SharedPreferences mRemenbersp;
    private SharedPreferences mJoinloginsp;
    private boolean mCheckRemenberChecked;
    private SharedPreferences.Editor mEditremenber;
    private boolean iseye = true;//明文密文
    private SharedPreferences mIsUserMessageSP;
    private SharedPreferences mIsStart;
    private SharedPreferences mIsonelogin;
    private SharedPreferences mIsonelogintwo;
    private SharedPreferences mIstrue;
    private String mIsremenberphone;
    private String mIsremenberpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //1 初始化控件
        initView();
        //2 初始化登录的Presenter
        initPresenter();
        //3 初始化sp
        initSP();
        //4 记住密码设置
        isRemenber();
    }

    /**
     * //4 记住密码设置
     *
     *  记住密码  和  选框设置
     * */
    private void isRemenber() {
        boolean isremenbertrue = mRemenbersp.getBoolean("isremenbertrue", false);//得到设置的密码选框状态
        checkRemenber.setChecked(isremenbertrue);
        mIsremenberphone = mRemenbersp.getString("isremenberphone", "");
        mIsremenberpwd = mRemenbersp.getString("isremenberpwd", "");
        //判断
        if(!isremenbertrue){
            //如果没有选中  就清楚sp中的内容
            mRemenbersp.edit().clear().commit();
        }else{
            //否则  就取出sp中的数据  进行输入框赋值
            etUsername.setText(mIsremenberphone);
            etUserpassword.setText(mIsremenberpwd);
        }

        //自动登录
        boolean isjoinlogintrue = mJoinloginsp.getBoolean("isjoinlogintrue", false);
        checkJoinlogin.setChecked(isjoinlogintrue);
        if(isjoinlogintrue){
            //如果自动登录了  直接调用P层进行登录
            mLoginPresenter.getLoginDataP(mIsremenberphone,mIsremenberpwd);
        }

    }

    /**
     * //3 初始化sp
     *
     *     记住密码  自动登录
     *
     * */
    private void initSP() {
        mRemenbersp = getSharedPreferences("remenbersp", MODE_PRIVATE);//3.1 记住密码sp
        mJoinloginsp = getSharedPreferences("joinlogin", MODE_PRIVATE);//3.2  自动登录

        //我的资料等界面  需要的资料存储  只是一个存储作用  密码或手机号等
        mIsUserMessageSP = getSharedPreferences("isUserMessageSP", MODE_PRIVATE);

        //记录是否是第一次使用  并登录的
        mIsonelogin = getSharedPreferences("isonelogin", MODE_PRIVATE);
        mIsonelogintwo = getSharedPreferences("isonelogintwo", MODE_PRIVATE);

    }

    /**
     * //2 初始化登录的Presenter
     * */
    private void initPresenter() {
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attach(this);
    }

    /**
     * //1 初始化控件
     */
    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etUserpassword = (EditText) findViewById(R.id.et_userpassword);
        //输入密码设置
        imgStartLoginPasswordeye = (ImageView) findViewById(R.id.img_start_login_passwordeye);//设置密码框明文密文
        etUserpassword.setInputType(129);//设置默认密文
        checkRemenber = (CheckBox) findViewById(R.id.check_remenber);//记住密码复选框
        checkJoinlogin = (CheckBox) findViewById(R.id.check_joinlogin);//自动登录复选框
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtLoginKuaisuzhuce = (TextView) findViewById(R.id.txt_login_kuaisuzhuce);

        //1.1 点击事件监听
        btnLogin.setOnClickListener(this);
        txtLoginKuaisuzhuce.setOnClickListener(this);
        imgStartLoginPasswordeye.setOnClickListener(this);//密码框设置明文密文
        checkRemenber.setOnClickListener(this);//记住密码
        checkJoinlogin.setOnClickListener(this);//自动登录
    }

    /**
     * //1.1.1 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://点击登录
                //首先判断网络
                boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(this);
                if(!netWorkAvailable){
                    //如果为false则表示网络状态异常
                    Toast.makeText(this,"网络状态异常,请检查网络是否可用!",Toast.LENGTH_SHORT).show();
                    break;
                }


                //1 首先得到用户输入的数据
                String phone = etUsername.getText().toString().trim();
                String password = etUserpassword.getText().toString().trim();
                //2 正则表达式判断手机号  //匹配正整数
                Pattern p = Pattern
                        .compile("^[1-9]\\d*");
                Matcher phonez = p.matcher(phone);
                boolean matchespho = phonez.matches();
                if(!matchespho){
                    Toast.makeText(LoginActivity.this,"手机号只由数字组成！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //3 正则表达式判断输入密码(字母开头，允许5-16字节，允许字母数字下划线)
                /*Pattern pwd = Pattern
                        .compile("^[a-zA-Z][a-zA-Z0-9_]{4,15}");
                Matcher passwordz = pwd.matcher(password);
                boolean matchespwd = passwordz.matches();
                if(!matchespwd){
                    Toast.makeText(LoginActivity.this,"密码必须是字母开头，允许5-16字节，允许字母数字下划线！",Toast.LENGTH_SHORT).show();
                    break;
                }*/

                //4 自动登录
                boolean checked = checkJoinlogin.isChecked();
                mJoinloginsp.edit()
                        .putBoolean("isjoinlogintrue",checked)
                        .commit();

                //3记住密码设置
                mCheckRemenberChecked = checkRemenber.isChecked();//根据复选框  判断是否选择了记住密码
                mEditremenber = mRemenbersp.edit();//专门得到一个记住密码的edit
                mEditremenber.putBoolean("isremenbertrue",mCheckRemenberChecked);//记住密码设置为此时选择框的状态
                if(mCheckRemenberChecked){//通过判断  选择是否使用sp记住密码
                            mEditremenber
                            .putString("isremenberphone",phone)
                            .putString("isremenberpwd",password);
                }
                mEditremenber.commit();

                //2 判断非空
                if(phone.length()==0){
                    Toast.makeText(LoginActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(phone.length()!=11){
                    Toast.makeText(LoginActivity.this,"对不起！手机号只能为11位,请重新填写！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(password.length()==0){
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //只是存储用户数据
                mIsUserMessageSP.edit().
                        putString("isUserMessagePhone",phone).putString("isUserMessagePassword",password).commit();

                //得到第一次登录成功存储的值  走到这一步第一次没有存储为FALSE
                boolean aBoolean = mIsonelogin.getBoolean("isoneuserlogin", false);
                //通过另一个对象存储
                if(aBoolean){
                    mIsonelogintwo.edit().putBoolean("isuserone",true).commit();//第一次存储为FALSE  第二次为true
                }else{
                    mIsonelogintwo.edit().putBoolean("isuserone",false).commit();//第一次存储为FALSE  第二次为true
                }
                //不为空 就调用P层Post方法
                mLoginPresenter.getLoginDataP(phone,password);
                break;
            case R.id.txt_login_kuaisuzhuce://快速注册
                Intent intentzhuce = new Intent(LoginActivity.this, ZhuCeActivity.class);
                startActivity(intentzhuce);
                break;

            case R.id.check_remenber://记住密码
                //根据记住密码选框是否选中  来改变自动登录选框状态
                 if(!checkRemenber.isChecked()){//如果记住密码没有选中
                    checkJoinlogin.setChecked(false);//也将自动登录选框设置为不选中
                 }
                break;

            case R.id.check_joinlogin://自动登录
                if(checkJoinlogin.isChecked()){//如果自动登录是选中状态
                    checkRemenber.setChecked(true);//就将密码也设置为选中状态
                }
                break;

            case R.id.img_start_login_passwordeye://设置明文密文
                if(iseye){
                    //密文设置 129是固定的 128是在布局中设置的，这里设置128不管用
                    etUserpassword.setInputType(129);
                   iseye = false;
                }else {
                    //设置明文
                    etUserpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iseye = true;
                }
                break;
        }
    }

    /**
     * 3 实现登录的接口后  必须实现的方法
     * */
    @Override
    public void LoginBean(LoginBean loginBean) {
        //请求成功
        if(loginBean!=null){
            String message = loginBean.getMessage();
            if(message.equals("登录成功")){//判断是否登录成功
                //提示
                Toast.makeText(this,""+message,Toast.LENGTH_SHORT).show();
                //使用封装的工具类进行存储userid  和 sessionid
                int userId = loginBean.getResult().getUserId();
                String sessionId = loginBean.getResult().getSessionId();
                String headPic = loginBean.getResult().getHeadPic();//头像路径
                MyUtils.putData(this,"userid",userId);
                MyUtils.putData(this,"sessionid",sessionId);
                MyUtils.putData(this,"headpic",headPic);
                //存储第一次使用并登录成功的数据
                mIsonelogin.edit().putBoolean("isoneuserlogin",true)
                        .putString("isuserid", String.valueOf(loginBean.getResult().getUserId()))
                        .putString("isSessionId",loginBean.getResult().getSessionId())//这个是不断改变的
                        .commit();
                Log.i("utils", "LoginBean: "+loginBean.getResult().getSessionId());
                Intent intentlogin = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intentlogin);
            }else {
                Toast.makeText(this,""+message,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failder(Exception e) {
         //请求失败  进行提示
        Toast.makeText(this,""+e,Toast.LENGTH_SHORT).show();
    }

    /**
     * 防止内存泄漏
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLoginPresenter!= null){
            mLoginPresenter.datach();
        }

    }
}
