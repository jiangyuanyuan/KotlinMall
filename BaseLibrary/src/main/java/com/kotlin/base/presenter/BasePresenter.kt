package com.kotlin.base.presenter

import com.kotlin.base.presenter.view.BaseView

/**
 * Created by jiangyuanyuan on 18/3/18.
 */
open class BasePresenter <T : BaseView>{
    lateinit var mView : T
}