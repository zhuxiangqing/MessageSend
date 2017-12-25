package com.zhuxiangqing.messageforwarder.repository;

import android.text.TextUtils;

import com.zhuxiangqing.messageforwarder.api.ThinkerjetService;
import com.zhuxiangqing.messageforwarder.entity.BaseEntity;
import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class SMSRepository {
    private ThinkerjetService thinkerjetService;
    private SharedPrefrenceHelper helper;

    @Inject
    public SMSRepository(ThinkerjetService thinkerjetService,
                         SharedPrefrenceHelper helper) {
        this.thinkerjetService = thinkerjetService;
        this.helper = helper;
    }

    public void sendSMSToRemote(int subId,String phoneNumber, String imsi, String smsContent, String remark, Callback<BaseEntity> call) {
        if (TextUtils.isEmpty(phoneNumber)){
            switch (subId){
                case 0:
                    phoneNumber = helper.getValue("card_one");
                    break;
                case 1:
                    phoneNumber = helper.getValue("card_two");
                    break;
            }
        }
        Call<BaseEntity> smsCall = thinkerjetService.smsContent(phoneNumber, imsi, smsContent, remark);
        smsCall.enqueue(call);
    }


}
