package com.zhuxiangqing.messageforwarder.repository;

import android.text.TextUtils;

import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;
import com.zhuxiangqing.messageforwarder.entity.BaseEntity;
import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class SMSRepository {
    private ThinkerjetService thinkerjetService;
    private CardRepository cardRepository;

    @Inject
    public SMSRepository(ThinkerjetService thinkerjetService,
                         CardRepository cardRepository) {
        this.thinkerjetService = thinkerjetService;
        this.cardRepository = cardRepository;
    }

    public Observable<BaseEntity> sendSMSToRemote(int subId, String phoneNumber, String imsi, String smsContent, String remark) {
        if (TextUtils.isEmpty(phoneNumber)) {
            switch (subId) {
                case 0:
                    phoneNumber = cardRepository.getCardOne();
                    break;
                case 1:
                    phoneNumber = cardRepository.getCardTwo();
                    break;
            }
        }
        return thinkerjetService.smsContent(phoneNumber, imsi, smsContent, remark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        Disposable disposable = thinkerjetService.smsContent(phoneNumber, imsi, smsContent, remark)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseEntity>() {
//                    @Override
//                    public void accept(BaseEntity baseEntity) throws Exception {
//
//                    }
//                });
//        smsCall.enqueue(new Callback<BaseEntity>() {
//            @Override
//            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<BaseEntity> call, Throwable t) {
//
//            }
//        });
    }


}
