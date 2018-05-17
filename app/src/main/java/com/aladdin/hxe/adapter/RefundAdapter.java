package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.RefundORderBean;
import com.aladdin.hxe.holder.RefundHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class RefundAdapter extends RecyclerView.Adapter<RefundHolder> {

    private final Context context;
    private ArrayList<RefundORderBean.DataBean.RowsBean> rowsBean;
    private OnItemClickListener monItemClickListener;

    public RefundAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<RefundORderBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public RefundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_refund, parent, false);
        return new RefundHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RefundHolder holder, final int position) {
        //判断是否是拼团
        if (rowsBean.get(position).getOrderType().equals("PT")){
            holder.img_pin.setImageResource(R.drawable.pin);
            holder.tv_realfee.setText("￥:" + rowsBean.get(position).getTotalFee());
        }
        else{
            holder.img_pin.setImageResource(R.drawable.yue);
            holder.tv_realfee.setText("￥:" + rowsBean.get(position).getRealFee());
        }
        String takeNum = rowsBean.get(position).getTakeNum();
        if (takeNum==null) {
            holder.tv_orderNum.setText("#");
        } else {
            holder.tv_orderNum.setText("#" + rowsBean.get(position).getTakeNum());
        }
        holder.tv_firstName.setText(rowsBean.get(position).getOrdersProducts().get(0).getGoodsName());
        holder.tv_nickname.setText(rowsBean.get(position).getNickName());
        holder.tv_des.setText(rowsBean.get(position).getDes());
        holder.tv_updatetime.setText(rowsBean.get(position).getCreateTime().substring(0, 16));
        if(rowsBean.get(position).getUseDate()!=null){
            String useDate = rowsBean.get(position).getUseDate().substring(0, 10);
            String todayData = TimeUtils.GetCurrentTime().substring(0, 10);
            if (useDate.equals(todayData)) {
                holder.tv_arrivalTime.setText("今天" + rowsBean.get(position).getUseDate().substring(11, 16)+"到达");
            }else{
                holder.tv_arrivalTime.setText("昨天" + rowsBean.get(position).getUseDate().substring(11, 16)+"到达");
            }
        }else{
            holder.tv_arrivalTime.setText("");
        }
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
