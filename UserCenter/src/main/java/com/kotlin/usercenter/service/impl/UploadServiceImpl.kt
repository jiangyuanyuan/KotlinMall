package com.kotlin.usercenter.service.impl

import com.kotlin.base.rx.BaseFun
import com.kotlin.usercenter.data.repository.UploadRepository
import com.kotlin.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class UploadServiceImpl @Inject constructor() : UploadService {

    @Inject
    lateinit var uploadRepository : UploadRepository

    override fun getUploadToken(): Observable<String> {
        return uploadRepository.getUploadToken().flatMap(BaseFun<String>())
    }
}


