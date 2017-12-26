package com.zhuxiangqing.messageforwarder.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 * 通用SharedPrefrence 操作方法
 */

public class SharedPrefrenceHelper {

    private SharedPreferences sp;

    @Inject
    public SharedPrefrenceHelper(SharedPreferences sp) {
        this.sp = sp;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public String getString(String key) {
        return sp.getString(key, "");
    }


    public void remove(String... keys) {
        SharedPreferences.Editor editor = sp.edit();
        for (String key : keys
                ) {
            editor.remove(key);
        }
        editor.apply();
    }
}
