package com.zhuxiangqing.messageforwarder.ui.launcher;

import android.Manifest;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zhuxiangqing.messageforwarder.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by zhuxi on 2017/12/25.
 *
 */

public class LauncherViewModel extends AndroidViewModel {

    private final SingleLiveEvent<Void> launcherEvent = new SingleLiveEvent<>();
    private static final int RC_SMS_PERM = 123;


    @Inject
    public LauncherViewModel(@NonNull Application application) {
        super(application);
    }

    void observeAndCall(LifecycleOwner owner, final LauncherNavigator navigator) {
        launcherEvent.observe(owner, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
//                navigator

            }
        });
        launcherEvent.call();
    }





}
