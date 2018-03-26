package com.kotlin.usercenter.di.component

import com.kotlin.base.di.PreComponentSocpe
import com.kotlin.base.di.component.ActivityComponent
import com.kotlin.usercenter.di.module.UserModule
import com.kotlin.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@PreComponentSocpe
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {
    @PreComponentSocpe
    fun inject(activity : RegisterActivity)
}