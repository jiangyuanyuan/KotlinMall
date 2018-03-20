package com.kotlin.base.ui.activity

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView

/**
 * Created by jiangyuanyuan on 18/3/18.
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }
}