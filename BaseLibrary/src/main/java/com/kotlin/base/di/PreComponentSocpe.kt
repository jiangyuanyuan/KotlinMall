package com.kotlin.base.di


import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope


/**
 * Created by jiangyuanyuan on 23/3/18.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PreComponentSocpe