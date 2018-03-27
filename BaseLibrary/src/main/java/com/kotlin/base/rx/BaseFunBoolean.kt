package com.kotlin.base.rx

import android.util.Log
import com.kotlin.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by jiangyuanyuan on 26/3/18.
 */
class BaseFunBoolean : Func1<BaseResp<String>, Observable<Boolean>> {
    override fun call(t: BaseResp<String>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}