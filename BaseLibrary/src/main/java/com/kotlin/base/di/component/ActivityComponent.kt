package com.kotlin.base.di.component

import android.app.Activity
import android.content.Context
import com.kotlin.base.di.ActivityScope
import com.kotlin.base.di.module.ActivityModule
import com.kotlin.base.di.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by jiangyuanyuan on 23/3/18.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity():Activity
    fun context(): Context
    fun providerLifecycleProvider(): LifecycleProvider<*>
}