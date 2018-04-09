package com.kotlin.usercenter.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
interface UserInfoView :BaseView {
    fun onGetUploadTokenResult(result:String)
}