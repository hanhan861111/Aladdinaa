package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/1/25 0025 15:33..
 */

public class CallNumHolder extends BaseHolder {
    public final TextView tv_orderNum;
    public final TextView tv_arrivalTime;
    public final TextView tv_callNum;
    public final ImageView img_pin;
    public final TextView tv_firstName;
    public CallNumHolder(View itemView) {
        super(itemView);
        tv_orderNum = (TextView) itemView.findViewById(R.id.tv_orderNum);
        tv_arrivalTime = (TextView) itemView.findViewById(R.id.tv_arrivalTime);
        tv_callNum = (TextView) itemView.findViewById(R.id.tv_callNum);
        img_pin = (ImageView) itemView.findViewById(R.id.img_pin);
        tv_firstName = (TextView) itemView.findViewById(R.id.tv_firstName);
    }
}
