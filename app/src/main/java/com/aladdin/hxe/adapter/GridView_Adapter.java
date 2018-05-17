package com.aladdin.hxe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.aladdin.hxe.R;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/1/26 0026 16:02..
 */

public class GridView_Adapter extends BaseAdapter {
    private final Context context;
    private final ArrayList textList;
    private final ArrayList imageList;


    public GridView_Adapter(Context context, ArrayList<String> textList, ArrayList<Integer> imageList) {
        this.context = context;
        this.textList = textList;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView
                    .findViewById(R.id.tv_gridViewItem);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.img_gridViewItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText( textList.get(position).toString());
        viewHolder.mImageView.setImageResource((Integer) imageList.get(position));
        return convertView;
    }

    private final class ViewHolder {
        TextView mTextView;
        ImageView mImageView;
    }
}
