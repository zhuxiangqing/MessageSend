package com.zhuxiangqing.messageforwarder.di;

import com.zhuxiangqing.messageforwarder.login.LoginActivity;
import com.zhuxiangqing.messageforwarder.login.LoginModule;
import com.zhuxiangqing.messageforwarder.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2017/12/20.
 *
 */
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector()
    abstract MainActivity mainActivity();


}
