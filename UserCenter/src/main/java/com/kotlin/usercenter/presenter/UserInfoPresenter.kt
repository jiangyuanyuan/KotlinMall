package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.usercenter.presenter.view.UserInfoView
import com.kotlin.usercenter.service.UploadService
import com.kotlin.usercenter.service.UserService
import com.kotlin.usercenter.service.impl.UploadServiceImpl
import com.kotlin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    @Inject
    lateinit var uploadService: UploadServiceImpl

    fun getUploadToken(){
        if(!checkNetWork())
            return
        mView.showLoading()
        uploadService.getUploadToken().execute(object : BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                super.onNext(t)
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }
}