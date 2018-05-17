package com.aladdin.hxe.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aladdin.hxe.R;
import com.aladdin.hxe.activity.UpdateCategoryActivity;
import com.aladdin.hxe.bean.ProductList;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/3/28 0028 10:12..
 */

public class CategoryManagerAdapter extends RecyclerView.Adapter<CategoryManagerAdapter.ViewHolder> {

    private final Context context;
    private final List<ProductList.DataBean> dataBeen;
    private TextView tv_name;

    public CategoryManagerAdapter(Context context, List<ProductList.DataBean> mLeftListDatas) {
        this.context = context;
        dataBeen = mLeftListDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categorymanager, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        tv_name = holder.tv_name;
        tv_name.setText(dataBeen.get(position).getName());
        holder.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //点击传值
                Bundle bundle = new Bundle();
                bundle.putInt("myID",dataBeen.get(position).getId());
                bundle.putString("myNAME",dataBeen.get(position).getName());
                Intent intent = new Intent(context, UpdateCategoryActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_edit;
        private final TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_edit = (TextView) itemView.findViewById(R.id.tv_edit);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}
