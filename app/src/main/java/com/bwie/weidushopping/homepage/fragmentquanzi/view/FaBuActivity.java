package com.bwie.weidushopping.homepage.fragmentquanzi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.weidushopping.R;
public class FaBuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtFabu;
    private EditText txtCommodityid;
    private EditText txtContent;
    private TextView imgFbqz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu);
        initView();
    }

    private void initView() {
        txtFabu = (TextView) findViewById(R.id.txt_fabu);
        txtCommodityid = (EditText) findViewById(R.id.txt_commodityid);
        txtContent = (EditText) findViewById(R.id.txt_content);
        imgFbqz = (TextView) findViewById(R.id.img_fbqz);

        txtFabu.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_fabu://点击发布
                //得到数据
                String etCommodityid = txtCommodityid.getText().toString().trim();
                String etContent = txtContent.getText().toString();
                //可以得到图片


                //判空
                if(etCommodityid.equals("") || etCommodityid==null ||
                        etContent.equals("") || etContent==null){
                    //提示不能为空
                    Toast.makeText(this,"发布内容为空！",Toast.LENGTH_SHORT).show();
                }
                //使用intent回传
                Intent intent = new Intent();
                //存值
                intent.putExtra("commodityid",etCommodityid);
                intent.putExtra("content",etContent);
                //回传
                FaBuActivity.this.setResult(RESULT_OK,intent);
                //关闭当前Activity
                FaBuActivity.this.finish();


                break;

        }
    }

    /**
     * 銷毀
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
