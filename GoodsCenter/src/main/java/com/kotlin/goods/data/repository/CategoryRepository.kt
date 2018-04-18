package com.kotlin.goods.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.goods.data.api.CategoryApi
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
class CategoryRepository @Inject constructor() {
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}