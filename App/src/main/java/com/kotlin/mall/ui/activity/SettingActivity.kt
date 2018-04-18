package com.kotlin.mall.ui.activity

import android.os.Bundle
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        mLogoutBtn.setOnClickListener{
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}
