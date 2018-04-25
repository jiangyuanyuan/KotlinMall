package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.data.repository.GoodsRepository
import com.kotlin.goods.service.GoodsService
import rx.Observable
import javax.inject.Inject

class GoodsServiceImpl @Inject constructor():GoodsService {
    @Inject
    lateinit var goodsRepository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return goodsRepository.getGoodsList(categoryId,pageNo).convert()
    }

    override fun getGoodsListByKeyword(keyWord: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return goodsRepository.getGoodsListByKeyword(keyWord,pageNo).convert()
    }
}