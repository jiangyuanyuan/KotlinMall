package com.kotlin.goods.di.module

import com.kotlin.goods.service.GoodsService
import com.kotlin.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

@Module
class GoodsModule {
    @Provides
    fun providersGoodsService(service: GoodsServiceImpl):GoodsService{
        return service
    }
}