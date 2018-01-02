package com.zhuxiangqing.messageforwarder.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by zhuxi on 2017/12/29.
 *
 */
@Dao
public interface MessageDao {
    @Insert
    void insert(MessageEntity entity);

    @Query("SELECT * FROM message LIMIT 10 OFFSET 10 * :page")
    List<MessageEntity> getMessageListByPage(int page);

    @Query("SELECT * FROM message")
    List<MessageEntity> getAllMessageList();
}
