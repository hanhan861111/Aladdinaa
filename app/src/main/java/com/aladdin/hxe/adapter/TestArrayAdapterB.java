package com.aladdin.hxe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/3/7 0007 16:46..
 */

public class TestArrayAdapterB extends ArrayAdapter<String> {
    private final ArrayList<String> listName;
    private Context mContext;


    public TestArrayAdapterB(Context context, ArrayList<String> listName) {
        super(context, android.R.layout.simple_spinner_item, listName);
        mContext = context;
        this.listName = listName;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //修改Spinner展开后的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(listName.get(position));
        tv.setTextSize(12f);
        tv.setTextColor(Color.BLACK);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 修改Spinner选择后结果的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(listName.get(position));
        tv.setTextSize(14f);
        tv.setTextColor(Color.BLACK);
        return convertView;
    }
}
