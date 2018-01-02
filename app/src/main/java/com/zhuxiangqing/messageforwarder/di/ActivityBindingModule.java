package com.zhuxiangqing.messageforwarder.di;

import com.zhuxiangqing.messageforwarder.ui.launcher.LauncherActivity;
import com.zhuxiangqing.messageforwarder.ui.launcher.LauncherModule;
import com.zhuxiangqing.messageforwarder.ui.log.LogActivity;
import com.zhuxiangqing.messageforwarder.ui.log.LogModule;
import com.zhuxiangqing.messageforwarder.ui.main.MainActivity;
import com.zhuxiangqing.messageforwarder.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2017/12/20.
 */
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = LauncherModule.class)
    abstract LauncherActivity launcherActivity();


    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = LogModule.class)
    abstract LogActivity logActivity();


}
