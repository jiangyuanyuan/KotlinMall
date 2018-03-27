package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.usercenter.presenter.view.ForgetPwdView
import com.kotlin.usercenter.presenter.view.ResetPwdView
import com.kotlin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile,pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onResetPwdResult("密码重置成功")
                    }
                },lifecycleProvider)
    }
}