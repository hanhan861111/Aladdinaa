package com.aladdin.hxe.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/2/27 0027 14:17..
 */

public class UserRatingHolder extends BaseHolder {

    public final ImageView img_user;
    public final TextView tv_name;
    public final TextView tv_time;
    public final TextView tv_phone;
    public final TextView tv_comment;

    public UserRatingHolder(View itemView) {
        super(itemView);
        img_user = (ImageView) itemView.findViewById(R.id.img_user);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        tv_phone = (TextView) itemView.findViewById(R.id.tv_phone);
        tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);
    }
}
