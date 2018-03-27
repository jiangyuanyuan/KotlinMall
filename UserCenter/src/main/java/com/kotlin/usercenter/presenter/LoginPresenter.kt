package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.usercenter.data.protocol.UserInfo
import com.kotlin.usercenter.presenter.view.LoginView
import com.kotlin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
class LoginPresenter @Inject constructor(): BasePresenter<LoginView>(){
    @Inject
    lateinit var userService: UserServiceImpl

    fun login(mobile:String,pwd:String,pushId:String) {
        userService.login(mobile, pwd, pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                },lifecycleProvider)
    }
}