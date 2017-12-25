package com.zhuxiangqing.messageforwarder.ui.login;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2017/12/20.
 *
 */
@Module
public abstract class LoginModule {
    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();
}
