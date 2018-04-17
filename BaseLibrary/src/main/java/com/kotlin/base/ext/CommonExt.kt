package com.kotlin.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,activity: LifecycleProvider<*>) {
    observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

fun View.onClick(method:() ->Unit){
    this.setOnClickListener{method}
}

fun Button.enable(editText: EditText, method :()-> Boolean){
    var btn =this
    editText.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            super.onTextChanged(s, start, before, count)
            btn.isEnabled = method()
        }
    })
}

fun ImageView.loadUrl(url:String){
    GlideUtils.loadUrlImage(context,url,this)
}

fun BottomNavigationBar.slectChange(method: (i:Int) -> Unit){
    this.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
        override fun onTabReselected(position: Int) {
        }

        override fun onTabUnselected(position: Int) {
        }

        override fun onTabSelected(position: Int) {
            method(position)
        }

    })
}