package com.kotlin.base.rx

import android.util.Log
import com.kotlin.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
open class BaseSubscriber<T> (val baseView: BaseView): Subscriber<T>() {
    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if(e is BaseException){
            baseView.onError(e.msg)
        }
        Log.d("onError   ","e.ms")
    }

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        baseView.hideLoading()
    }
}