package com.kotlin.usercenter.presenter

import android.text.Editable
import android.util.Log
import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.base.utils.NetWorkUtils
import com.kotlin.usercenter.presenter.view.ForgetPwdView
import com.kotlin.usercenter.presenter.view.RegisterView
import com.kotlin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class ForgetPwdPresenter @Inject constructor(): BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile,verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                        mView.onForgetPwdResult("验证成功")
                    }
                },lifecycleProvider)
    }
}