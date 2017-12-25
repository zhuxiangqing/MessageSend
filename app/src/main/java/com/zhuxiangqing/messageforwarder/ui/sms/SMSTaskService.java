package com.zhuxiangqing.messageforwarder.ui.sms;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.zhuxiangqing.messageforwarder.entity.BaseEntity;
import com.zhuxiangqing.messageforwarder.repository.SMSRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhuxi on 2017/12/24.
 *
 */

public class SMSTaskService extends IntentService {
    @Inject
    SMSRepository repository;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public SMSTaskService() {
        super("sms_task");
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SmsMessage[] arrayOfSmsMessage = Telephony.Sms.Intents.getMessagesFromIntent((Intent) intent.getParcelableExtra("intent"));
        try {
            Method localMethod1 = SmsMessage.class.getMethod("getSubId", new Class[0]);
            TelephonyManager localTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = "";
            String phoneNumber = "";
            int i = ((Integer) localMethod1.invoke(arrayOfSmsMessage[0], new Object[0])).intValue();
            if (Build.VERSION.SDK_INT >= 22) {
                SubscriptionManager subscriptionManager = SubscriptionManager.from(this);
                phoneNumber = subscriptionManager.getActiveSubscriptionInfo(i).getNumber();
                int subId = subscriptionManager.getActiveSubscriptionInfo(i).getSubscriptionId();
                //
                Class[] arrayOfClass2 = new Class[1];
                arrayOfClass2[0] = Integer.TYPE;
                Method localMethod3 = TelephonyManager.class.getMethod("getSubscriberId", arrayOfClass2);
                Object[] arrayOfObject2 = new Object[1];
                arrayOfObject2[0] = subId;
                imsi = String.valueOf(localMethod3.invoke(localTelephonyManager, arrayOfObject2));
            }
            String str2 = arrayOfSmsMessage[0].getMessageBody();
            repository.sendSMSToRemote(phoneNumber, imsi, str2, "", new Callback<BaseEntity>() {
                @Override
                public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {

                }

                @Override
                public void onFailure(Call<BaseEntity> call, Throwable t) {

                }
            });

            return;
        } catch (NoSuchMethodException localNoSuchMethodException1) {
            localNoSuchMethodException1.printStackTrace();
            return;
        } catch (InvocationTargetException localInvocationTargetException) {
            localInvocationTargetException.printStackTrace();
            return;
        } catch (IllegalAccessException localIllegalAccessException) {
            localIllegalAccessException.printStackTrace();
        }
    }


}

