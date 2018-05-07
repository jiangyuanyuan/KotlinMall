package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ext.startLoading
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.di.component.DaggerCartComponent
import com.kotlin.goods.di.module.CartModule
import com.kotlin.goods.event.CartAllCheckedEvent
import com.kotlin.goods.event.UpdataCartSizeEvent
import com.kotlin.goods.event.UpdateTotalPriceEvent
import com.kotlin.goods.presenter.CartPresenter
import com.kotlin.goods.presenter.view.CartView
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

class CartFragment :BaseMvpFragment<CartPresenter>(), CartView {

    private lateinit var mAdapter:CartGoodsAdapter
    private var mTotalPrice:Long = 0

    override fun initComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent).cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context)
        mCartGoodsRv.adapter = mAdapter
        mHeaderBar.getRightView().setOnClickListener { refreshEditStatus() }
        mAllCheckedCb.setOnClickListener {
             for (item in mAdapter.dataList){
                 item.isSelected = mAllCheckedCb.isChecked
             }
            mAdapter.notifyDataSetChanged()
            upDataTotalPrice()
        }
        mDeleteBtn.setOnClickListener {
            val cartIdList:MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList){it.id}
            if (cartIdList.size==0){
                toast("请选择需要删除的数据")
            }else{
                mPresenter.deleteCartList(cartIdList)
            }
        }
        mSettleAccountsBtn.setOnClickListener {
            val cartGoodsList:MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList){it}
            if (cartGoodsList.size==0){
                toast("请选择需要购买的商品")
            }else{
                mPresenter.submitCart(cartGoodsList,mTotalPrice)
            }
        }
    }
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result!=null && result.size>0){
            mAdapter.setData(result)
            mAllCheckedCb.isChecked = false
            mHeaderBar.getRightView().setVisible(true)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,result?.size?:0)
        Bus.send(UpdataCartSizeEvent())
        upDataTotalPrice()
    }

    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()
    }

    override fun onSubmitCartResult(result: Int) {
        toast("订单提交成功${result}")
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>().subscribe{
           mAllCheckedCb.isChecked = it.isAllChecked
        }.registerInBus(this)
        Bus.observe<UpdateTotalPriceEvent>().subscribe{
            upDataTotalPrice()
        }.registerInBus(this)
    }

    private fun upDataTotalPrice(){
        mTotalPrice = mAdapter.dataList.filter { it.isSelected }
                .map { it.goodsCount*it.goodsPrice }
                .sum()
        mTotalPriceTv.text = "合计${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)
        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)
    }

    fun setBackVisible(isVisible:Boolean){
        mHeaderBar.getLeftView().setVisible(isVisible)
    }
}