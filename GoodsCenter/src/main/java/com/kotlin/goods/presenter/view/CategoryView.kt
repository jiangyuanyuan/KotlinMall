package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Category

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
interface CategoryView :BaseView{
    fun onCategoryResult(result: MutableList<Category>?)
}