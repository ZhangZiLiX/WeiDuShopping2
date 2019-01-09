package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodeqianbao;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.weidushopping.R;

/**
 * 我的Fragment  界面的  我的钱包
 */

public class QianBaoActivity extends AppCompatActivity {

    private TextView txtYueQianbaoWdf;
    private SharedPreferences mIsyouhuiquan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_bao);
        //1 初始化控件
        initView();
        //2 初始化sp
        initSP();
    }

    /**
     * //2 初始化sp
     * */
    private void initSP() {
        //优惠券是否领取
        mIsyouhuiquan = getSharedPreferences("isyouhuiquan", MODE_PRIVATE);
        if(mIsyouhuiquan.getBoolean("isyouhuitrue",false)){
            //如果为true  则表明用户领取了  就赋值
            txtYueQianbaoWdf.setText(100.00+"");
        }
    }

    /**
     * 1 初始化控件
     * */
    private void initView() {
        txtYueQianbaoWdf = (TextView) findViewById(R.id.txt_yue_qianbao_wdf);//钱包的余额
    }
}
