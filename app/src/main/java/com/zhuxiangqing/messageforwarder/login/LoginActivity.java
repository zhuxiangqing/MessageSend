package com.zhuxiangqing.messageforwarder.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseActivity;
import com.zhuxiangqing.messageforwarder.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class LoginActivity extends BaseActivity implements HasFragmentInjector, LoginNavigator {
    @Inject DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
