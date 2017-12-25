package com.zhuxiangqing.messageforwarder.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zhuxiangqing.messageforwarder.SingleLiveEvent;
import com.zhuxiangqing.messageforwarder.repository.CardRepository;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class MainViewModel extends AndroidViewModel {

    public final ObservableField<String> card1 = new ObservableField<>();
    public final ObservableField<String> card2 = new ObservableField<>();

    public final SingleLiveEvent<Void> submitClickEvent = new SingleLiveEvent<>();

    private CardRepository cardRepository;


    @Inject
    public MainViewModel(@NonNull Application application, CardRepository cardRepository) {
        super(application);
        this.cardRepository = cardRepository;
    }


    public void observe(LifecycleOwner owner, final MainNavigator navigator) {
        submitClickEvent.observe(owner, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                navigator.startSMSService();
            }
        });
    }


    public void onSubmitClick() {
        submitClickEvent.call();
    }

}
