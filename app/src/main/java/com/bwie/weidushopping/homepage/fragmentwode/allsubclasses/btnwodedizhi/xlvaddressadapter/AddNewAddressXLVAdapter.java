package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.xlvaddressadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentquanzi.adapter.QuanZiAdapter;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean.NewsAddress;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:2018/12/14
 * author:张自力(DELL)
 * function:  添加新地址  存储的adapter
 */

public class AddNewAddressXLVAdapter extends XRecyclerView.Adapter<AddNewAddressXLVAdapter.ViewHolder> {
    //定义一个接口
    public interface DZOnClickListener{
        void onChanger(int id);
    }
    private DZOnClickListener mlistener;
    public void setDZOnClickListener (DZOnClickListener listener){
        this.mlistener = listener;
    }


    private Context mContext;
    private List<NewsAddress> mList;

    public AddNewAddressXLVAdapter(Context context, List<NewsAddress> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.addnewaddressxlv_adapter, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //绑定数据
        holder.txtNewusernameBtnwdWdf.setText(mList.get(position).getUserName());
        holder.txtNewuserphoneBtnwdWdf.setText(mList.get(position).getUserPhone());
        holder.txtNewuserdiquBtnwdWdf.setText(mList.get(position).getUserDiQu());
        holder.txtNewuserxiangxiaddBtnwdWdf.setText(mList.get(position).getUserXiangXiAddress());
        holder.txtNewuseryzbianmaBtnwdWdf.setText(mList.get(position).getUserBianMa());

        //点击删除通过接口回调进行删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //将id传过去
                mlistener.onChanger(position);
                //移除数据
                mList.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

     class ViewHolder extends XRecyclerView.ViewHolder {
        public TextView txtNewusernameBtnwdWdf;
        public TextView txtNewuserphoneBtnwdWdf;
        public TextView txtNewuserdiquBtnwdWdf;
        public TextView txtNewuserxiangxiaddBtnwdWdf;
        public TextView txtNewuseryzbianmaBtnwdWdf;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNewusernameBtnwdWdf = itemView.findViewById(R.id.txt_newusername_btnwd_wdf);//收货人
            txtNewuserphoneBtnwdWdf = itemView.findViewById(R.id.txt_newuserphone_btnwd_wdf);//收货人手机号
            txtNewuserdiquBtnwdWdf = itemView.findViewById(R.id.txt_newuserdiqu_btnwd_wdf);//地区
            txtNewuserxiangxiaddBtnwdWdf = itemView.findViewById(R.id.txt_newuserxiangxiadd_btnwd_wdf);//详情地址
            txtNewuseryzbianmaBtnwdWdf = itemView.findViewById(R.id.txt_newuseryzbianma_btnwd_wdf);//邮政编码
        }
    }
}
