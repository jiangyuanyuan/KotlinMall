package com.kotlin.usercenter.service

import com.kotlin.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
interface UserService {
    fun register(mobile: String, pwd: String, code: String): Observable<Boolean>
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>
}