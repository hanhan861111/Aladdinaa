package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.InquireCancelOrderBean;
import com.aladdin.hxe.holder.InquireCancelOrderHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/2/8 0008 15:44..
 */

public class InquireCancelOrderAdapter extends RecyclerView.Adapter<InquireCancelOrderHolder> {
    private boolean isChecked = false;
    private final Context context;
    private OnItemClickListener monItemClickListener;
    private ArrayList<InquireCancelOrderBean.DataBean.RowsBean> rowsBean;
    private List<InquireCancelOrderBean.DataBean.RowsBean.OrdersProductsBean> ordersProducts;
    private InquireAllOrderAdapter.ButtonInterface buttonInterface;

    public InquireCancelOrderAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<InquireCancelOrderBean.DataBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public InquireCancelOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inquire_allorder, parent, false);
        return new InquireCancelOrderHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final InquireCancelOrderHolder holder, final int position) {
        if (rowsBean.size() > 0) {
            //判断是否是拼团
            if (rowsBean.get(position).getOrderType().equals("PT")){
                holder.img_pin.setImageResource(R.drawable.pin);
                holder.tv_orderPrice.setText("￥:" + rowsBean.get(position).getTotalFee());

            }else{
                holder.img_pin.setImageResource(R.drawable.yue);
                holder.tv_orderPrice.setText("￥:" + rowsBean.get(position).getRealFee());
            }
            holder.tv_orderNum.setText("#" + rowsBean.get(position).getTakeNum());
            holder.tv_createTime.setText(rowsBean.get(position).getCreateTime());
            if (rowsBean.get(position).getOrderStatus() == 6) {
                holder.tv_updateTime.setText(rowsBean.get(position).getUpdateTime());
            } else {
                holder.tv_updateTime.setText("该订单尚未结束");
            }
            switch (rowsBean.get(position).getIsPay()) {
                case 3:
                    holder.tv_orderState.setText("退款中");
                    break;
                case 4:
                    holder.tv_orderState.setText("已退款");
                    break;
                case 5:
                    holder.tv_orderState.setText("拒绝退款");
                    break;
                default:
                    break;
            }
            if(rowsBean.get(position).getIsPay()==2&&rowsBean.get(position).getOrderStatus()==3){
                holder.tv_orderState.setText("商家拒单");
            }
            holder.tv_userName.setText(rowsBean.get(position).getNickName());
            if (rowsBean.get(position).getOrdersProducts().size() > 0) {
                ordersProducts = rowsBean.get(position).getOrdersProducts();
                holder.tv_fistfoodName.setText(rowsBean.get(position).getOrdersProducts().get(0).getGoodsName());
            }

        }

        holder.tv_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonInterface!=null) {
//                  接口实例化后的而对象，调用重写后的方法
                    buttonInterface.onclick(holder.itemView,position);
                }
            }
        });
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

    /**
     * 按钮点击事件需要的方法
     */
    public void buttonSetOnclick(InquireAllOrderAdapter.ButtonInterface buttonInterface) {
        this.buttonInterface = buttonInterface;
    }

    /**
     * 按钮点击事件对应的接口
     */
    public interface ButtonInterface {
        public void onclick(View view, int position);
    }

}
