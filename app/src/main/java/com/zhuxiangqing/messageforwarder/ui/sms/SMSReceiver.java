package com.zhuxiangqing.messageforwarder.ui.sms;

import android.content.Context;
import android.content.Intent;

import com.zhuxiangqing.messageforwarder.repository.CardRepository;

import javax.inject.Inject;

import dagger.android.DaggerBroadcastReceiver;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class SMSReceiver extends DaggerBroadcastReceiver {

    @Inject
    CardRepository cardRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);
        if (!cardRepository.hadNumberSaved()){
            return;
        }
        Intent smsIntent = new Intent(context, SMSTaskService.class);
        smsIntent.putExtra("intent", intent);
        context.startService(smsIntent);
    }


}
