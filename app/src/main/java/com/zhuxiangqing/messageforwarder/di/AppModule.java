package com.zhuxiangqing.messageforwarder.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zhuxiangqing.messageforwarder.api.GenericFormDataInterceptor;
import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;
import com.zhuxiangqing.messageforwarder.utils.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhuxi on 2017/12/21.
 *
 */
@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Singleton
    @Provides
    ThinkerjetService provideThinkerjetAPI(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://www.ln178.com/app/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ThinkerjetService.class);
    }

    @Provides
    OkHttpClient provideOkHttpClient(GenericFormDataInterceptor formDataInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("network", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(formDataInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreference(Application application) {
        SharedPreferences sp = application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
        return sp;
    }


}
