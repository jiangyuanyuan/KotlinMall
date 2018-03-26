package com.kotlin.usercenter.presenter

import android.text.Editable
import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.usercenter.presenter.view.RegisterView
import com.kotlin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    fun register(mobile: String, pwd: String, code: String) {

        userService.register(mobile, pwd, code)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        if (t)
                        mView.onRegisterResult("注册成功")
                    }
                },lifecycleProvider)
    }
}