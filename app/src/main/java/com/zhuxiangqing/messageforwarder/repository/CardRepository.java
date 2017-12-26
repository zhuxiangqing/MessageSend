package com.zhuxiangqing.messageforwarder.repository;

import com.zhuxiangqing.messageforwarder.utils.SharedPrefrenceHelper;

import javax.inject.Inject;

/**
 * Created by zhuxi on 2017/12/24.
 */

public class CardRepository {

    private SharedPrefrenceHelper helper;

    @Inject
    public CardRepository(SharedPrefrenceHelper helper) {
        this.helper = helper;
    }


    public void clearNum() {
        helper.remove("card_one");
        helper.remove("card_two");
    }

    public void saveCardOne(String card1) {
        helper.putValue("card_one", card1);
    }


    public void saveCardTwo(String card2) {
        helper.putValue("card_two", card2);
    }

    public String getCardOne() {
        return helper.getValue("card_one");
    }

    public String getCardTwo() {
        return helper.getValue("card_two");
    }


}
