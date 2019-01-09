package com.bwie.weidushopping.homepage.fragmentshouye.adapter.xiangqing;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.XiangQingBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:2018/12/17
 * author:张自力(DELL)
 * function:  详情页的adapter
 */

public class XiangQingAdapter extends XRecyclerView.Adapter<XiangQingAdapter.ViewHolder> {

    private Context mContext;
    private XiangQingBean mXiangQingBean;
    public XiangQingAdapter(Context context, XiangQingBean xiangQingBean) {
        mContext = context;
        mXiangQingBean = xiangQingBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.xiangqing_adapter,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mXiangQingBean!=null){
            Glide.with(mContext).load("http://172.17.8.100/images/small/commodity/nz/wy/7/2.jpg").into(holder.mSimpleXiangQingShyf);
            holder.mTxtTitleXiangQingShyf.setText(mXiangQingBean.getResult().getCategoryName());
            holder.mTxtJieShaoXiangQingShyf.setText(mXiangQingBean.getResult().getCommodityName());
            holder.mTxtPriceXiangQingShyf.setText("价格:"+mXiangQingBean.getResult().getPrice());
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView mSimpleXiangQingShyf;
        private final TextView mTxtTitleXiangQingShyf;
        private final TextView mTxtJieShaoXiangQingShyf;
        private final TextView mTxtPriceXiangQingShyf;

        public ViewHolder(View itemView) {
            super(itemView);
            mSimpleXiangQingShyf = itemView.findViewById(R.id.simple_xiangqing_shyf);
            mTxtTitleXiangQingShyf = itemView.findViewById(R.id.txt_title_xiangqing_shyf);
            mTxtJieShaoXiangQingShyf = itemView.findViewById(R.id.txt_jieshao_xiangqing_shyf);
            mTxtPriceXiangQingShyf = itemView.findViewById(R.id.txt_price_xiangqing_shyf);
        }
    }

}
