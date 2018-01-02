package com.zhuxiangqing.messageforwarder.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by zhuxi on 2017/12/29.
 *
 */
@Database(entities = {MessageEntity.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    //需要是public的 用来调用获取Dao
    public abstract MessageDao messageDao();
}
