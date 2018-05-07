package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.protocol.Category

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
interface CartView :BaseView{
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    fun onDeleteCartListResult(result: Boolean)
    fun onSubmitCartResult(result: Int)
}