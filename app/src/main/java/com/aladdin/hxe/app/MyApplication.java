package com.aladdin.hxe.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;

import com.aladdin.common.base.BaseApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ${韩永光} on on 2017/11/14 0014 15:30..
 */

public class MyApplication extends BaseApplication {
    private static int mainThreadId;
    private static ExecutorService threadPool;
    @Override
    public void onCreate() {
        super.onCreate();
        //程序一启动，就将未捕获异常初始化
        //CrashUtil.getInstance().init();
//        SingletonHolder.INSTANCE = this;
      //  AppInfo.init(getApplicationContext());z
        //获取主线程ID
        mainThreadId = Process.myTid();
        //创建线程池
        threadPool = Executors.newFixedThreadPool(5);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    private Activity activity;

    private ActivityLifecycleCallbacks lifecycleCallback = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                MyApplication.this.activity = activity;
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                MyApplication.this.activity = activity;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                MyApplication.this.activity = activity;
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    protected static class SingletonHolder {
        public static MyApplication INSTANCE;
    }

    public MyApplication() {
        registerActivityLifecycleCallbacks(lifecycleCallback);
        SingletonHolder.INSTANCE = this;
    }


    public void onLowMemory() {
        System.gc();
        System.runFinalization();
        System.gc();
        super.onLowMemory();
    }

    public Activity getCurrentActivity() {
        return activity;
    }

    public static MyApplication get() {
        return SingletonHolder.INSTANCE;
    }
}
