package com.kotlin.base.presenter

import android.content.Context
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 18/3/18.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetWork():Boolean{
        if (NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}