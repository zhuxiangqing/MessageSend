package com.zhuxiangqing.messageforwarder.di;

import android.app.Application;

import com.zhuxiangqing.messageforwarder.App;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by zhuxi on 2017/12/20.
 *
 */
@Component(modules = {AndroidSupportInjectionModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance Application application();
        AppComponent build();
    }
    void inject(App app);
}
