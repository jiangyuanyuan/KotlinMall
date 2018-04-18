package com.kotlin.goods.di.module

import com.kotlin.goods.service.CategoryService
import com.kotlin.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@Module
class CategoryModule {
    @Provides
    fun providersUserService(service: CategoryServiceImpl):CategoryService{
        return service
    }
}