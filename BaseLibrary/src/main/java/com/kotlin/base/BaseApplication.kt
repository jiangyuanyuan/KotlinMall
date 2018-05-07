package com.kotlin.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.di.component.AppComponent
import com.kotlin.base.di.component.DaggerAppComponent
import com.kotlin.base.di.module.AppModule

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
open class BaseApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInject()
        context = this
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

    private fun initAppInject() {
        appComponent= DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
    companion object {
        lateinit var context:Context
    }
}