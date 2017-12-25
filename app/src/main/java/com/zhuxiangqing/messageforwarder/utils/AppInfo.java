package com.zhuxiangqing.messageforwarder.utils;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 * 用于管理APP基本信息 如果构建版本号等
 */

public class AppInfo {
    private Application application;

    @Inject
    public AppInfo(Application application) {
        this.application = application;
    }


    /*
    获取版本号
     */
    public int getBuildVerison() {
        PackageInfo info;
        try {
            info = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }


    public int getConfigOs(){
        return 1;
    }
}
