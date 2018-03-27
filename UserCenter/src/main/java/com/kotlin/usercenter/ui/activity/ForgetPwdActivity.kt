package com.kotlin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.usercenter.R
import com.kotlin.usercenter.di.component.DaggerUserComponent
import com.kotlin.usercenter.di.module.UserModule
import com.kotlin.usercenter.presenter.ForgetPwdPresenter
import com.kotlin.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast


class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> mVerifyCodeBtn.requestSendVerifyNumber()
            R.id.mLeftIv -> finish()
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
            }

        }
    }

    override fun initComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<ResetPwdActivity>().putExtra("mobile", mMobileEt.text.toString()))
    }


    private fun initView() {
        mNextBtn.setOnClickListener(this)
        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mHeaderBar.getLeftView().setOnClickListener(this)

    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }
}
