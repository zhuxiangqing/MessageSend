package com.zhuxiangqing.messageforwarder.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class SharedPrefrenceHelper {

    SharedPreferences sp;

    @Inject
    public SharedPrefrenceHelper(SharedPreferences sp) {
        this.sp = sp;
    }

    public void putValue(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public String getValue(String key) {
        return sp.getString(key, "");
    }

    public void remove(String key){
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }
}
