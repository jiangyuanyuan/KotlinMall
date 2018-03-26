package com.kotlin.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.kotlin.base.R
import org.jetbrains.anko.find

/**
 * Created by jiangyuanyuan on 26/3/18.
 */
class ProgressLoading private constructor(context: Context,theme:Int) :Dialog(context,theme) {
    companion object {
        lateinit var mDialog:ProgressLoading
        private var animDrawable:AnimationDrawable? =null

        //静态方法
        fun create(context: Context):ProgressLoading{
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.setCancelable(true)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f
            mDialog.window.attributes =lp

            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable =loadingView.background as AnimationDrawable
            return mDialog
        }
    }
    fun showLoading(){
        show()
        animDrawable?.start()
    }

    fun hideLoading(){
        dismiss()
        animDrawable?.stop()
    }
}