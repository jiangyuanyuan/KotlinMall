package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Goods

interface GoodsDetailView : BaseView{
    fun onGoodsDetailResult(result: Goods)
}