package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.presenter.view.CategoryView
import com.kotlin.goods.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
class CategoryPresenter @Inject constructor(): BasePresenter<CategoryView>(){
    @Inject
    lateinit var categoryServiceImpl: CategoryServiceImpl

    fun getCategory(parentId:Int) {
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        categoryServiceImpl.getCategory(parentId)
                .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        mView.onCategoryResult(t)
                    }

                },lifecycleProvider)
    }
}