package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodezuji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.weidushopping.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * date:2018/12/12
 * author:张自力(DELL)
 * function:  //我的  的Fragment  的  点击我的足迹的 adapter
 */

public class ZuJiAdapterr extends XRecyclerView.Adapter<ZuJiAdapterr.ViewHolder> {

    private Context mContext;
     //注意  list  和封装类 还没有写
    //private


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.zuji_wdf_adapter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends XRecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            ImageView imgZuJiAdapterWDF = itemView.findViewById(R.id.img_zuji_adapter_wdf);

        }
    }

}
