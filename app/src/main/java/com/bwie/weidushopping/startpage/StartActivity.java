package com.bwie.weidushopping.startpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.bwie.weidushopping.R;
import com.bwie.weidushopping.loginandzhucepage.loginpage.view.LoginActivity;
import com.bwie.weidushopping.baseactivity.BaseActionBar;

public class StartActivity extends BaseActionBar implements View.OnClickListener {

    /**
     * 设置倒计时  跳转
     * */
    private int FLAG = 123;//handler倒计时
    //定义一个变量用来表示倒计时
    private int time = 20;
    //用来计时改变总布局背景颜色
    private int x=0;
    //使用一个Handler延迟发
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==FLAG){
                //如果时间大于零  就让时间减1
                if(time>0){
                    time--;
                    x++;
                    //为btn重新赋值
                    btnTiaoguo.setText("跳过("+time+"s)");
                    //重新发送
                    handler.sendEmptyMessageDelayed(FLAG,1000);
                }else if(time==0){
                    //时间为0时跳转到新的页面
                    mSpStart.edit()
                            .putBoolean("isStartTrue",true)
                            .commit();
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(x==3){
                    mRlStartPage.setBackgroundResource(R.drawable.tzy01);
                }else if(x==6){
                    mRlStartPage.setBackgroundResource(R.drawable.tzy05);
                }else if(x==9){
                    mRlStartPage.setBackgroundResource(R.drawable.tzy07);
                }else if(x==12){
                    mRlStartPage.setBackgroundResource(R.drawable.tzy04);
                }else if(x==15){
                    mRlStartPage.setBackgroundResource(R.drawable.tzy06);
                }else if(x==18){
                    mRlStartPage.setBackgroundResource(R.drawable.tzy03);
                }
            }
        }
    };


    private Button btnTiaoguo;
    private RelativeLayout mRlStartPage;
    private SharedPreferences mSpStart;
    private SharedPreferences mIsStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //1 初始化控件
        initView();
        //2 判断是否是第一次使用该软件
        isUserOne();
        //3 定义一个sp
        initSP();


    }

    /**
     * //3 定义一个sp
     *     存储是否是第一次使用该软件
     * */
    private void initSP() {
        //用来存储  是否是第一次使用本软件  如果是就加载启动页  如果不是  就直接跳转登录界面
        mSpStart = getSharedPreferences("isStart", MODE_PRIVATE);
    }

    /**
     * //2 判断是否是第一次使用该软件
     * */
    private void isUserOne() {
        //接收是否第一次使用对象初始化
        mIsStart = getSharedPreferences("isStart", MODE_PRIVATE);
        boolean isStartUser = mIsStart.getBoolean("isStartTrue", false);
        //判断
        if(isStartUser){//不是第一次登录  直接进行跳转
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * //1 初始化控件
     * */
    private void initView() {
        //闪屏页总布局id
        mRlStartPage = findViewById(R.id.rl_startpage);
        btnTiaoguo = (Button) findViewById(R.id.btn_tiaoguo);

        btnTiaoguo.setOnClickListener(this);

        handler.sendEmptyMessageDelayed(FLAG,2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiaoguo:
                //点击跳过
                mSpStart.edit()
                        .putBoolean("isStartTrue",true)
                        .commit();
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    //销毁Handler
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
