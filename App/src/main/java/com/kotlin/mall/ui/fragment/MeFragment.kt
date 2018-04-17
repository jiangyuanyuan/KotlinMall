package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.loadUrl
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.mall.R
import com.kotlin.mall.ui.activity.SettingActivity
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.usercenter.ui.activity.LoginActivity
import com.kotlin.usercenter.ui.activity.UserInfoActivity
import isLogined
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

class MeFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_me,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mUserNameTv.setOnClickListener(this)
        mUserIconIv.setOnClickListener(this)
        mSettingTv.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mUserIconIv,R.id.mUserNameTv ->
                if (isLogined()){
                    startActivity<UserInfoActivity>()
                }else{
                    startActivity<LoginActivity>()
                }
            R.id.mSettingTv -> startActivity<SettingActivity>()
        }
    }
    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (isLogined()){
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()){
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
            mUserIconIv.isClickable = false
            mUserNameTv.isClickable = false
        }else{
            mUserIconIv.isClickable = true
            mUserNameTv.isClickable = true
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }

    }

}