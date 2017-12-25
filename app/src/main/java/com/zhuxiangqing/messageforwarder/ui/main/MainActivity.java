package com.zhuxiangqing.messageforwarder.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseActivity;

/**
 * Created by zhuxi on 2017/12/18.
 *
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            MainFragment mainFragment = MainFragment.create();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFrame, mainFragment)
                    .commit();
        }
    }



}
