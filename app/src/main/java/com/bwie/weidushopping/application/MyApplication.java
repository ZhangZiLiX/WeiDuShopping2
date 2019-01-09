package com.bwie.weidushopping.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Toast;

import com.bwie.weidushopping.db.DaoMaster;
import com.bwie.weidushopping.db.DaoSession;
import com.bwie.weidushopping.ourcommon.utils.NetWorkUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;

/**
 * date:2018/12/5
 * author:张自力(DELL)
 * function:  全局配置
 */

public class MyApplication extends Application {

    //1 静态单例
    public static MyApplication instances;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;//1.1 静态单例  防止重复创建
        //1 Fresco全局初始化
        initFresco();
        //2 短信验证
        initDuanXin();
        //3 GreenDao全局配置
        initGreenDao();
        //4 判断网络是否正常的全局设置
        initNetWork();
    }

    /**
     * //4 判断网络是否正常的全局设置
     * */
    private void initNetWork() {
        boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(this);
        if(netWorkAvailable){
            //如果为true则表示正常
        }else{
            //如果为false则表示网络状态异常
            Toast.makeText(this,"网络状态异常,请检查网络是否可用!",Toast.LENGTH_SHORT).show();
        }

    }

    //1.2静态单例  防止重复创建
    public static MyApplication getInstances(){
        return instances;
    }

    /**
     * //3 GreenDao全局配置
     * */
    private void initGreenDao() {
        mHelper = new DaoMaster.DevOpenHelper(this, "customer.db");
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoSession = mDaoMaster.newSession();
    }
    //3.2
    public DaoSession getDaoSession(){
        return mDaoSession;
    }
    //3.3
    public SQLiteDatabase getDb(){
        return db;
    }

    //2短信验证全局配置
    private void initDuanXin() {
        MobSDK.init(this);
    }

    /**
     * //1 Fresco全局初始化
     * */
    private void initFresco() {
        Fresco.initialize(this);
    }
}
