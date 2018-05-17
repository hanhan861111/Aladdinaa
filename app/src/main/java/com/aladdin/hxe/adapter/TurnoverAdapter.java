package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.OrderByDataBean;
import com.aladdin.hxe.holder.TurnoverHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class TurnoverAdapter extends RecyclerView.Adapter<TurnoverHolder> {

    private final Context context;
    private OnItemClickListener monItemClickListener;
    private ArrayList<OrderByDataBean.DataBean.RowsBean> rowsBean;

    public TurnoverAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<OrderByDataBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public TurnoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_turnover, null);
        TurnoverHolder turnoverHolder = new TurnoverHolder(view);
        return turnoverHolder;
    }

    @Override
    public void onBindViewHolder(final TurnoverHolder holder, final int position) {
        holder.tv_orderNum.setText("订单号:"+rowsBean.get(position).getOrderNum());
        holder.tv_orderPrice.setText(rowsBean.get(position).getOrdersProducts().get(0).getGoodsPrice() + "");
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
