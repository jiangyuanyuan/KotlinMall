package com.kotlin.usercenter.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlin.base.di.component.DaggerActivityComponent
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.usercenter.R
import com.kotlin.usercenter.di.component.DaggerUserComponent
import com.kotlin.usercenter.di.module.UserModule
import com.kotlin.usercenter.presenter.LoginPresenter
import com.kotlin.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity :  BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    override fun onClick(view: View) {
        when(view.id){
            R.id.mLoginBtn-> mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
        }
    }

    override fun onLoginResult(result: String) {
        toast(result)
    }

    override fun initComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt,{isBtnEnable()})
        mLoginBtn.enable(mPwdEt,{isBtnEnable()})
        mLoginBtn.setOnClickListener(this)
    }

    fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not()&&
                mPwdEt.text.isNullOrEmpty().not()
    }
}
