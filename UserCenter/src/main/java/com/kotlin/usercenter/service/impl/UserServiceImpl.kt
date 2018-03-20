package com.kotlin.usercenter.service.impl

import com.kotlin.usercenter.service.UserService
import rx.Observable

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, code: String, pwd: String): Observable<Boolean> {

        //这里要去请求网络
        return Observable.just(false)
    }
}