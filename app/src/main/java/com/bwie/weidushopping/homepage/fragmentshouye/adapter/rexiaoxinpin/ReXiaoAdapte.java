package com.bwie.weidushopping.homepage.fragmentshouye.adapter.rexiaoxinpin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.ShopBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:2018/12/7
 * author:张自力(DELL)
 * function:  首页Fragment  热销商品的Adapter
 */

public class ReXiaoAdapte extends XRecyclerView.Adapter<ReXiaoAdapte.ViewHolder>{
    //定义一个接口
    public interface RXSPOnClickListener{
       void onChanger(int id);
    }
    private RXSPOnClickListener mlistener;
    public void setRXSPOnClickListener (RXSPOnClickListener listener){
        this.mlistener = listener;
    }

    private Context mContext;
    private List<ShopBean.ResultBean.RxxpBean.CommodityListBeanXX> mList;
    public ReXiaoAdapte(Context context, List<ShopBean.ResultBean.RxxpBean.CommodityListBeanXX> list) {
        mContext = context;
        mList = list;
    }

    //初始化视图
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(mContext,R.layout.rexiaoxinpin_adapter_syf,null);
        // 在父布局内，不会出现太大空白  使用这个布局，必须给布局设置固定宽度
        View view = LayoutInflater.from(mContext).inflate(R.layout.rexiaoxinpin_adapter_syf,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //分类标题
        //商品
        Glide.with(mContext).load(mList.get(position).getMasterPic()).into(holder.mImgRXXPSYF);
        holder.mTxtNameRXXPSYF.setText(mList.get(position).getCommodityName());
        holder.mTxtPriceRXXPSYF.setText("￥"+mList.get(position).getPrice());
        holder.mTxtNumberRXXPSYF.setText("已售"+mList.get(position).getSaleNum()+"件");

        //点击回调
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.get(position)!=null){
                    mlistener.onChanger(mList.get(position).getCommodityId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //初始化布局
    class ViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView mImgRXXPSYF;
        private final TextView mTxtNameRXXPSYF;
        private final TextView mTxtPriceRXXPSYF;
        private final TextView mTxtNumberRXXPSYF;
/*        private final TextView mTxtRXXPTitle;
        private final RecyclerView mRecyclerShoppingSYF;*/
        //private final TextView mTxtRXXPTitle;

        public ViewHolder(View itemView) {
            super(itemView);
/*            //===========
            mTxtRXXPTitle = itemView.findViewById(R.id.txt_rrxp_title);//类型名
            mRecyclerShoppingSYF = itemView.findViewById(R.id.recycler_shopping_syf);
            //===========*/
            mImgRXXPSYF = itemView.findViewById(R.id.img_rxxp_syf);//商品图图
            mTxtNameRXXPSYF = itemView.findViewById(R.id.txtname_rxxp_syf);//名
            mTxtPriceRXXPSYF = itemView.findViewById(R.id.txtprice_rxxp_syf);//价格
            mTxtNumberRXXPSYF = itemView.findViewById(R.id.txtnumber_rxxp_syf); //已售件数
        }
    }

}
