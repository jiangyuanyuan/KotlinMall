package com.kotlin.usercenter.service

import com.kotlin.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
interface UploadService {
    fun getUploadToken(): Observable<String>
}