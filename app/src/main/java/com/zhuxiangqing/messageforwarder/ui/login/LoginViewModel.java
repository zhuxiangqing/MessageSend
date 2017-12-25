package com.zhuxiangqing.messageforwarder.ui.login;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zhuxiangqing.messageforwarder.SingleLiveEvent;
import com.zhuxiangqing.messageforwarder.api.ApiResponse;
import com.zhuxiangqing.messageforwarder.entity.login.LoginEntity;
import com.zhuxiangqing.messageforwarder.repository.UserRepository;
import com.zhuxiangqing.messageforwarder.utils.AbsentLiveData;

import javax.inject.Inject;

/**
 * created by Zhu
 */
public class LoginViewModel extends AndroidViewModel {


    public final ObservableField<String> account = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final ObservableBoolean inputEnabled = new ObservableBoolean(true);
    public final ObservableBoolean loginEnabled = new ObservableBoolean(false);
    //登陆按钮点击触发
    private final MutableLiveData<String> loginEvent = new MutableLiveData<>();
    private LiveData<ApiResponse<LoginEntity>> loginResult;

    //跳转到MainPage的Event
    private final SingleLiveEvent<Void> openMainPageEvent = new SingleLiveEvent<>();

    private UserRepository repository;

    @Inject
    LoginViewModel(@NonNull final Application application, final UserRepository userRepository) {
        super(application);
        this.repository = userRepository;
        loginResult = Transformations.switchMap(loginEvent, new Function<String, LiveData<ApiResponse<LoginEntity>>>() {
            @Override
            public LiveData<ApiResponse<LoginEntity>> apply(String input) {
                if (TextUtils.isEmpty(account.get()) || TextUtils.isEmpty(password.get())) {
                    return AbsentLiveData.create();
                }
                return repository.login(account.get(), password.get());
            }
        });

    }


    private void setInputAndClickEnabled(boolean enabled) {
        inputEnabled.set(enabled);
        loginEnabled.set(enabled);
    }


    void observe(LifecycleOwner owner, final LoginNavigator navigator) {
        loginResult.observe(owner, new Observer<ApiResponse<LoginEntity>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<LoginEntity> loginEntityApiResponse) {
                setInputAndClickEnabled(true);
                LoginEntity body = loginEntityApiResponse.body;
                if (null != body) {
                    switch (body.getErr()) {
                        case 0:
                            repository.saveLoginInfo(body.getToken());
                            navigator.openMain();
                            break;
                        default:
                            navigator.toastMessage("未知错误");
                            break;
                    }
                } else {
                    navigator.toastMessage("未知错误");
                }
            }
        });
        openMainPageEvent.observe(owner, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                navigator.openMain();
            }
        });
    }


    //登陆按钮点击事件
    public void loginClick() {
        //
        setInputAndClickEnabled(false);
        loginEvent.setValue("");
    }

    /*
    onTextChanged 方法优先于Observable会被调用 所以使用account 来判断是否不为空 不太准确
     */
    public void onAccountChanged(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence.toString())) {
            loginEnabled.set(false);
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            loginEnabled.set(false);
            return;
        }
        loginEnabled.set(true);

    }

    public void onPasswordChanged(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence.toString())) {
            loginEnabled.set(false);
            return;
        }
        if (TextUtils.isEmpty(account.get())) {
            loginEnabled.set(false);
            return;
        }
        loginEnabled.set(true);
    }


}
