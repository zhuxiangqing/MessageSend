package com.zhuxiangqing.messageforwarder.api;

import android.arch.lifecycle.LiveData;

import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhuxi on 2017/12/21.
 *
 */

public interface ThinkerjetService {
    @FormUrlEncoded
    @POST("doLogin")
    LiveData<ApiResponse<LoginEntity>> doLogin(
            @Field("loginName") String loginName,
            @Field("loginPwd") String loginPwd,
            @Field("clientId") String clientId
    );
}
