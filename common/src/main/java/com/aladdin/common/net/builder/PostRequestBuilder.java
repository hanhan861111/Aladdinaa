package com.aladdin.common.net.builder;

import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: Ruijinglin
 * @date: 2018/2/5/0005
 * Explain:      .
 */

public class PostRequestBuilder extends RequestBuilder {

    private MediaType mMediaType;
    private String mContent;

    public PostRequestBuilder setMediaType(MediaType type) {
        this.mMediaType = type;
        return this;
    }

    public PostRequestBuilder setContent(String content) {
        this.mContent = content;
        return this;
    }

    @Override
    protected Request buildRequest() {

        Request.Builder requestBuilder = new Request.Builder()
                .url(mUrl);

        RequestBody requestBody = null;

        if (mMediaType != null && mContent != null) {
            requestBody=RequestBody.create(mMediaType, mContent);
        } else {

            FormBody.Builder formBuilder = new FormBody.Builder();

            Set<Map.Entry<String, String>> paramsEntries = mParams.entrySet();
            for (Map.Entry<String, String> paramsEntry : paramsEntries) {
                formBuilder.add(paramsEntry.getKey(), paramsEntry.getValue());
            }

            Set<Map.Entry<String, String>> headerEntries = mHeaders.entrySet();
            for (Map.Entry<String, String> headerEntry : headerEntries) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
            requestBody = formBuilder.build();
        }

        return requestBuilder.post(requestBody).build();
    }
}