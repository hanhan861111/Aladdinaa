package com.aladdin.hxe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.CityBean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/4/4 0004 15:26..
 */

public class SpinnerAdapterCity extends BaseAdapter {
    private final List<CityBean.DataBean>  data;
    private Context context;
    private int layoutId;

    /**
     * 构造方法
     * @param context 上下文对象
     * @param data
     * @param layoutId 布局Id
     */
    public SpinnerAdapterCity(Context context, List<CityBean.DataBean> data, int layoutId) {
        this.context = context;
        this.data = data;
        this.layoutId = layoutId;
    }

    /**
     * 获取Item总数
     * @return
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * 获取一个Item对象
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * 获取指定item的ID
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 绘制的内容均在此实现
     * @param position position就是位置从0开始
     * @param convertView convertView是Spinner中每一项要显示的view
     * @param parent parent就是父窗体了，也就是Spinner
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView != null ? convertView : View.inflate(context, layoutId, null);
        TextView txt_name = (TextView) item.findViewById(R.id.txt_name);
        txt_name.setText(data.get(position).getCityName()+"");
        txt_name.setTextColor(Color.BLACK);
        return item;
    }
}
