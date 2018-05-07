package com.kotlin.goods.presenter

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsDetailView
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.service.CartService
import com.kotlin.goods.service.GoodsService
import rx.Observable
import javax.inject.Inject

class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>(){
    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService


    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWork()){return}
        goodsService.getGoodsDetail(goodsId).execute(object: BaseSubscriber<Goods>(mView){
            override fun onNext(t: Goods) {
                super.onNext(t)
                mView.onGoodsDetailResult(t)
            }
        },lifecycleProvider)
    }

    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String){
        if (!checkNetWork()){return}
        cartService.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,
                goodsCount,goodsSku).execute(object :BaseSubscriber<Int>(mView){
             override fun onNext(t: Int) {
                super.onNext(t)
                 mView.onAddCartResult(t)
            }
        },lifecycleProvider)
    }
}