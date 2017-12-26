package com.zhuxiangqing.messageforwarder.ui.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import javax.inject.Inject;

import dagger.android.DaggerBroadcastReceiver;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class SMSReceiver extends DaggerBroadcastReceiver {

    @Inject
    SharedPrefrenceHelper helper;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);
        if (TextUtils.isEmpty(helper.getValue("card_one"))||TextUtils.isEmpty(helper.getValue("card_two"))){
            return;
        }
        Intent smsIntent = new Intent(context, SMSTaskService.class);
        smsIntent.putExtra("intent", intent);
        context.startService(smsIntent);
    }


}
