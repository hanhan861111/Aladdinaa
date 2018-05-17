package com.aladdin.hxe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.AllOrderBean;
import com.aladdin.hxe.holder.AllOrderHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderHolder> {

    private final Context context;
    private OnItemClickListener monItemClickListener;
    private ArrayList<AllOrderBean.DataBean.RowsBean> rowsBean;

    public AllOrderAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<AllOrderBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public AllOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allorder, parent, false);

        return new AllOrderHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final AllOrderHolder holder, final int position) {
        holder.tv_orderNum.setText("订单号:"+rowsBean.get(position).getOrderNum());
        if(rowsBean.get(position).getOrdersProducts().size()>0){
            holder.tv_orderPrice.setText(rowsBean.get(position).getRealFee() + "");
            if(rowsBean.get(position).getIsPay()==1){
                holder.tv_payStatus.setText("未支付");
                holder.tv_payStatus.setTextColor(Color.RED);
            }else if(rowsBean.get(position).getIsPay()==2){
                holder.tv_payStatus.setText("已支付");
                holder.tv_payStatus.setTextColor(Color.GREEN);
            }

        }

        holder.tv_orderTime.setText(rowsBean.get(position).getCreateTime());
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
