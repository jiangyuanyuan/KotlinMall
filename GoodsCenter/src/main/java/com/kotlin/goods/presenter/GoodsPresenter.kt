package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

class GoodsPresenter @Inject constructor() : BasePresenter<GoodsListView>(){
    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int){
        if (!checkNetWork()){return}
        goodsService.getGoodsList(categoryId,pageNo).execute(object: BaseSubscriber<MutableList<Goods>?>(mView){
            override fun onNext(t: MutableList<Goods>?) {
                super.onNext(t)
                mView.onGoodsListResult(t)
            }
        },lifecycleProvider)
    }

    fun getGoodsListByKeyword(keyWord: String, pageNo: Int){
        if (!checkNetWork()){return}
        goodsService.getGoodsListByKeyword(keyWord,pageNo).execute(object: BaseSubscriber<MutableList<Goods>?>(mView){
            override fun onNext(t: MutableList<Goods>?) {
                super.onNext(t)
                mView.onGoodsListResult(t)
            }
        },lifecycleProvider)
    }
}