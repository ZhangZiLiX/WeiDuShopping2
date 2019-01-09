package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodezuji.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwie.weidushopping.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * //我的  的Fragment  的  点击我的足迹的 布局
 *
 *http://172.17.8.100/small/commodity/verify/v1/browseList?userId=18sessionId=15910975491page=5&count=5
 * */

public class ZuJiActivity extends AppCompatActivity {

    private XRecyclerView xlvZjWdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_ji);
        //1 初始化控件
        initView();
    }

    /**
     * //1 初始化控件
     * */
    private void initView() {
        xlvZjWdf = (XRecyclerView) findViewById(R.id.xlv_zj_wdf);
        //添加布局管理器
        //4 瀑布流  瀑布流管理器，只有两个参数的构造方法，第一个是列数，第二个是方向
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        xlvZjWdf.setLayoutManager(layoutManager);
    }
}
