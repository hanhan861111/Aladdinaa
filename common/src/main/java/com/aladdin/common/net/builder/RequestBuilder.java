package com.aladdin.common.net.builder;

import com.aladdin.common.net.HttpRequest;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * @author: Ruijinglin
 * @date: 2018/2/3/0003
 * Explain:      .
 */

public abstract class RequestBuilder {

    protected String mUrl = "";
    protected Map<String, String> mParams;
    protected Map<String, String> mHeaders;

    protected abstract Request buildRequest();

    public RequestBuilder() {
        init();
    }

    /**
     * 配置默认的属性
     */
    private void init() {
        mParams = new HashMap<>();
        mParams.put("token", "");
        mHeaders = new HashMap<>();
        mHeaders.put("Accept", "*/*");
    }


    /**
     * 设置Url
     *
     * @param url
     * @return
     */
    public RequestBuilder setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 添加请求头
     *
     * @param key
     * @param value
     * @return
     */
    public RequestBuilder addHeader(String key, String value) {
        if (this.mHeaders == null) {
            mHeaders = new HashMap<>();
        }
        mHeaders.put(key, value);
        return this;
    }

    /**
     * 设置新的请求头
     *
     * @param map
     * @return
     */
    public RequestBuilder setHeader(Map<String, String> map) {
        if (mHeaders != null) {
            mHeaders.clear();
        }
        mHeaders = map;
        return this;
    }

    /**
     * 设置新的请求头
     *
     * @return
     */
    public RequestBuilder noHeader() {
        if (mHeaders != null) {
            mHeaders.clear();
        }
        return this;
    }

    /**
     * 设置请求参数
     *
     * @param map
     * @return
     */
    public RequestBuilder setParams(Map<String, String> map) {
        if (mParams != null) {
            mParams.clear();
        }
        this.mParams = map;
        return this;
    }

    /**
     * 添加请求参数
     *
     * @param key
     * @param value
     * @return
     */
    public RequestBuilder addParams(String key, String value) {
        if (this.mParams == null) {
            mParams = new HashMap<>();
        }
        mParams.put(key, value);
        return this;
    }

    /**
     * 添加请求参数
     *
     * @param key
     * @return
     */
    public RequestBuilder noParams(String key) {
        if (this.mParams != null) {
            mParams.remove(key);
        }
        return this;
    }


    public HttpRequest builder() {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.request(buildRequest());
        return httpRequest;
    }

}
