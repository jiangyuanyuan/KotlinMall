package com.kotlin.usercenter.service.impl

import android.util.Log
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.rx.BaseException
import com.kotlin.base.rx.BaseFun
import com.kotlin.base.rx.BaseFunBoolean
import com.kotlin.usercenter.data.protocol.UserInfo
import com.kotlin.usercenter.data.repository.UserRepository
import com.kotlin.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class UserServiceImpl @Inject constructor() : UserService {



    @Inject
    lateinit var userRepository: UserRepository

    override fun register(mobile: String, pwd: String, code: String): Observable<Boolean> {
        return userRepository.register(mobile, pwd, code).flatMap(BaseFunBoolean())

    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return userRepository.login(mobile,pwd,pushId).flatMap(BaseFun<UserInfo>())
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return userRepository.forgetPwd(mobile,verifyCode).flatMap(BaseFunBoolean())
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return userRepository.resetPwd(mobile,pwd).flatMap(BaseFunBoolean())
    }
    override fun editUser(mUserIcon: String, mUserName: String, mUserGender: String, mUserSign: String): Observable<UserInfo> {
        return userRepository.editUser(mUserIcon,mUserName,mUserGender,mUserSign).flatMap(BaseFun<UserInfo>())
    }
}


