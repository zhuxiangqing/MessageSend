package com.zhuxiangqing.messageforwarder.di;

import com.zhuxiangqing.messageforwarder.api.GenericFormDataInterceptor;
import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhuxi on 2017/12/21.
 */
@Module(includes = {ViewModelModule.class})
public abstract class AppModule {

    @Singleton
    @Provides
    ThinkerjetService provideThinkerjetAPI(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ThinkerjetService.class);
    }

    @Provides
    OkHttpClient provideOkHttpClient(GenericFormDataInterceptor formDataInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(formDataInterceptor)
                .build();
    }


}
