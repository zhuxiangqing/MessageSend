package com.zhuxiangqing.messageforwarder.di;

import com.zhuxiangqing.messageforwarder.ui.sms.SMSReceiver;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhuxi on 2017/12/26.
 *
 */

@Module
public abstract class BroadCastBindingModule {
    @ContributesAndroidInjector
    abstract SMSReceiver contributeSMSReceiver();
}
