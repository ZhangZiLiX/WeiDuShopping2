package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentquanzi.bean.QuanZiBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodequanzi.bean.WoDeQuanZiBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * date:2018/12/11
 * author:张自力(DELL)
 * function:  圈子的 Adapter
 */

public class WoDeQuanZiAdapter extends XRecyclerView.Adapter<WoDeQuanZiAdapter.ViewHolder> {

    private boolean isDianZan ;//一开始为FALSE

    /**
     * 1 长按删除事件  就扣定义
     * */
    //定义一个接口
    public interface QZOnClickListener{
        void onChanger(int id);
    }
    private QZOnClickListener mlistener;
    public void setQZOnClickListener (QZOnClickListener listener){
        this.mlistener = listener;
    }


    //将发布时间 long类型转换
    public static final String QuanZi_FaBu_Time = "yyyy-MM-dd HH:mm:ss";

    private Context mContext;
    private List<WoDeQuanZiBean.ResultBean> mList;

    public WoDeQuanZiAdapter(Context context, List<WoDeQuanZiBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = View.inflate(mContext, R.layout.quanzi_adapter,null);
        View view = LayoutInflater.from(mContext).inflate( R.layout.wodequanzi_adapter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //1 头像
        Uri uri =Uri.parse(mList.get(position).getHeadPic());
        holder.mSimpleTouXiang.setImageURI(uri);

        //2 昵称
        holder.mTxtUserNameQZAdapter.setText(mList.get(position).getNickName());
        //3 时间转换格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(QuanZi_FaBu_Time, Locale.getDefault());
        holder.mTxtTimeQZAdapter.setText(dateFormat.format(mList.get(position).getCreateTime()));
        //4 发布的说说
        holder.mTxtQuanShuoQZAdapter.setText(mList.get(position).getContent());
        holder.mTxtCountQZAdapter.setText(mList.get(position).getGreatNum()+"");

        //点赞图动态设置
        if(!isDianZan){//如果没有点过赞，就加载黑色的
            holder.mImgDianZanQZAdapter.setImageResource(R.mipmap.common_btn_prise_n_hdpi);
        }else{
            holder.mImgDianZanQZAdapter.setImageResource(R.mipmap.common_btn_prise_s_hdpi);
        }

        //5 判断圈子说说配图  有图片  就展示  没有就隐藏控件
        if(mList.get(position).getImage()!=null){
            holder.mImgQuanShuoTuQZAdapter.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mList.get(position).getImage()).into(holder.mImgQuanShuoTuQZAdapter);
        }else{
            holder.mImgQuanShuoTuQZAdapter.setVisibility(View.GONE);
        }

        /**
         * 1 长按删除事件接口回调
         *
         * 传递id删除
         * */
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mList!=null){
                    mlistener.onChanger(mList.get(position).getCommodityId());
                    mList.remove(position);//删除
                    notifyDataSetChanged();//刷新
                }
                return false;
            }
        });

        /**
         * 通过点击点赞  传id
         * */
        holder.mImgDianZanQZAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList!=null){
                    //点赞标识符
                    isDianZan = true;
                    mlistener.onChanger(mList.get(position).getCommodityId());
                    //点赞图片设置默认
                    holder.mImgDianZanQZAdapter.setImageResource(R.mipmap.common_btn_prise_s_hdpi);
                    int count = mList.get(position).getGreatNum() + 1;
                    holder.mTxtCountQZAdapter.setText(count+"");
                    notifyDataSetChanged();//刷新
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView mSimpleTouXiang;
        private final TextView mTxtUserNameQZAdapter;
        private final TextView mTxtTimeQZAdapter;
        private final TextView mTxtQuanShuoQZAdapter;
        private final ImageView mImgQuanShuoTuQZAdapter;
        private final ImageView mImgDianZanQZAdapter;
        private final TextView mTxtCountQZAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            mSimpleTouXiang = itemView.findViewById(R.id.simple_touxiang);//头像
            mTxtUserNameQZAdapter = itemView.findViewById(R.id.txt_username_qzadapter);//昵称
            mTxtTimeQZAdapter = itemView.findViewById(R.id.txt_time_qzadapter);//时间
            mTxtQuanShuoQZAdapter = itemView.findViewById(R.id.txt_quanshuo_qzadapter);//圈子说说
            mImgQuanShuoTuQZAdapter = itemView.findViewById(R.id.img_quanshuotu_qzadapter);//圈子图
            mImgDianZanQZAdapter = itemView.findViewById(R.id.img_dianzan_quanziadapter);//点赞
            mTxtCountQZAdapter = itemView.findViewById(R.id.txt_count_qzadapter);//点赞数
        }
    }

}
