package com.aladdin.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ruijinglin
 * Date: 2018/2/2/0002
 * Explain:      .
 */

public class BaseApplication extends Application {

    private static Context mContext;
    private static Handler mHandler = new Handler();
    private static List<Activity> mActivitys = new ArrayList<>();

    private volatile static BaseApplication INSTANCE;

    public static BaseApplication getInstance() {
        if (INSTANCE == null) {
            synchronized (BaseApplication.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BaseApplication();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public void addActivity(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        mActivitys.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (Activity a : mActivitys) {
            if (a == activity) {
                if (!activity.isFinishing()) {
                    activity.finish();
                    activity = null;
                }
            }
        }
        mActivitys.remove(activity);

    }

    public void clearActivitys() {
        for (Activity a : mActivitys) {
            if (!a.isFinishing()) {
                a.finish();
                a = null;
            }
        }
        mActivitys.clear();
    }

    public void gotoLogin() {
//        startActivity(new Intent(this, LoginActivity.class));
    }
}
