package com.aladdin.hxe.holder;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aladdin.hxe.R;

/**
 * Created by ${韩永光} on on 2018/4/25 0025 09:49..
 */

public class NewOrderMainHolder extends BaseViewFinder {
   public RecyclerView recyclerView;

    public NewOrderMainHolder(Activity activity) {
        super(activity);
        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }
}
