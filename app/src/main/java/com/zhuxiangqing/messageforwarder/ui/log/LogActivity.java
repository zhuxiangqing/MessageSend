package com.zhuxiangqing.messageforwarder.ui.log;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseActivity;

/**
 * Created by zhuxi on 2018/1/2.
 */

public class LogActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        if (null == savedInstanceState) {
            LogFragment logFragment = LogFragment.create();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, logFragment, LogFragment.class.toString())
                    .commit();
        }
    }
}
