package com.kotlin.usercenter.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.usercenter.data.api.UploadApi
import com.kotlin.usercenter.data.api.UserApi
import com.kotlin.usercenter.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
class UploadRepository @Inject constructor() {

    fun getUploadToken(): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }
}