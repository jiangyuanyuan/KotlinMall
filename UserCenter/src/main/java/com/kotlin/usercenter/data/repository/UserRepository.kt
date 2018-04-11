package com.kotlin.usercenter.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.protocol.EditUserReq
import com.kotlin.usercenter.data.api.UserApi
import com.kotlin.usercenter.data.protocol.*
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

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetPwdReq(mobile,verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).resetPwd(ResetPwdReq(mobile,pwd))
    }

    fun editUser(mUserIcon: String, mUserName: String, mUserGender: String, mUserSign: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).editUser(EditUserReq(mUserIcon,mUserName,mUserGender,mUserSign))
    }
}