package com.zhuxiangqing.messageforwarder.ui.launcher;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2017/12/25.
 */

@Module
public abstract class LauncherModule {

    @ContributesAndroidInjector
    abstract LauncherFragment contributeLauncherFragment();
}
