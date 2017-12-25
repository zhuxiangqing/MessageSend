package com.zhuxiangqing.messageforwarder.ui.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseFragment;
import com.zhuxiangqing.messageforwarder.databinding.FragmentLoginBinding;
import com.zhuxiangqing.messageforwarder.di.Injectable;
import com.zhuxiangqing.messageforwarder.ui.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/18.
 *
 */

public class LoginFragment extends BaseFragment implements Injectable, LoginNavigator {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel loginViewModel;

    public static LoginFragment create() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        FragmentLoginBinding binding = FragmentLoginBinding.bind(view);
        //ViewModel
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel.observe(this, this);
    }


    @Override
    public void openMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void toastMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
