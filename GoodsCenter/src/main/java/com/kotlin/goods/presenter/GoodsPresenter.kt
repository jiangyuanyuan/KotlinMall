package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsView
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

class GoodsPresenter @Inject constructor() : BasePresenter<GoodsView>(){
    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int){
        if (!checkNetWork()){return}
        goodsService.getGoodsList(categoryId,pageNo).execute(object: BaseSubscriber<MutableList<Goods>?>(mView){
            override fun onNext(t: MutableList<Goods>?) {
                super.onNext(t)
                mView.onGoodsResult(t)
            }
        },lifecycleProvider)
    }
}