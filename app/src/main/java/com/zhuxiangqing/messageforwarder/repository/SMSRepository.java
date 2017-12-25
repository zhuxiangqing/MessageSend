package com.zhuxiangqing.messageforwarder.repository;

import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;
import com.zhuxiangqing.messageforwarder.entity.BaseEntity;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class SMSRepository {
    private ThinkerjetService thinkerjetService;

    @Inject
    public SMSRepository(ThinkerjetService thinkerjetService) {
        this.thinkerjetService = thinkerjetService;
    }

    public void sendSMSToRemote(String phoneNumber, String imsi, String smsContent, String remark, Callback<BaseEntity> call) {
        Call<BaseEntity> smsCall = thinkerjetService.smsContent(phoneNumber, imsi, smsContent, remark);
        smsCall.enqueue(call);
    }


}
