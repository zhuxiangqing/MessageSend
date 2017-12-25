package com.zhuxiangqing.messageforwarder.ui.sms;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.zhuxiangqing.messageforwarder.ui.main.MainActivity;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class SMSService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //定义一个notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setContentTitle(getApplicationInfo().name)
                .build();

        //把该service创建为前台service
        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }
}
