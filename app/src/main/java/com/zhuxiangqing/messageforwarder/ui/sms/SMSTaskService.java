package com.zhuxiangqing.messageforwarder.ui.sms;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.arch.persistence.room.Insert;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.telecom.PhoneAccount;
import android.telephony.SmsMessage;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.zhuxiangqing.messageforwarder.db.MessageDao;
import com.zhuxiangqing.messageforwarder.db.MessageEntity;
import com.zhuxiangqing.messageforwarder.entity.BaseEntity;
import com.zhuxiangqing.messageforwarder.repository.SMSRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;

import dagger.android.DaggerIntentService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class SMSTaskService extends DaggerIntentService {
    @Inject
    SMSRepository repository;
    @Inject
    MessageDao dao;

    private final CompositeDisposable disposable = new CompositeDisposable();

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

            final String phoneNumber = "";
            int slotId = 0;
            int i = ((Integer) localMethod1.invoke(arrayOfSmsMessage[0], new Object[0])).intValue();
            if (Build.VERSION.SDK_INT >= 22) {
                SubscriptionManager subscriptionManager = SubscriptionManager.from(this);
                slotId = subscriptionManager.getActiveSubscriptionInfo(i).getSimSlotIndex();

            } else {
                Class[] arrayOfClass2 = new Class[1];
                arrayOfClass2[0] = Integer.TYPE;
                Method getSlotIndex = TelephonyManager.class.getMethod("getSlotIndex", arrayOfClass2);
                Object[] arrayOfObject2 = new Object[1];
                slotId = (int) getSlotIndex.invoke(null, arrayOfObject2);
            }
            //
            Class[] arrayOfClass2 = new Class[1];
            arrayOfClass2[0] = Integer.TYPE;
            Method localMethod3 = TelephonyManager.class.getMethod("getSubscriberId", arrayOfClass2);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = i;
            final String imsi = String.valueOf(localMethod3.invoke(localTelephonyManager, arrayOfObject2));

            final String str2 = arrayOfSmsMessage[0].getMessageBody();
            final long date = arrayOfSmsMessage[0].getTimestampMillis();

            //通过Rxjava处理网络请求
            disposable.add(repository.sendSMSToRemote(slotId, phoneNumber, imsi, str2, "")
                    .subscribe(new Consumer<BaseEntity>() {
                        @Override
                        public void accept(BaseEntity baseEntity) throws Exception {
                            dao.insert(new MessageEntity(str2, phoneNumber, imsi,date));
                        }
                    }));
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

    //


    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}

