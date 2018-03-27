package com.kotlin.usercenter.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.usercenter.R
import com.kotlin.usercenter.di.component.DaggerUserComponent
import com.kotlin.usercenter.di.module.UserModule
import com.kotlin.usercenter.presenter.ResetPwdPresenter
import com.kotlin.usercenter.presenter.view.ResetPwdView

import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast


class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()

    }

    override fun initComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }


    private fun initView() {
        mConfirmBtn.enable(mPwdEt, { isBtnEnable() })
        mConfirmBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mConfirmBtn.setOnClickListener {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()) {
                toast("密码不一致")
                return@setOnClickListener
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }
        mHeaderBar.getLeftView().setOnClickListener { finish() }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
