package com.kotlin.goods.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.goods.R
import com.kotlin.goods.ui.fragment.CartFragment

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}
