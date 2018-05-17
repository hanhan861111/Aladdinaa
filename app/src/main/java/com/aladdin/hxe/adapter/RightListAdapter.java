package com.aladdin.hxe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.activity.UpdateFoodActivity;
import com.aladdin.hxe.bean.GoodsStatusBean;
import com.aladdin.hxe.bean.ProductList;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;


/**
 * @author: Ruijinglin
 * @date: 2018/3/1/0001
 * Explain:      .
 */

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.ViewHolder> {

    private final List<ProductList.DataBean.ProductBean> mRightListDatas;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private String token = SharedPreferencesUtils.getString(context, "token", "");
    private int status;


    public RightListAdapter(Context context, List<ProductList.DataBean.ProductBean> mRightListDatas) {
        this.context = context;
        this.mRightListDatas = mRightListDatas;

    }


    @Override
    public int getItemCount() {
        return mRightListDatas == null ? 0 : mRightListDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.right_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int goodsStatusId = mRightListDatas.get(position).getId();
        status = mRightListDatas.get(position).getStatus();
        holder.tv_right_name.setText(mRightListDatas.get(position).getName());
        holder.tv_right_youhuiPrice.setText("¥" + mRightListDatas.get(position).getSellPrice());
        holder.tv_right_yuanPrice.setText("¥:" + mRightListDatas.get(position).getActivityPrice());
        holder.tv_right_yuanPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        holder.tv_right_youhuiPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        //cardview设置
        holder.cardView.setRadius(8);//设置图片圆角的半径大小
        holder.cardView.setCardElevation(0);//设置阴影部分大小
        holder.cardView.setContentPadding(0, 0, 0, 0);//设置图片距离阴影大小
        Glide.with(context).load(mRightListDatas.get(position).getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.img_right);
        if (status == 2) {
            holder.tv_right_down.setText("下架");
        } else if (status == 3) {
            holder.tv_right_down.setText("上架");
        }
        holder.img_right.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
        //编辑
        holder.tv_right_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putSerializable("mRightListDatas", (Serializable) mRightListDatas);
                Intent intent = new Intent(context, UpdateFoodActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        //上下架
        holder.tv_right_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == 2) {
                    holder.tv_right_down.setText("下架");
                    RequestManager.postJson().addParams("token", token).
                            addParams("status", String.valueOf(status)).
                            addParams("id", String.valueOf(goodsStatusId)).
                            setUrl(Url.update).builder().onUI().
                            setCallback(new IRequestBeanListener<GoodsStatusBean>() {
                                @Override
                                public void onErr(String s) {
                                }

                                @Override
                                public void onSuccess(GoodsStatusBean baen) {
                                }
                            });
                    status = 3;
                } else if (status == 3) {
                    holder.tv_right_down.setText("上架");
                    RequestManager.postJson().addParams("token", token).
                            addParams("status", String.valueOf(status)).
                            addParams("id", String.valueOf(goodsStatusId)).
                            setUrl(Url.update).builder().onUI().
                            setCallback(new IRequestBeanListener<GoodsStatusBean>() {
                                @Override
                                public void onErr(String s) {

                                }

                                @Override
                                public void onSuccess(GoodsStatusBean baen) {
                                    Log.d("eeeee", baen.getMsg() + "上架");
                                }
                            });
                    status = 2;
                }
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView img_right;
        private final TextView tv_right_edit;
        private final TextView tv_right_down;
        private final TextView tv_right_name;
        private final TextView tv_right_youhuiPrice;
        private final CardView cardView;
        private final TextView tv_right_yuanPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            img_right = (ImageView) itemView.findViewById(R.id.img_right);
            img_right.setScaleType(ImageView.ScaleType.CENTER_CROP);
            tv_right_edit = (TextView) itemView.findViewById(R.id.tv_right_edit);
            tv_right_down = (TextView) itemView.findViewById(R.id.tv_right_down);
            tv_right_name = (TextView) itemView.findViewById(R.id.tv_right_name);
            tv_right_youhuiPrice = (TextView) itemView.findViewById(R.id.tv_right_youhuiPrice);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tv_right_yuanPrice = (TextView) itemView.findViewById(R.id.tv_right_yuanPrice);
        }
    }

    public void setOnItemClickListener(OnItemClickListener monItemClickListener) {
        onItemClickListener = monItemClickListener;
    }


}

