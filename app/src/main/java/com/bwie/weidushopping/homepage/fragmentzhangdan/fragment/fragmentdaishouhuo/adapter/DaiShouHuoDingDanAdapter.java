package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaishouhuo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentquanbudingdan.bean.QuanBuDingDanBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * date:2018/12/17
 * author:张自力(DELL)
 * function:  详情页的adapter
 */

public class DaiShouHuoDingDanAdapter extends XRecyclerView.Adapter<DaiShouHuoDingDanAdapter.ViewHolder> {
    //3 时间转换格式
    //将发布时间 long类型转换
    public static final String QuanZi_FaBu_Time = "yyyy-MM-dd HH:mm:ss";

    //接口回调
    public interface onBtnDSHClickListener{
        //确认订单
        void onDingDanChange(int id);
        //删除订单
        void onDeleteDingDanChange(int id);
    }
    private onBtnDSHClickListener mOnBtnClickListener;
    public void setOnBtnDSHClickListener(onBtnDSHClickListener listener){
        mOnBtnClickListener = listener;
    }

    private Context mContext;
    private List<QuanBuDingDanBean.OrderListBean> mOrderListBeans;
    public DaiShouHuoDingDanAdapter(Context context, List<QuanBuDingDanBean.OrderListBean> orderListBeans) {
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
            final QuanBuDingDanBean.OrderListBean.DetailListBean detailListBean = orderListBean.getDetailList().get(0);

            String commodityPic = detailListBean.getCommodityPic();
            String[] split = commodityPic.split("\\|");

            Glide.with(mContext).load(split[0]).into(holder.mImagXiangQingShyf);
            holder.mTxtTitleXiangQingShyf.setText(detailListBean.getCommodityName());
            holder.mTxtJieShaoXiangQingShyf.setText(orderListBean.getExpressCompName());
            holder.mTxtPriceXiangQingShyf.setText(detailListBean.getCommodityPrice()+"");
            holder.mTxtNumXiangQingShy.setText(detailListBean.getCommodityCount()+"");
            SimpleDateFormat dateFormat = new SimpleDateFormat(QuanZi_FaBu_Time, Locale.getDefault());
            holder.mTxtTimeShyf.setText(dateFormat.format(detailListBean.getOrderDetailId()));


            //点击取消  和  确认订单的回调
            holder.mTxtCancleShyf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(mContext,"取消订单成功",Toast.LENGTH_SHORT).show();
                    mOnBtnClickListener.onDeleteDingDanChange(detailListBean.getOrderDetailId());
                }
            });
            //确认订单的回调
            holder.mTxtYesShyf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext,"请去待收货页面进行确认订单",Toast.LENGTH_SHORT).show();
                    mOnBtnClickListener.onDingDanChange(detailListBean.getOrderDetailId());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mOrderListBeans.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView mImagXiangQingShyf;
        private final TextView mTxtTitleXiangQingShyf;
        private final TextView mTxtJieShaoXiangQingShyf;
        private final TextView mTxtPriceXiangQingShyf;
        private final TextView mTxtTimeShyf;
        private final TextView mTxtNumXiangQingShy;
        private final TextView mTxtCancleShyf;
        private final TextView mTxtYesShyf;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtTimeShyf = itemView.findViewById(R.id.txt_time_shyf);//时间

            mImagXiangQingShyf = itemView.findViewById(R.id.img_xiangqing_shyf);
            mTxtTitleXiangQingShyf = itemView.findViewById(R.id.txt_title_xiangqing_shyf);
            mTxtJieShaoXiangQingShyf = itemView.findViewById(R.id.txt_jieshao_xiangqing_shyf);
            mTxtPriceXiangQingShyf = itemView.findViewById(R.id.txt_price_xiangqing_shyf);

            mTxtNumXiangQingShy = itemView.findViewById(R.id.txt_num_xiangqing_shyf);//数量
            mTxtCancleShyf = itemView.findViewById(R.id.txt_cancle_shyf);//取消订单
            mTxtYesShyf = itemView.findViewById(R.id.txt_yes_shyf);//确认订单
        }
    }

}
