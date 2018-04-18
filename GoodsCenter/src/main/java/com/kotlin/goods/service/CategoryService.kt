package com.kotlin.goods.service

import com.kotlin.goods.data.protocol.Category
import rx.Observable

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
interface CategoryService {
    fun getCategory(parentId:Int): Observable<MutableList<Category>?>
}