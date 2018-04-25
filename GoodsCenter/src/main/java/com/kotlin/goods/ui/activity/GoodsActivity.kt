package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.bingoogolapple.refreshlayout.adapters.BGARefreshLayoutAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.BaseApplication.Companion.context
import com.kotlin.base.ext.startLoading
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.di.component.DaggerGoodsComponent
import com.kotlin.goods.di.module.GoodsModule
import com.kotlin.goods.presenter.GoodsPresenter
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*

class GoodsActivity : BaseMvpActivity<GoodsPresenter>(),GoodsListView,BGARefreshLayout.BGARefreshLayoutDelegate{


    private lateinit var goodsAdapter: GoodsAdapter
    private var mCurrentPage : Int = 1
    private var mMaxPage : Int = 1

    override fun initComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        val manager = GridLayoutManager(context,2)
        mGoodsRv.layoutManager = manager
        goodsAdapter = GoodsAdapter(context)
        mGoodsRv.adapter = goodsAdapter

    }
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra("categoryId",1),1)

    }

    override fun onGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result !=null &&result.size>0){
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1){
                goodsAdapter.setData(result)
            }else {
                goodsAdapter.dataList.addAll(result)
                goodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        mCurrentPage ++
        return if (mCurrentPage<mMaxPage){
            loadData()
            true
        }else{
            false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this,true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }
}
