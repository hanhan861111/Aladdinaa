package com.aladdin.common.net;

import android.util.Log;

import com.aladdin.common.net.builder.GetRequestBuilder;
import com.aladdin.common.net.builder.JsonRequestBuilder;
import com.aladdin.common.net.builder.PostRequestBuilder;
import com.aladdin.common.net.listener.IRequestBeanListener;

/**
 * User: Ruijinglin
 * Date: 2018/2/2
 * Explain: 请求的管理类，这里是所有请求的入口    .
 * <p>
 * 处理公共参数(token)
 * 统一处理请求的响应，token失效前往登录界面
 * 请求的缓存、响应时长
 * 响应的线程
 */

public class RequestManager {

    /**
     * 提交一个post请求
     *
     * @return
     */
    public static PostRequestBuilder post() {
        return new PostRequestBuilder();
    }

    /**
     * 提交一个post json形式的请求
     *
     * @return
     */
    public static JsonRequestBuilder postJson() {
        return new JsonRequestBuilder();
    }

    /**
     * 提交一个get请求
     */
    public static GetRequestBuilder get() {
        return new GetRequestBuilder();
    }


    private void demo(){

        /**
         * 具体的参数、url、请求头等信息的构建 在RequestBuilder中 方法都有注释
         * 最后调用builder方法得到HttpRequest实例 其中onUI方法设置回调在主线程，默认是在子线程
         * setCallback设置响应结果的回调，IRequestBeanListener的泛型 是指定的实体类，可以直接得到解析完的对象，
         * 如果不想要解析好的bean，可以指定String,得到请求原始的json等数据
         */
        RequestManager.get()
                .setUrl("http://192.168.1.30:8080/response.jsp")
                .noHeader()
                .noParams("token")
                .builder()
                .setCallback(new IRequestBeanListener<String>() {
                    @Override
                    public void onSuccess(String baen) {
                        Log.e("TAG", "onSuccess: -----------: "+ baen);
                        Log.e("TAG", "Thread: -----------:" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

}
