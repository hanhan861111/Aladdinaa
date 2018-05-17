package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/1/25 0025 15:33..
 */

public class CancelHolder extends BaseHolder {
    public final TextView tv_updatetime;
    public final TextView tv_realfee;
    public final TextView tv_orderNum;
    public final TextView tv_arrivalTime;
    public final TextView tv_callNum;
    public final TextView tv_reason;
    public final ImageView img_pin;
    public final TextView tv_firstName;

    public CancelHolder(View itemView) {
        super(itemView);
        tv_orderNum = (TextView) itemView.findViewById(R.id.tv_orderNum);
        tv_arrivalTime = (TextView) itemView.findViewById(R.id.tv_arrivalTime);
        tv_callNum = (TextView) itemView.findViewById(R.id.tv_callNum);
        tv_reason = (TextView) itemView.findViewById(R.id.tv_reason);
        img_pin = (ImageView) itemView.findViewById(R.id.img_pin);
        tv_updatetime = (TextView) itemView.findViewById(R.id.tv_updatetime);
        tv_realfee = (TextView) itemView.findViewById(R.id.tv_realfee);
        tv_firstName = (TextView) itemView.findViewById(R.id.tv_firstName);
    }
}
