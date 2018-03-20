package com.kotlin.usercenter.presenter

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.usercenter.presenter.view.RegisterView
import com.kotlin.usercenter.service.impl.UserServiceImpl
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class RegisterPresenter : BasePresenter<RegisterView>(){

    fun register(mobile:String,code:String,pwd:String){

        /*
            业务逻辑
         */
        var userService = UserServiceImpl()
        userService.register(mobile,code,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<Boolean>(){
                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }

                })

    }
}