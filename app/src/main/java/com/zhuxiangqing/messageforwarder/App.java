package com.zhuxiangqing.messageforwarder;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;

import com.zhuxiangqing.messageforwarder.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasServiceInjector;

/**
 * Created by zhuxi on 2017/12/20.
 */

public class App extends Application implements HasActivityInjector,HasServiceInjector,HasBroadcastReceiverInjector{
    @Inject//miss this got:activityInjector() returned null
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceAndroidInjector;
    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceAndroidInjector;
    }

    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverDispatchingAndroidInjector;
    }
}
