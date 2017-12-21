package com.zhuxiangqing.messageforwarder.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zhuxiangqing.messageforwarder.AppExecutors;
import com.zhuxiangqing.messageforwarder.api.ApiResponse;
import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;
import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;
import com.zhuxiangqing.messageforwarder.vo.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zhuxi on 2017/12/18.
 *
 */
@Singleton
public class UserRepository {
    private ThinkerjetService thinkerjetService;
    private AppExecutors appExecutors;

    @Inject
    public UserRepository(ThinkerjetService thinkerjetService,
                          AppExecutors appExecutors) {
        this.thinkerjetService = thinkerjetService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<LoginEntity>> login(final String account, final String password) {
        return new NetworkBoundResource<LoginEntity, LoginEntity>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull LoginEntity item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable LoginEntity data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<LoginEntity> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<LoginEntity>> createCall() {
                return thinkerjetService.doLogin(account, password, "");
            }
        }.asLiveData();
    }
}
