package com.bwie.weidushopping.homepage.fragmentshouye.adapter.banner;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.BannerBean;
import com.bwie.weidushopping.ourcommon.utils.HttpsToHttp;

import java.util.List;


/**
 * date:2018/12/7
 * author:张自力(DELL)
 * function:
 */

public class BannerAdapter extends PagerAdapter {

    private Context mContext;
    private List<BannerBean.ResultBean> mList;
    public BannerAdapter(Context context, List<BannerBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        if(mList==null){
            return 0;
        }
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //覆写两个方法
    //覆写两个方法
    //1 添加 的方法
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //定义一个imageView对象
        ImageView imageView = new ImageView(mContext);
        //使用Glid将数据imageView
        Glide.with(mContext).load(mList.get(position).getImageUrl()).into(imageView);
        //加入
        container.addView(imageView);
        return imageView;
    }
    //2 删除的方法

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //记得要删除super
        /*super.destroyItem(container, position, object);*/
        container.removeView((View) object);
    }

}
