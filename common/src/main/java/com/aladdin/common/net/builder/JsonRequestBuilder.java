package com.aladdin.common.net.builder;

import com.aladdin.common.utils.GsonUtils;

import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: Ruijinglin
 * @date: 2018/2/3/0003
 * Explain:      .
 */

public class JsonRequestBuilder extends RequestBuilder {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Request buildRequest() {
        String json = GsonUtils.getGson().toJson(mParams);
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder()
                .url(mUrl)
                .post(requestBody);
        Set<Map.Entry<String, String>> entries = mHeaders.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

}
