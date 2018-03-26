package com.kotlin.base.ext

import android.view.View
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.rx.BaseFunBoolean
import com.kotlin.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,activity: LifecycleProvider<*>) {
    observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

fun View.onClick(method:() ->Unit){
    this.setOnClickListener{method}
}
