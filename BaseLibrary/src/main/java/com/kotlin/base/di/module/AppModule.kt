package com.kotlin.base.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@Module
@Singleton
class AppModule (private val context: Context){
    @Singleton
    @Provides
    fun providerContext():Context{
        return context
    }
}