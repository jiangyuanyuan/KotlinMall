package com.kotlin.base.di.module

import android.app.Activity
import com.kotlin.base.di.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jiangyuanyuan on 23/3/18.
 */
@Module
class ActivityModule (private val activity: Activity){
    @ActivityScope
    @Provides
    fun prviderActivity():Activity{
        return activity
    }
}