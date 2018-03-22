package com.kotlin.base.di

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by jiangyuanyuan on 23/3/18.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope