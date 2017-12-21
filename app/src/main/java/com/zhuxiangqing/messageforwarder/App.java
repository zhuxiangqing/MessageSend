package com.zhuxiangqing.messageforwarder;

import android.app.Activity;
import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by zhuxi on 2017/12/20.
 */

public class App extends Application implements HasActivityInjector {
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
