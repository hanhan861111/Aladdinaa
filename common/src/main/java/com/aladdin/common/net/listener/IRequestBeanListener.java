package com.aladdin.common.net.listener;

import com.aladdin.common.utils.GsonUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: Ruijinglin
 * @date: 2018/2/6/
 * Explain:      .
 */

public abstract class IRequestBeanListener<T> implements IRequestListener {

    public abstract void onSuccess(T baen);

    public void onResponse(String s) {

        Type type = getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> entityClass = (Class) types[0];

        if (entityClass == String.class) {
            onSuccess((T) s);
            return;
        }
        T t = GsonUtils.getGson().fromJson(s, entityClass);
        onSuccess(t);
    }

}
