package com.zhuxiangqing.messageforwarder.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zhuxiangqing.messageforwarder.SingleLiveEvent;
import com.zhuxiangqing.messageforwarder.repository.CardRepository;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class MainViewModel extends AndroidViewModel {

    //EditText card1
    // input text;String
    public final ObservableField<String> card1 = new ObservableField<>("13591607767");
    // Observable Fields over
//    //error: String
//    public final ObservableField<String> card1ErrorMsg = new ObservableField<>();
//    //forced? boolean
//    public final ObservableBoolean card1Forced = new ObservableBoolean(false);
//
    public final ObservableField<String> card2 = new ObservableField<>("15040262427");

    public final ObservableBoolean submitEnabled = new ObservableBoolean(false);


    public final ObservableBoolean numberHadSaved = new ObservableBoolean(false);

    public final SingleLiveEvent<Void> submitClickEvent = new SingleLiveEvent<>();

    private CardRepository cardRepository;


    @Inject
    public MainViewModel(@NonNull Application application, CardRepository cardRepository) {
        super(application);
        this.cardRepository = cardRepository;

    }

    @Inject
    public void init() {
        numberHadSaved.set(numberHadSaved());
        card1.set(cardRepository.getCardOne());
        card2.set(cardRepository.getCardTwo());
    }

    boolean numberHadSaved() {
        return !TextUtils.isEmpty(cardRepository.getCardOne()) && !TextUtils.isEmpty(cardRepository.getCardTwo());
    }


    void observe(LifecycleOwner owner, final MainNavigator navigator) {
        submitClickEvent.observe(owner, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                navigator.startSMSService();
            }
        });
        if (numberHadSaved()){
            submitClickEvent.call();
        }
    }

    public void onCardOneChanged(CharSequence cardOne) {
        submitEnabled.set(!TextUtils.isEmpty(cardOne.toString()) && !TextUtils.isEmpty(card2.get()));
    }

    public void onCardTwoChanged(CharSequence cardTwo) {
        submitEnabled.set(!TextUtils.isEmpty(cardTwo.toString()) && !TextUtils.isEmpty(card1.get()));
    }


    public void onNumClearClick() {
        cardRepository.clearNum();
        init();
    }

    public void onSubmitClick() {
        //仅作为拦截
        if (TextUtils.isEmpty(card1.get()) || TextUtils.isEmpty(card2.get())) {
            return;
        }
        cardRepository.saveCardOne(card1.get());
        cardRepository.saveCardTwo(card2.get());
        init();
        submitClickEvent.call();
    }

}
