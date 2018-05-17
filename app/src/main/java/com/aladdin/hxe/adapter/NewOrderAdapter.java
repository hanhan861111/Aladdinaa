package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.NewOrderBean;
import com.aladdin.hxe.holder.NewOrderHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.interfaces.OnItemLongClickListener;
import com.aladdin.hxe.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class NewOrderAdapter extends RecyclerView.Adapter<NewOrderHolder> {

    private final Context context;
    private ArrayList<NewOrderBean.DataBean.RowsBean> rowsBean;
    private OnItemClickListener monItemClickListener;
    private OnItemLongClickListener monItemLongClickListener;

    public NewOrderAdapter(Context context) {
        this.context = context;

    }


    public void setDatas(ArrayList<NewOrderBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }


    @Override
    public NewOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_neworder, parent, false);

        return new NewOrderHolder(inflate);
    }


    @Override
    public void onBindViewHolder(final NewOrderHolder holder, final int position) {
        int orderStatus = rowsBean.get(position).getOrderStatus();
        //判断是否是拼团
        if (rowsBean.get(position).getOrderType().equals("PT")) {
            holder.img_pin.setImageResource(R.drawable.pin);
            holder.tv_realfee.setText("￥:" + rowsBean.get(position).getTotalFee());

        } else {
            holder.img_pin.setImageResource(R.drawable.yue);
            holder.tv_realfee.setText("￥:" + rowsBean.get(position).getRealFee());
        }

        if (orderStatus == 2) {
            holder.order_status.setText(" 已接单");
            holder.order_status.setTextColor(0xff12cb77);
            holder.tv_callNum.setVisibility(View.VISIBLE);
        } else if (orderStatus == 1) {
            holder.order_status.setText("点击接单");
            holder.tv_refuse.setVisibility(View.VISIBLE);
            holder.tv_accept.setVisibility(View.VISIBLE);
            holder.tv_callNum.setVisibility(View.GONE);
            holder.order_status.setHintTextColor(0xff444444);
        } else if (orderStatus == 4) {
            holder.order_status.setText("已叫号");
            holder.tv_refuse.setVisibility(View.GONE);
            holder.tv_accept.setVisibility(View.GONE);
            holder.tv_callNum.setVisibility(View.VISIBLE);
            holder.order_status.setTextColor(0xffffd200);
        }
        String takeNum = rowsBean.get(position).getTakeNum();
        if (takeNum == null) {
            holder.ordernumber.setText("#");
        } else {
            holder.ordernumber.setText("#" + rowsBean.get(position).getTakeNum());
        }
        if (rowsBean.get(position).getUseDate() != null) {
            String useDate = rowsBean.get(position).getUseDate().substring(0, 10);
            String todayData = TimeUtils.GetCurrentTime().substring(0, 10);
            if (useDate.equals(todayData)) {
                holder.arrivaltime.setText("今天" + rowsBean.get(position).getUseDate().substring(11, 16));
            }else{
                holder.arrivaltime.setText("昨天" + rowsBean.get(position).getUseDate().substring(11, 16));
            }

        } else {
            holder.arrivaltime.setText("    ");
        }

        holder.tv_firstName.setText(rowsBean.get(position).getOrdersProducts().get(0).getGoodsName());
        if (rowsBean.get(position).getOrdersProducts().size() > 1) {
            holder.tv_num.setText("···");
        } else {
            holder.tv_num.setText(rowsBean.get(position).getOrdersProducts().get(0).getGoodsNum() + "");
        }

        holder.tv_nickname.setText(rowsBean.get(position).getNickName());
        holder.tv_des.setText(rowsBean.get(position).getDes());
        holder.tv_updatetime.setText(rowsBean.get(position).getCreateTime().substring(0, 16));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (monItemClickListener != null) {
                    monItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (monItemLongClickListener != null) {
                    monItemLongClickListener.onItemClick(holder.itemView, position);
                }
                return true;
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

    public void setOnItemLongClickListener(OnItemLongClickListener monItemLongClickListener) {
        this.monItemLongClickListener = monItemLongClickListener;
    }

}
