package com.zhuxiangqing.messageforwarder.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.zhuxiangqing.messageforwarder.ui.launcher.LauncherViewModel;
import com.zhuxiangqing.messageforwarder.ui.login.LoginViewModel;
import com.zhuxiangqing.messageforwarder.ui.main.MainViewModel;
import com.zhuxiangqing.messageforwarder.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by zhuxi on 2017/12/21.
 *
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel.class)
    abstract ViewModel bindLauncherViewModel(LauncherViewModel launcherViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
