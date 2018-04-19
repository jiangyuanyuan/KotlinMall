package com.kotlin.goods.di.module

import com.kotlin.goods.service.GoodsService
import dagger.Module
import dagger.Provides

@Module
class GoodsModule {
    @Provides
    fun providersGoodsService(service:GoodsService):GoodsService{
        return service
    }
}