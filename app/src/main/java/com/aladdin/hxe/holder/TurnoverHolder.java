package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/3/14 0014 21:43..
 */

public class TurnoverHolder extends BaseHolder {

    public final TextView tv_orderNum;
    public final TextView tv_orderPrice;
    public final TextView tv_orderTime;

    public TurnoverHolder(View itemView) {
        super(itemView);
        tv_orderNum = (TextView) itemView.findViewById(R.id.tv_orderNum);
        tv_orderPrice = (TextView) itemView.findViewById(R.id.tv_orderPrice);
        tv_orderTime = (TextView) itemView.findViewById(R.id.tv_orderTime);

    }
}
