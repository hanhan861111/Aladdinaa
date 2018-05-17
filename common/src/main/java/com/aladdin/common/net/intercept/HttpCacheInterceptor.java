package com.aladdin.common.net.intercept;

import com.aladdin.common.base.BaseApplication;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.aladdin.common.utils.NetUtils.isInternetConnection;

/**
 * User: Ruijinglin
 * Date: 2018/2/2/0002
 * Explain:      .
 */

public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!isInternetConnection(BaseApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);

        if (isInternetConnection(BaseApplication.getContext())) {
            // read from cache for 1 minute
            int maxAge = 60 * 60;
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            // tolerate 4-weeks stale
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return response;
    }

}
