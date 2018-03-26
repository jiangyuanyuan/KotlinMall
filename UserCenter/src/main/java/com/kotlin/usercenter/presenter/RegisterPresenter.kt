package com.kotlin.usercenter.presenter

import android.text.Editable
import android.util.Log
import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.base.utils.NetWorkUtils
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
        if (!checkNetWork()){
            Log.d("网络连接","————————————失败:"+checkNetWork().toString())
            return
        }
        mView.showLoading()
        userService.register(mobile, pwd, code)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                        mView.onRegisterResult("注册成功")
                    }
                },lifecycleProvider)
    }
}