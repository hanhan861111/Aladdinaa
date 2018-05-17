package com.aladdin.common.net.builder;

import java.util.Map;
import java.util.Set;

import okhttp3.Request;

/**
 * @author: Ruijinglin
 * @date: 2018/2/6/0006
 * Explain:      .
 */

public class GetRequestBuilder extends RequestBuilder {

    @Override
    protected Request buildRequest() {

        Request.Builder builder = new Request.Builder()
                .url(mUrl)
                .get();

        Set<Map.Entry<String, String>> entries = mHeaders.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }
}
