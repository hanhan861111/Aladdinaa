package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/3/23 0023 15:11..
 */

public class InquireAllOrderHolder extends BaseHolder {

    public final TextView tv_orderNum;
    public final TextView tv_orderState;
    public final TextView tv_userName;
    public final LinearLayout ll_call;
    public final TextView tv_fistfoodName;
    public final TextView tv_dots;
    public final TextView tv_open;
    public final ListView lv_orderproducts;
    public final TextView tv_des;
    public final TextView tv_orderNumber;
    public final TextView tv_updateTime;
    public final TextView tv_createTime;
    public final TextView tv_payState;
    public final TextView tv_orderPrice;
    public final LinearLayout ll_inquire_hide;
    public final ImageView img_pin;

    public InquireAllOrderHolder(View itemView) {
        super(itemView);
        tv_orderNum = (TextView) itemView.findViewById(R.id.tv_orderNum);
        tv_orderState = (TextView) itemView.findViewById(R.id.tv_orderState);
        tv_userName = (TextView) itemView.findViewById(R.id.tv_userName);
        ll_call = (LinearLayout) itemView.findViewById(R.id.ll_call);
        tv_fistfoodName = (TextView) itemView.findViewById(R.id.tv_fistfoodName);
        tv_dots = (TextView) itemView.findViewById(R.id.tv_dots);
        tv_open = (TextView) itemView.findViewById(R.id.tv_open);
        lv_orderproducts = (ListView) itemView.findViewById(R.id.lv_orderproducts);
        tv_des = (TextView) itemView.findViewById(R.id.tv_des);
        tv_orderNumber = (TextView) itemView.findViewById(R.id.tv_orderNumber);
        tv_updateTime = (TextView) itemView.findViewById(R.id.tv_updateTime);
        tv_createTime = (TextView) itemView.findViewById(R.id.tv_createTime);
        tv_payState = (TextView) itemView.findViewById(R.id.tv_payState);
        tv_orderPrice = (TextView) itemView.findViewById(R.id.tv_orderPrice);
        ll_inquire_hide = (LinearLayout) itemView.findViewById(R.id.ll_inquire_hide);
        img_pin = (ImageView) itemView.findViewById(R.id.img_pin);
    }
}
