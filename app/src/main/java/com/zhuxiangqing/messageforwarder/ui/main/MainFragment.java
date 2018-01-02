package com.zhuxiangqing.messageforwarder.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuxiangqing.messageforwarder.base.BaseInjectableFragment;
import com.zhuxiangqing.messageforwarder.databinding.FragmentMainBinding;
import com.zhuxiangqing.messageforwarder.di.Injectable;
import com.zhuxiangqing.messageforwarder.ui.sms.SMSService;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class MainFragment extends BaseInjectableFragment implements Injectable, MainNavigator {
    @Inject
    ViewModelProvider.Factory factory;

    private MainViewModel mainViewModel;

    public static MainFragment create() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMainBinding dataBinding = FragmentMainBinding.inflate(inflater, container, false);
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        dataBinding.setViewModel(mainViewModel);
        return dataBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.observe(this,this);
    }

    @Override
    public void startSMSService() {
        Intent intent = new Intent(getContext(), SMSService.class);
        getActivity().startService(intent);
    }
}
