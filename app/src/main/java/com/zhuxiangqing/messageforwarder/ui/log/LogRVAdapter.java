package com.zhuxiangqing.messageforwarder.ui.log;

import android.view.View;
import android.view.ViewGroup;

import com.zhuxiangqing.messageforwarder.BR;
import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseRVAdapter;
import com.zhuxiangqing.messageforwarder.db.MessageEntity;

/**
 * Created by zhuxi on 2018/1/2.
 *
 */

public class LogRVAdapter extends BaseRVAdapter<MessageEntity> {

    @Override
    protected int getLayoutId() {
        return R.layout.item_log;
    }

    @Override
    protected int getVariant() {
        return BR.item;
    }
}
