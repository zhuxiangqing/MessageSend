package com.zhuxiangqing.messageforwarder.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseFragment;
import com.zhuxiangqing.messageforwarder.databinding.FragmentLoginBinding;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/18.
 *
 */

public class LoginFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        FragmentLoginBinding binding = FragmentLoginBinding.bind(view);
        //ViewModel
        loginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
