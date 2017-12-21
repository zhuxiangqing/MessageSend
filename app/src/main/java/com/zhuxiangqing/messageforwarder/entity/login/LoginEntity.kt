package com.zhuxiangqing.messageforwarder.entity.login

/**
 * Created by zhuxi on 2017/12/18.
 *
 */

data class LoginEntity(
        var token: String = "",
        var autologin: Boolean = false,
        var user: UserEntity?)
