package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/3/23 0023 15:11..
 */

public class ScanHolder extends BaseHolder{
    public final TextView tv_orderNum;
    public final TextView tv_orderPrice;
    public final TextView tv_orderTime;
    public final ImageView img_sacn;

    public ScanHolder(View itemView) {
        super(itemView);
        img_sacn = (ImageView) itemView.findViewById(R.id.img_sacn);
        tv_orderNum = (TextView) itemView.findViewById(R.id.tv_orderNum);
        tv_orderPrice = (TextView) itemView.findViewById(R.id.tv_orderPrice);
        tv_orderTime = (TextView) itemView.findViewById(R.id.tv_orderTime);

    }
}
