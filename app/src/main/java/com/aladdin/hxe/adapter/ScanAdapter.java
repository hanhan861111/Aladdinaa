package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.ScanBean;
import com.aladdin.hxe.holder.ScanHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class ScanAdapter extends RecyclerView.Adapter<ScanHolder> {

    private final Context context;
    private OnItemClickListener monItemClickListener;
    private ArrayList<ScanBean.DataBean.RowsBean> rowsBean;

    public ScanAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<ScanBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public ScanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scan, parent, false);

        return new ScanHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ScanHolder holder, final int position) {
        holder.tv_orderNum.setText(rowsBean.get(position).getOrderNum().substring(14));
        holder.tv_orderPrice.setText(rowsBean.get(position).getRealFee()+"");
        if(rowsBean.get(position).getOrdersProducts().size()>0){
            holder.tv_orderPrice.setText("￥"+rowsBean.get(position).getRealFee());
            if(rowsBean.get(position).getIsPay()==1){
                holder.img_sacn.setImageResource(R.mipmap.wechat);
            }else if(rowsBean.get(position).getIsPay()==2){
                holder.img_sacn.setImageResource(R.mipmap.alipay);
            }
        }

        holder.tv_orderTime.setText(rowsBean.get(position).getCreateTime().substring(11,16));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (monItemClickListener != null) {
                    monItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowsBean == null ? 0 : rowsBean.size();
    }

    //条目点击回调
    public void setOnItemClickListener(OnItemClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }
}
