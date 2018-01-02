package com.zhuxiangqing.messageforwarder.ui.log;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.zhuxiangqing.messageforwarder.db.MessageEntity;

import java.util.List;

/**
 * Created by zhuxi on 2018/1/2.
 *
 */

public class LogListBindings {
    @BindingAdapter("app:messageList")
    public static void setLogList(RecyclerView recyclerView, List<MessageEntity> list){
        LogRVAdapter adapter = (LogRVAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.refresh(list);
        }
    }
}
