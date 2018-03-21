package com.kotlin.usercenter.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.usercenter.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

}