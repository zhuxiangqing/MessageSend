package com.zhuxiangqing.messageforwarder.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2017/12/25.
 *
 */
@Module
public abstract class MainModule {
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();
}
