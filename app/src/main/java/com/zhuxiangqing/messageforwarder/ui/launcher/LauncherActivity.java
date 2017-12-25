package com.zhuxiangqing.messageforwarder.ui.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseActivity;

/**
 * Created by zhuxi on 2017/12/25.
 *
 */

public class LauncherActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        if (null == savedInstanceState){
            LauncherFragment fragment = LauncherFragment.create();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame,fragment)
                    .commit();
        }
    }
}
