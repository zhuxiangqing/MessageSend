package com.zhuxiangqing.messageforwarder.ui.log;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2018/1/2.
 *
 */
@Module
public abstract class LogModule {
    @ContributesAndroidInjector
    abstract LogFragment logFragment();
}
