package com.zhuxiangqing.messageforwarder.ui.login;

import android.os.Bundle;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseActivity;

/*
 HasSupportFragmentInjector for v4.Fragment
 */
public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        if (savedInstanceState == null) {
            LoginFragment loginFragment = LoginFragment.create();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, loginFragment, loginFragment.getClass().toString())
                    .commit();
        }
    }

}
