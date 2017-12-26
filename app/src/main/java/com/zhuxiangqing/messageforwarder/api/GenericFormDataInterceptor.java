package com.zhuxiangqing.messageforwarder.api;

import android.app.Application;

import com.thinkerjet.apisafe.ApiSafe;
import com.zhuxiangqing.messageforwarder.utils.AppInfo;
import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhuxi on 2017/12/21.
 */

public class GenericFormDataInterceptor implements Interceptor {

    private SharedPrefrenceHelper helper;
    private Application application;

    @Inject
    public GenericFormDataInterceptor(SharedPrefrenceHelper helper, Application application) {
        this.helper = helper;
        this.application = application;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        String noceStr = ApiSafe.getNonceStr(8);
        String time = ApiSafe.getTimestamp();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("ver", String.valueOf(AppInfo.getBuildVerison(this.application)))
                .addQueryParameter("os_ver", android.os.Build.VERSION.RELEASE)
                .addQueryParameter("os", String.valueOf(AppInfo.getConfigOs()))
                .addQueryParameter("token", helper.getString("token"))
                .addQueryParameter("timestamp", time)
                .addQueryParameter("noncestr", noceStr)
                .addQueryParameter("signature",
                        ApiSafe.getApiSignature(helper.getString("token")
                                , time
                                , noceStr))
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
