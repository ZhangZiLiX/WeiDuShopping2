package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.XiangQingBean;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.bean.QuanBuDingDanBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:2018/12/17
 * author:张自力(DELL)
 * function:  详情页的adapter
 */

public class QuanBuDingDanAdapter extends XRecyclerView.Adapter<QuanBuDingDanAdapter.ViewHolder> {

    private Context mContext;
    private List<QuanBuDingDanBean.OrderListBean> mOrderListBeans;
    public QuanBuDingDanAdapter(Context context, List<QuanBuDingDanBean.OrderListBean> orderListBeans) {
        mContext = context;
        mOrderListBeans = orderListBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.xiangqing_adapter,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(mOrderListBeans!=null){
            QuanBuDingDanBean.OrderListBean orderListBean = mOrderListBeans.get(0);
            QuanBuDingDanBean.OrderListBean.DetailListBean detailListBean = orderListBean.getDetailList().get(0);
            Glide.with(mContext).load(detailListBean.getCommodityPic()).into(holder.mSimpleXiangQingShyf);
            holder.mTxtTitleXiangQingShyf.setText(detailListBean.getCommodityName());
            holder.mTxtJieShaoXiangQingShyf.setText(orderListBean.getExpressCompName());
            holder.mTxtPriceXiangQingShyf.setText(detailListBean.getCommodityPrice());
        }

    }

    @Override
    public int getItemCount() {
        return mOrderListBeans.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView mSimpleXiangQingShyf;
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
