package com.aladdin.common.net;

import android.util.Log;

import com.aladdin.common.base.BaseApplication;
import com.aladdin.common.net.intercept.HttpCacheInterceptor;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.common.net.listener.IRequestListener;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: Ruijinglin
 * Date: 2018/2/2/
 * Explain:      .
 */

public class HttpRequest {

    private final String TAG = HttpRequest.class.getSimpleName();

    //缓存OkHttpClient实例
    private static Map<String, OkHttpClient> mRequestMap = new LinkedHashMap<>();

    private IRequestListener mRequestListener;

    private Boolean mResponseOnUI = false;

    //设置连接超时时长
    private final long CONNECT_TIMEOUT = 10;

    public void request(Request request) {

        final String url = request.url().toString();
        OkHttpClient okHttpClient = getOkHttpClient(url);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mRequestListener == null) {
                    Log.e(TAG, "onFailure: ----------未设置相应的回调 + " + e.getMessage());
                }
                Log.e(TAG, "onFailure: ----------- :" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (mRequestListener == null) {
                    Log.e(TAG, "onResponse: ----------未设置相应的回调");
                }

                String stringResponse = "";

                try {
                    stringResponse = response.body().string();

                    //token失效处理
                    boolean noToken = stringResponse.contains("\"status\":\"301\"");
                    if (noToken) {
                        BaseApplication.getInstance().clearActivitys();
                        BaseApplication.getInstance().gotoLogin();
                        return;
                    }

                    //是否响应在UI线程
                    if (mResponseOnUI) {
                        final String finalStringResponse = stringResponse;
                        BaseApplication.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                disposeResponse(finalStringResponse);
                            }
                        });
                    } else {
                        disposeResponse(stringResponse);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "onResponse: Exception:-------------:" + e);
                }finally {
                    Log.e(TAG, "onResponse: 结果----------- :" + stringResponse);
                }

            }
        });
    }

    private void disposeResponse(String stringResponse) {
        if (mRequestListener instanceof IRequestBeanListener) {
            IRequestBeanListener baneListener = (IRequestBeanListener) HttpRequest.this.mRequestListener;
            baneListener.onResponse(stringResponse);
        }
    }

    /**
     * 通过url获取缓存或者创建新的OkHttpClient
     *
     * @param url
     * @return
     */
    private OkHttpClient getOkHttpClient(String url) {
        OkHttpClient okHttpClient = mRequestMap.get(url);
        if (okHttpClient == null) {
            synchronized (this) {
                okHttpClient = mRequestMap.get(url);
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HttpCacheInterceptor())
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 是否在主线程
     *
     * @return
     */
    public HttpRequest onUI() {
        mResponseOnUI = true;
        return this;
    }

    /**
     * 设置回调
     *
     * @param listener
     */
    public void setCallback(IRequestListener listener) {
        this.mRequestListener = listener;
    }
}
