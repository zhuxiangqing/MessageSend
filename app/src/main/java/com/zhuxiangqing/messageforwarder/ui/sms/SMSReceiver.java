package com.zhuxiangqing.messageforwarder.ui.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent smsIntent = new Intent(context, SMSTaskService.class);
        smsIntent.putExtra("intent", intent);
        context.startService(smsIntent);
    }


}
