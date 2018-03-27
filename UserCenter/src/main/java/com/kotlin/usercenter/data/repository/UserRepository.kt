package com.kotlin.usercenter.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.usercenter.data.api.UserApi
import com.kotlin.usercenter.data.protocol.LoginReq
import com.kotlin.usercenter.data.protocol.RegisterReq
import com.kotlin.usercenter.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
class UserRepository @Inject constructor() {
    fun register(mobile: String, pwd: String, code: String): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,pwd,code))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile,pwd,pushId))
    }
}