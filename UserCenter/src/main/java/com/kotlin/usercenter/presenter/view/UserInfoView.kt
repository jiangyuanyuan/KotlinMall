package com.kotlin.usercenter.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.usercenter.data.protocol.UserInfo

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
interface UserInfoView :BaseView {
    fun onGetUploadTokenResult(result:String)
    fun onEditUserResult(result:UserInfo)
}