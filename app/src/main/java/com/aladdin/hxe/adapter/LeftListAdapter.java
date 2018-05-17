package com.aladdin.hxe.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.ProductList;
import com.aladdin.hxe.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: Ruijinglin
 * @date: 2018/3/1/0001
 * Explain:      .
 */

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.ViewHolder> {

    private List<ProductList.DataBean> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private List<Boolean> isClicks;

    public LeftListAdapter(List<ProductList.DataBean> mDatas) {
        this.mDatas = mDatas;
        isClicks = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            isClicks.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LeftListAdapter.ViewHolder holder, final int position) {

        holder.name.setText(mDatas.get(position).getName());
        holder.itemView.setTag(holder.name);
        if (isClicks.get(position)) {
            holder.name.setTextColor(Color.parseColor("#ffd200"));
            holder.name.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.name.setTextColor(Color.BLACK);
            holder.name.setBackgroundColor(Color.parseColor("#f0f0f0"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
                for (int i = 0; i < isClicks.size(); i++) {
                    isClicks.set(i, false);
                }
                isClicks.set(position, true);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.left_name);
        }
    }

}
