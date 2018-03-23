package com.kotlin.base.di.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by jiangyuanyuan on 23/3/18.
 */
@Module
class LifecycleProviderModule (private val provider: LifecycleProvider<*>){
    @Provides
    fun providerLifecycleProvider():LifecycleProvider<*>{
        return provider
    }
}