package com.kotlin.goods.ui.activity

import afterLogin
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.UpdataCartSizeEvent
import com.kotlin.goods.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*
import q.rorbin.badgeview.QBadgeView

class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBage:QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
        initObserve()
        loadCartSize()
    }

    private fun loadCartSize() {
        setCartBadge()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager,this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
        mAddCartBtn.setOnClickListener {
            afterLogin{
                Bus.send(AddCartEvent())
            }
        }
        mCartBage = QBadgeView(this)
    }

    private fun initObserve() {
        Bus.observe<UpdataCartSizeEvent>().subscribe{
            setCartBadge()
        }.registerInBus(this)
    }

    private fun setCartBadge() {
        mCartBage.badgeGravity = Gravity.END or Gravity.TOP
        mCartBage.setGravityOffset(22f,-2f,true)
        mCartBage.setBadgeTextSize(6f,true)
        mCartBage.bindTarget(mEnterCartTv).badgeNumber =  AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }
}
