package com.bwie.weidushopping.homepage.fragmentgouwuche.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentgouwuche.bean.GouWuCheBean;
import com.bwie.weidushopping.ourcommon.utils.AddDecreaseView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:2018/12/15
 * author:张自力(DELL)
 * function:  购物车Fragment的Adapter
 */

public class GouWuCheAdapter extends XRecyclerView.Adapter<GouWuCheAdapter.ViewHolder> {

    /**
     * 2 点击商品的复选框监听
     */
    public interface OnItemClickListener {
        void onCheckedChange(boolean ischecked,double totalPrice);
        void onNumChange(double price);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private Context mContext;
    private List<GouWuCheBean.ResultBean> mList;

    public GouWuCheAdapter(Context context, List<GouWuCheBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_product, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //绑定数据
        final GouWuCheBean.ResultBean resultBean = mList.get(position);
        if (resultBean == null) {
            return;
        }
        Glide.with(mContext).load(resultBean.getPic()).into(holder.imgProduct);
        holder.txtProjectTitle.setText(resultBean.getCommodityName());
        holder.txtProjectPrice.setText(resultBean.getPrice() + "");
        holder.cbProduct.setChecked(resultBean.isIschecked());
        holder.addDecrease.setNum(resultBean.getCount());

        //商品复选框设置
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //点击复选框选中商品
                resultBean.setIschecked(isChecked);

                //计算这款商品总价格
                double totalPrice = resultBean.getPrice() * resultBean.getCount();

                if(!isChecked){ //没有选中就归零
                    totalPrice = -totalPrice;
                }
                //通过接口传递给外面
                mOnItemClickListener.onCheckedChange(isAllSeleted(),totalPrice);
            }
        });

        //1 加减器监听
        holder.addDecrease.setOnOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {//点击加
                resultBean.setCount(num);//将商品加1
                if(resultBean.isIschecked()){
                    mOnItemClickListener.onNumChange(resultBean.getPrice());
                }
            }

            @Override
            public void decrease(int num) {//点击减号
                resultBean.setCount(num);//商品减1 同步计数器
                if(resultBean.isIschecked()){
                    mOnItemClickListener.onNumChange(-resultBean.getPrice());
                }
            }
        });


    }

    //全选框设置
    public void updateChecked(boolean ischecked) {
        for (int i = 0; i < mList.size(); i++) {
            GouWuCheBean.ResultBean resultBean = mList.get(i);
            resultBean.setIschecked(ischecked);
        }
        notifyDataSetChanged();
    }

    //如果商品选框是选中状态  就展示位选中装填
    private boolean isAllSeleted() {
        for (int i = 0; i < mList.size(); i++) {
            if (!mList.get(i).isIschecked()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder {
        private final CheckBox cbProduct;
        private final ImageView imgProduct;
        private final TextView txtProjectTitle;
        private final TextView txtProjectPrice;
        private final AddDecreaseView addDecrease;

        public ViewHolder(View itemView) {
            super(itemView);
            cbProduct = itemView.findViewById(R.id.cb_product);//选择按钮
            imgProduct = itemView.findViewById(R.id.img_product);//产品
            txtProjectTitle = itemView.findViewById(R.id.txt_project_title);//商品介绍
            txtProjectPrice = itemView.findViewById(R.id.txt_project_price);//价格
            addDecrease = itemView.findViewById(R.id.add_decrease);//加减器
        }
    }

}
