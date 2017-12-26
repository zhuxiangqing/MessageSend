package com.zhuxiangqing.messageforwarder.ui.sms;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.zhuxiangqing.messageforwarder.ui.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        //要监听整个sms 而不能写成监听sms/inbox
//        getContentResolver().registerContentObserver(Uri.parse("content://sms"),
//                true,
//                new ContentObserver(new Handler()) {
//                    @Override
//                    public void onChange(boolean selfChange, Uri uri) {
//                        super.onChange(selfChange, uri);
//                        //查询发送向箱中的短信
//                        Cursor cursor=getContentResolver().query(uri, null, null, null, null);
//                        //遍历查询结果获取用户正在发送的短信
//                        while (cursor.moveToNext()) {
//                            StringBuffer sb=new StringBuffer();
//                            Log.d("content_observer", "onChange: "+cursor.getColumnNames());
//                            //获取短信的发送地址
//                            for (String key:cursor.getColumnNames()
//                                 ) {
//                                sb.append(key+" : "+cursor.getString(cursor.getColumnIndex(key))+"\n");
//                            }
//                            sb.append("发送地址："+cursor.getString(cursor.getColumnIndex("address")));
//                            //
//                            sb.append("sub_id"+cursor.getInt(cursor.getColumnIndex("sub_id")));
//                            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
////                            telephonyManager.getSubscriberId();
//                            //获取短信的标题
//                            sb.append("\n标题："+cursor.getString(cursor.getColumnIndex("subject")));
//                            //获取短信的内容
//                            sb.append("\n内容："+cursor.getString(cursor.getColumnIndex("body")));
//                            //获取短信的发送时间
//                            Date date=new Date(cursor.getLong(cursor.getColumnIndex("date")));
//                            //格式化以秒为单位的日期
//                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
//                            sb.append("\n时间："+sdf.format(date));
//                            System.out.println("查询到的正在发送的短信："+sb.toString());
//                        }
//                    }
//                });
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
