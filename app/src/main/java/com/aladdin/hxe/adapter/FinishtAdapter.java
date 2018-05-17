package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.FinishOrderFragmentBean;
import com.aladdin.hxe.holder.FinishFragmentHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class FinishtAdapter extends RecyclerView.Adapter<FinishFragmentHolder> {

    private final Context context;
    private OnItemClickListener monItemClickListener;
    private ArrayList<FinishOrderFragmentBean.DataBean.RowsBean> rowsBean;

    public FinishtAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<FinishOrderFragmentBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public FinishFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_finishorder, parent, false);

        return new FinishFragmentHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final FinishFragmentHolder holder, final int position) {
        holder.tv_orderNum.setText("订单号:"+rowsBean.get(position).getOrderNum());
        holder.tv_orderPrice.setText(rowsBean.get(position).getRealFee()+ "");
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
