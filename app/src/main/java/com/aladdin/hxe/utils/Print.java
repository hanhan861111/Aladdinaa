package com.aladdin.hxe.utils;

import android.content.Context;
import android.content.Intent;

import com.ysh.rn.printet.BtService;
import com.ysh.rn.printet.print.PrintUtil;

/**
 * Created by ${韩永光} on on 2018/4/21 0021 15:37..
 */

public class Print {
    private Context mContext;
    private Context applicationContext;

    public Print(Context mContext) {
        this.mContext = mContext;
    }

    public void getPrint() {
        Intent intent2 = new Intent(mContext.getApplicationContext(), BtService.class);
        intent2.setAction(PrintUtil.ACTION_PRINT_TEST_TWO);
        mContext.startService(intent2);

    }


}
