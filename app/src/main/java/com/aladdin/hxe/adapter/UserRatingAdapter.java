package com.aladdin.hxe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.FindallevaluationBean;
import com.aladdin.hxe.holder.UserRatingHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/2/27 0027 14:18..
 */

public class UserRatingAdapter extends RecyclerView.Adapter<UserRatingHolder> {

    private final Context context;
    private ArrayList<FindallevaluationBean.DataBean.EvaluationlistBean.RowsBean> rowsBean;

    public UserRatingAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<FindallevaluationBean.DataBean.EvaluationlistBean.RowsBean> rowsBean) {
        this.rowsBean = rowsBean;
        notifyDataSetChanged();
    }

    @Override
    public UserRatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.userraring_item, null);
        UserRatingHolder userRatingHolder = new UserRatingHolder(view);
        return userRatingHolder;
    }

    @Override
    public void onBindViewHolder(UserRatingHolder holder, int position) {
        holder.tv_comment.setText(rowsBean.get(position).getContent());
        holder.tv_time.setText(rowsBean.get(position).getCreateTime().substring(0, 10));
        holder.tv_phone.setText(rowsBean.get(position).getPhone());
        Glide.with(context).load( rowsBean.get(position).getUserImage()).into(holder.img_user);
        holder.tv_name.setText(rowsBean.get(position).getNickName());
    }

    @Override
    public int getItemCount() {
        return rowsBean == null ? 0 : rowsBean.size();
    }
}
