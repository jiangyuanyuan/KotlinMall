package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.presenter.view.CartView
import com.kotlin.goods.presenter.view.CategoryView
import com.kotlin.goods.service.impl.CartServiceImpl
import com.kotlin.goods.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 27/3/18.
 */
class CartPresenter @Inject constructor(): BasePresenter<CartView>(){
    @Inject
    lateinit var cartServiceImpl: CartServiceImpl

    fun getCartList() {
        if (!checkNetWork()){
            return
        }
        cartServiceImpl.getCartList()
                .execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                        mView.onGetCartListResult(t)
                    }

                },lifecycleProvider)
    }

    fun deleteCartList(list: List<Int>) {
        if (!checkNetWork()){
            return
        }
        cartServiceImpl.deleteCartList(list)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onDeleteCartListResult(t)
                    }
                },lifecycleProvider)
    }

    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long){
        if (!checkNetWork()){
            return
        }
        cartServiceImpl.submitCart(list,totalPrice).execute(object :BaseSubscriber<Int>(mView){
            override fun onNext(t: Int) {
                super.onNext(t)
                mView.onSubmitCartResult(t)
            }
        },lifecycleProvider)
    }

}