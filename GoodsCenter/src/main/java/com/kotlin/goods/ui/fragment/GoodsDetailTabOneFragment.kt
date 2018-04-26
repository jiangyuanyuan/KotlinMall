package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ext.startLoading
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.di.component.DaggerCategoryComponent
import com.kotlin.goods.di.component.DaggerGoodsComponent
import com.kotlin.goods.di.module.CategoryModule
import com.kotlin.goods.di.module.GoodsModule
import com.kotlin.goods.event.GoodsDetailImageEvent
import com.kotlin.goods.presenter.CategoryPresenter
import com.kotlin.goods.presenter.GoodsDetailPresenter
import com.kotlin.goods.presenter.view.CategoryView
import com.kotlin.goods.presenter.view.GoodsDetailView
import com.kotlin.goods.ui.activity.GoodsActivity
import com.kotlin.goods.ui.adapter.SecondCategoryAdapter
import com.kotlin.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.startActivity

class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(),GoodsDetailView{
    override fun initComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_goods_detail_tab_one,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID,-1))
    }

    override fun onGoodsDetailResult(result: Goods) {
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()
        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne,result.goodsDetailTwo))
    }

}