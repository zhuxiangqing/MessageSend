package com.zhuxiangqing.messageforwarder.api;

import android.util.Pair;

import com.thinkerjet.apisafe.ApiSafe;

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
 *
 */

public class GenericFormDataInterceptor implements Interceptor {

    @Inject
    public GenericFormDataInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String noceStr = ApiSafe.getNonceStr(8);
        String time = ApiSafe.getTimestamp();
        Request newRequest = chain.request().newBuilder()
//                .addHeader("ver", String.valueOf(ConvenientAppLike.getBuildVersion()))
                .addHeader("os_ver", android.os.Build.VERSION.RELEASE)
//                .addHeader("os", String.valueOf(JnConstants.CONFIG.OS))
//                .addHeader("token", XdData.getInstance().getToken())
                .addHeader("timestamp", time)
                .addHeader("noncestr", noceStr)
//                .addHeader("signature",
//                        ApiSafe.getApiSignature(XdSession.getInstance().getToken()
//                                , time
//                                , noceStr))
                .build();

        return chain.proceed(newRequest);
    }
}
