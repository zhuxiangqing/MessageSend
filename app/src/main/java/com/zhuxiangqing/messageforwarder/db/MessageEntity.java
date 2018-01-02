package com.zhuxiangqing.messageforwarder.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by zhuxi on 2017/12/29.
 *
 */

@Entity(tableName = "message")
public class MessageEntity {
    private String body;
    private String phoneNumber;
    private String imsi;
    @PrimaryKey
    private long date;

    public MessageEntity() {
    }

    public MessageEntity(String body, String phoneNumber, String imsi, long date) {
        this.body = body;
        this.phoneNumber = phoneNumber;
        this.imsi = imsi;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImsi() {
        return imsi;
    }

    public long getDate() {
        return date;
    }
}
