package com.kotlin.usercenter.di.module

import com.kotlin.usercenter.service.UserService
import com.kotlin.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@Module
class UserModule {
    @Provides
    fun providersUserService(service:UserServiceImpl):UserService{
        return service
    }
}