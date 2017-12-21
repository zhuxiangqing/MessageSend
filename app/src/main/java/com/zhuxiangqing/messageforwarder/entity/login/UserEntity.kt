package com.zhuxiangqing.messageforwarder.entity.login

import android.text.TextUtils

/**
 * Created by Z on 2016/5/10.
 */
class UserEntity {

    /**
     * CONTACT_PHONE : 18640128925
     * E_MAIL : 11111@qq.com
     * MOBILE_PHONE : 18640128925
     * PSPT_NO : 210103198405142112
     * QQ_NUMBER : 12345
     * STORE_ADDRESS : 辽宁省沈阳市皇姑器岐山东路4号
     * STORE_KIND : supermarketShop
     */
    var mobilePhone: String? = null
    var cityCode: String? = null
    var createDate: Long = 0
    var createFrom: String? = null
    var createPerson: String? = null
    var departCode: String? = null
    var departName: String? = null
    var developer: String? = null
    var eparchyCode: String? = null
    var id: Int = 0
    var manager: String? = null
    var provinceCode: String? = null
    var remark: String? = null
    var status: Int = 0
    var storeName: String? = null
    var updateDate: Long = 0
    var updatePerson: String? = null
    var userCode: String? = null
    var userLoginName: String? = null
    var userRealName: String? = null
    var userType: String? = null

    var attrMap: AttrMapEntity
        get() = attrMap
        set(attrMap) {
            this.attrMap = attrMap
        }

    fun hasRealName(): Boolean {
        return if (TextUtils.isEmpty(userRealName)) false else true
    }

    fun hasEmail(): Boolean {
        return if (null == attrMap) false else if (TextUtils.isEmpty(attrMap.e_MAIL)) false else true
    }


}
