package com.kotlin.base.presenter

import com.kotlin.base.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 18/3/18.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}