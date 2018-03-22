package com.kotlin.base.di.component

import android.app.Activity
import com.kotlin.base.di.ActivityScope
import com.kotlin.base.di.module.ActivityModule
import dagger.Component

/**
 * Created by jiangyuanyuan on 23/3/18.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity():Activity
}