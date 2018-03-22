package com.kotlin.usercenter.di.component

import com.kotlin.usercenter.di.module.UserModule
import com.kotlin.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity : RegisterActivity)
}