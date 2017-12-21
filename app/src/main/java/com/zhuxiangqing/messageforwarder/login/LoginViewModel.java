package com.zhuxiangqing.messageforwarder.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;
import com.zhuxiangqing.messageforwarder.repository.UserRepository;
import com.zhuxiangqing.messageforwarder.vo.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/18.
 */

public class LoginViewModel extends AndroidViewModel {

    public final ObservableField<String> account = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();



    private UserRepository userRepository;
    private LiveData<Resource<LoginEntity>> userResult;

    @Inject
    public LoginViewModel(@NonNull Application application, UserRepository userRepository) {
        super(application);
        this.userRepository = userRepository;
    }

    public ObservableField<String> getAccount() {
        return account;
    }

    public ObservableField<String> getPassword() {
        return password;
    }


    public LiveData<Resource<LoginEntity>> login() {
        userResult = userRepository.login(account.get(), password.get());
        return userResult;
    }
}
