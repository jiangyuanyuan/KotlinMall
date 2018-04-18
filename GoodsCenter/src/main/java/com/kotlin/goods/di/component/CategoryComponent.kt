package com.kotlin.goods.di.component

import com.kotlin.base.di.PreComponentSocpe
import com.kotlin.base.di.component.ActivityComponent
import com.kotlin.goods.di.module.CategoryModule
import com.kotlin.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by jiangyuanyuan on 22/3/18.
 */
@PreComponentSocpe
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    @PreComponentSocpe
    fun inject(fragment : CategoryFragment)
}