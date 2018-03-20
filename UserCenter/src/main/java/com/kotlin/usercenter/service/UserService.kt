package com.kotlin.usercenter.service

import rx.Observable

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
interface UserService {
    fun register(mobile:String,code:String,pwd:String) : Observable<Boolean>
}