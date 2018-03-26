package com.kotlin.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.kotlin.base.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*


/**
 * Created by jiangyuanyuan on 26/3/18.
 */
class HanderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    //是否显示返回图标
    private var isShowBack = true
    //中间文字内容
    private var titleText:String? =null
    //右边文字
    private var rightText:String? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true)
        titleText =typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)
        typedArray.recycle()
        initview()
    }

    private fun initview() {
        View.inflate(context,R.layout.layout_header_bar,this)

        mLeftIv.visibility = if (isShowBack) View.VISIBLE else View.GONE
        mTitleTv.text?.let {
            mTitleTv.text =it
        }
        mRightTv.text?.let {
            mRightTv.text =it
        }
    }
}