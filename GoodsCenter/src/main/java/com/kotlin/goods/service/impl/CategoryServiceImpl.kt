package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.repository.CategoryRepository
import com.kotlin.goods.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 20/3/18.
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {



    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return categoryRepository.getCategory(parentId).convert()
    }

}


