package com.aladdin.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * User: Ruijinglin
 * Date: 2018/2/2
 * Explain:      .
 */

public class NetUtils {

    /**
     * 监测网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}
