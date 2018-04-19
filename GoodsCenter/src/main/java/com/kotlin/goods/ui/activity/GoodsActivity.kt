package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.kotlin.base.BaseApplication.Companion.context
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.di.module.GoodsModule
import com.kotlin.goods.presenter.GoodsPresenter
import com.kotlin.goods.presenter.view.GoodsView
import com.kotlin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*

class GoodsActivity : BaseMvpActivity<GoodsPresenter>(),GoodsView {

    private lateinit var goodsAdapter: GoodsAdapter

    override fun initComponent() {
//        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        loadData()
    }

    private fun initView() {
        val manager = GridLayoutManager(context,2)
        mGoodsRv.layoutManager = manager
        goodsAdapter = GoodsAdapter(context)
        mGoodsRv.adapter = goodsAdapter

    }
    private fun loadData() {
        mPresenter.getGoodsList(intent.getIntExtra("categoryId",1),1)

    }

    override fun onGoodsResult(result: MutableList<Goods>?) {
        result?.let {
            goodsAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }
    }
}
