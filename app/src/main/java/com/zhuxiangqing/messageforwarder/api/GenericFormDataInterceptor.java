package com.zhuxiangqing.messageforwarder.api;

import android.util.Pair;

import com.thinkerjet.apisafe.ApiSafe;
import com.zhuxiangqing.messageforwarder.utils.AppInfo;
import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.multibindings.IntoMap;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhuxi on 2017/12/21.
 */

public class GenericFormDataInterceptor implements Interceptor {

    private SharedPrefrenceHelper helper;
    private AppInfo info;

    @Inject
    public GenericFormDataInterceptor(SharedPrefrenceHelper helper, AppInfo info) {
        this.helper = helper;
        this.info = info;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        String noceStr = ApiSafe.getNonceStr(8);
        String time = ApiSafe.getTimestamp();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("ver", String.valueOf(info.getBuildVerison()))
                .addQueryParameter("os_ver", android.os.Build.VERSION.RELEASE)
                .addQueryParameter("os", String.valueOf(info.getConfigOs()))
                .addQueryParameter("token", helper.getValue("token"))
                .addQueryParameter("timestamp", time)
                .addQueryParameter("noncestr", noceStr)
                .addQueryParameter("signature",
                        ApiSafe.getApiSignature(helper.getValue("token")
                                , time
                                , noceStr))
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
