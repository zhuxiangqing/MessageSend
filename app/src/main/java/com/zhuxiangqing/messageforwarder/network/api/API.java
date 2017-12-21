package com.zhuxiangqing.messageforwarder.network.api;

import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhuxi on 2017/12/18.
 */

public interface API {
    @FormUrlEncoded
    @POST("app/doLogin")
    Call<LoginEntity> doLogin(
            @Field("loginName") String loginName,
            @Field("loginPwd") String loginPwd,
            @Field("clientId") String clientId
    );
}
