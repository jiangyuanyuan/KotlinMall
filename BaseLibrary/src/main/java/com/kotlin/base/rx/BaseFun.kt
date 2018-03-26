package com.kotlin.base.rx

import com.kotlin.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by jiangyuanyuan on 26/3/18.
 */
class BaseFun<T> : Func1<BaseResp<T>, Observable<T>>{
    override fun call(t: BaseResp<T>): Observable<T>? {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.msg))
        }
        return Observable.just(t.data)
    }
}