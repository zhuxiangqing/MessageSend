package com.zhuxiangqing.messageforwarder.network;

import com.zhuxiangqing.messageforwarder.network.api.API;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zhuxi on 2017/12/18.
 */

public class Network {

    private Network() {
    }

    private static class NetworkHolder {
        private static Network mInstance = new Network();
    }

    private API api;

    private API createAPI() {
        if (null == api) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .build();
            api = retrofit.create(API.class);
        }
        return api;
    }

    public static API getAPI() {
        return NetworkHolder.mInstance.createAPI();
    }
}
