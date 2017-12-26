package com.zhuxiangqing.messageforwarder.repository;

import android.text.TextUtils;

import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 * 负责处理
 */

public class CardRepository {

    private static final String KEY_CARD_ONE = "CARD_ONE";
    private static final String KEY_CARD_TWO = "CARD_TWO";

    //todo 这个对象太具体了 考虑接口化
    private SharedPrefrenceHelper helper;

    @Inject
    public CardRepository(SharedPrefrenceHelper helper) {
        this.helper = helper;
    }


    public void clearNum() {
        helper.remove(KEY_CARD_ONE, KEY_CARD_TWO);
    }


    public boolean hadNumberSaved() {
        return !TextUtils.isEmpty(helper.getString(KEY_CARD_ONE)) && !TextUtils.isEmpty(helper.getString(KEY_CARD_TWO));
    }

    public void saveCardOne(String card1) {
        helper.putString(KEY_CARD_ONE, card1);
    }


    public void saveCardTwo(String card2) {
        helper.putString(KEY_CARD_TWO, card2);
    }

    public String getCardOne() {
        return helper.getString(KEY_CARD_ONE);
    }

    public String getCardTwo() {
        return helper.getString(KEY_CARD_TWO);
    }


}
