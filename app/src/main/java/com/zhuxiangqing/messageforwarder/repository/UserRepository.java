package com.zhuxiangqing.messageforwarder.repository;

import android.arch.lifecycle.LiveData;

import com.zhuxiangqing.messageforwarder.AppExecutors;
import com.zhuxiangqing.messageforwarder.api.ApiResponse;
import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;
import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;
import com.zhuxiangqing.messageforwarder.utils.MD5Util;
import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zhuxi on 2017/12/18.
 */
@Singleton
public class UserRepository {
    private ThinkerjetService thinkerjetService;
    private AppExecutors appExecutors;
    private SharedPrefrenceHelper helper;


    @Inject
    public UserRepository(ThinkerjetService thinkerjetService,
                          AppExecutors appExecutors,
                          SharedPrefrenceHelper helper) {
        this.thinkerjetService = thinkerjetService;
        this.appExecutors = appExecutors;
        this.helper = helper;
    }

    public LiveData<ApiResponse<LoginEntity>> login(String account, String password) {
        return thinkerjetService.doLogin(account, MD5Util.md5(password), "");
    }

    public void saveLoginInfo(String token) {
        helper.putString("token", token);
    }

}
