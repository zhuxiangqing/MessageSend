package com.zhuxiangqing.messageforwarder.api;

import android.arch.lifecycle.LiveData;

import com.zhuxiangqing.messageforwarder.entity.BaseEntity;
import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhuxi on 2017/12/21.
 */

public interface ThinkerjetService {
    @FormUrlEncoded
    @POST("doLogin")
    LiveData<ApiResponse<LoginEntity>> doLogin(
            @Field("loginName") String loginName,
            @Field("loginPwd") String loginPwd,
            @Field("clientId") String clientId
    );


    /*
    /app/smsContent/smsContent

    1) token
1) phoneNumber  号码
1) imsi  IMSI
1) smsContent  短信内容
1) remark  备注
     */
    @FormUrlEncoded
    @POST("smsContent/smsContent")
    Call<BaseEntity> smsContent(
            @Field("phoneNumber") String phoneNumber,
            @Field("imsi") String imsi,
            @Field("smsContent") String smsContent,
            @Field("remark") String remark
    );
}
