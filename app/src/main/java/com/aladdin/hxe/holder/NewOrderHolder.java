package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/1/25 0025 15:33..
 */

public class NewOrderHolder extends BaseHolder {
    public final LinearLayout order_hide;
    public final TextView arrivaltime;
    public final TextView ordernumber;
    public final TextView order_status;
    public final TextView tv_nickname;
    public final LinearLayout ll_call;
    public final TextView tv_des;
    public final TextView tv_updatetime;
    public final TextView tv_realfee;
    public final ListView lv_orderproducts;
    public final TextView tv_refuse;
    public final TextView tv_accept;
    public final TextView tv_callNum;
    public final TextView tv_delete;
    public final LinearLayout ll_orc;
    public final ImageView img_pin;
    public final TextView tv_firstName;
    public final TextView tv_num;

    public NewOrderHolder(View itemView) {
        super(itemView);
        arrivaltime = (TextView) itemView.findViewById(R.id.arrivaltime);
        order_hide = (LinearLayout) itemView.findViewById(R.id.order_hide);
        ordernumber = (TextView) itemView.findViewById(R.id.ordernumber);
        order_status = (TextView) itemView.findViewById(R.id.order_status);
        tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
        ll_call = (LinearLayout) itemView.findViewById(R.id.ll_call);
        tv_des = (TextView) itemView.findViewById(R.id.tv_des);
        tv_updatetime = (TextView) itemView.findViewById(R.id.tv_updatetime);
        tv_realfee = (TextView) itemView.findViewById(R.id.tv_realfee);
        lv_orderproducts = (ListView) itemView.findViewById(R.id.lv_orderproducts);
        tv_refuse = (TextView) itemView.findViewById(R.id.tv_refuse);
        tv_accept = (TextView) itemView.findViewById(R.id.tv_accept);
        tv_callNum = (TextView) itemView.findViewById(R.id.tv_callNum);
        tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
        ll_orc = (LinearLayout) itemView.findViewById(R.id.ll_orc);
        img_pin = (ImageView) itemView.findViewById(R.id.img_pin);
        tv_firstName = (TextView) itemView.findViewById(R.id.tv_firstName);
        tv_num = (TextView) itemView.findViewById(R.id.tv_num);
    }



}
