package com.kotlin.base.rx

import android.util.Log
import com.kotlin.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by jiangyuanyuan on 26/3/18.
 */
class BaseFunBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}