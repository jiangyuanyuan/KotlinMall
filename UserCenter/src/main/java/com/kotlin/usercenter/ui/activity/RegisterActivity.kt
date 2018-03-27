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
import com.kotlin.usercenter.presenter.RegisterPresenter
import com.kotlin.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView ,View.OnClickListener{
    override fun onClick(view: View) {
        when(view.id){
            R.id.mRegisterBtn ->  { mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString()) }
            R.id.mVerifyCodeBtn -> mVerifyCodeBtn.requestSendVerifyNumber()
            else -> Log.d("点击","点击了")
        }
    }

    override fun initComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()

    }

    private fun initView() {
        mVerifyCodeBtn.setOnClickListener(this)
        mRegisterBtn.setOnClickListener(this)
        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})

    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not()&&
                mPwdEt.text.isNullOrEmpty().not()&&
                mPwdConfirmEt.text.isNullOrEmpty().not()&&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }
}
