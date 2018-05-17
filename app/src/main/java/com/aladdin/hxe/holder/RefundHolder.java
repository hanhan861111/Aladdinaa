package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/3/2 0002 15:05..
 */

public class RefundHolder extends BaseHolder {
    public final TextView tv_orderNum;
    public final TextView tv_arrivalTime;
    public final LinearLayout ll_callNum;
    public final TextView tv_reason;
    public final ImageView img_pin;
    public final TextView tv_updatetime;
    public final TextView tv_realfee;
    public final TextView tv_firstName;
    public final TextView tv_nickname;
    public final TextView tv_des;

    public RefundHolder(View itemView) {
        super(itemView);
        tv_updatetime = (TextView) itemView.findViewById(R.id.tv_updatetime);
        tv_realfee = (TextView) itemView.findViewById(R.id.tv_realfee);
        tv_orderNum = (TextView) itemView.findViewById(R.id.tv_orderNum);
        tv_arrivalTime = (TextView) itemView.findViewById(R.id.tv_arrivalTime);
        ll_callNum = (LinearLayout) itemView.findViewById(R.id.ll_call);
        tv_reason = (TextView) itemView.findViewById(R.id.tv_reason);
        img_pin = (ImageView) itemView.findViewById(R.id.img_pin);
        tv_firstName = (TextView) itemView.findViewById(R.id.tv_firstName);
        tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
        tv_des = (TextView) itemView.findViewById(R.id.tv_des);
    }
}
