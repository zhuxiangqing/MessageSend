package com.zhuxiangqing.messageforwarder.ui.log;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuxiangqing.messageforwarder.base.BaseInjectableFragment;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2018/1/2.
 */

public class LogFragment extends BaseInjectableFragment {
    @Inject
    ViewModelProvider.Factory factory;

    public static LogFragment create(){
        return new LogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
