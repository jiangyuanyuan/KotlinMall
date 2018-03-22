package com.kotlin.base.di.component

import android.content.Context
import com.kotlin.base.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context():Context
}