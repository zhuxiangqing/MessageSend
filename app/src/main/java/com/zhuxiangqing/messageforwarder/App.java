package com.zhuxiangqing.messageforwarder;

import android.app.Activity;
import android.app.Application;

import com.zhuxiangqing.messageforwarder.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by zhuxi on 2017/12/20.
 */

public class App extends Application implements HasActivityInjector {
    @Inject//miss this got:activityInjector() returned null
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
