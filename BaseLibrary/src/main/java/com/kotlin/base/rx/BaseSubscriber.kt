package com.kotlin.base.rx

import com.kotlin.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
open class BaseSubscriber<T> (val baseView: BaseView): Subscriber<T>() {
    override fun onError(e: Throwable?) {
        baseView.hideLoading()
    }

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        baseView.hideLoading()
    }
}