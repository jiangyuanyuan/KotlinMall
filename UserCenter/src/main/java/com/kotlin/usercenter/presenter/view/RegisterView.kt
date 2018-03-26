package com.kotlin.usercenter.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
interface RegisterView : BaseView {
    fun onRegisterResult(result: String)
}