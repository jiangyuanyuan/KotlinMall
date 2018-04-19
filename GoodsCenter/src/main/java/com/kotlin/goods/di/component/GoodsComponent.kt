package com.kotlin.goods.di.component

import com.kotlin.base.di.PreComponentSocpe
import com.kotlin.base.di.component.ActivityComponent
import com.kotlin.goods.di.module.GoodsModule
import com.kotlin.goods.ui.activity.GoodsActivity
import dagger.Component
@PreComponentSocpe
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
}