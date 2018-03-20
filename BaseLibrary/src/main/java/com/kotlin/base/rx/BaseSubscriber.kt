package com.kotlin.base.rx

import rx.Subscriber

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onError(e: Throwable?) {

    }

    override fun onNext(t: T) {

    }

    override fun onCompleted() {
    }
}