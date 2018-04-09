package com.kotlin.usercenter.di.component

import com.kotlin.base.di.PreComponentSocpe
import com.kotlin.base.di.component.ActivityComponent
import com.kotlin.usercenter.di.module.UploadModule
import com.kotlin.usercenter.di.module.UserModule
import com.kotlin.usercenter.ui.activity.*
import dagger.Component

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@PreComponentSocpe
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class,UploadModule::class))
interface UserComponent {
    @PreComponentSocpe
    fun inject(activity : RegisterActivity)
    fun inject(activity : LoginActivity)
    fun inject(activity : ForgetPwdActivity)
    fun inject(activity : ResetPwdActivity)
    fun inject(activity : UserInfoActivity)
}