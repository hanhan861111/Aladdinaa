package com.aladdin.common.utils;

import com.google.gson.Gson;

/**
 * User: Ruijinglin
 * Date: 2018/2/2/0002
 * Explain:      .
 */

public class GsonUtils {

    private static class Instance{
        public static final Gson instance = new Gson();
    }

    public static Gson getGson(){
        return Instance.instance;
    }

}
