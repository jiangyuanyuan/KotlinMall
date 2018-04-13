package com.kotlin.base.ui.fragment

import android.os.Bundle
import android.os.PersistableBundle
import com.kotlin.base.BaseApplication
import com.kotlin.base.di.component.ActivityComponent
import com.kotlin.base.di.component.DaggerActivityComponent
import com.kotlin.base.di.module.ActivityModule
import com.kotlin.base.di.module.LifecycleProviderModule
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.widgets.ProgressLoading
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Created by jiangyuanyuan on 18/3/18.
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    lateinit var progressLoading: ProgressLoading

    override fun showLoading() {
        progressLoading.showLoading()
    }

    override fun hideLoading() {
        progressLoading.hideLoading()
    }

    override fun onError(text:String) {
        toast(text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()
        initComponent()
        progressLoading = ProgressLoading.create(activity)
    }

    protected abstract fun initComponent()

    private fun initActivityInject() {
        activityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}