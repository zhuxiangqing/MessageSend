package com.zhuxiangqing.messageforwarder.base;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.zhuxiangqing.messageforwarder.di.Injectable;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by zhuxi on 2017/12/18.
 *
 */

public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector,Injectable  {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
