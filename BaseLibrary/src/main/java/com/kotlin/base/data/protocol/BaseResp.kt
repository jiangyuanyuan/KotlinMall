package com.kotlin.base.data.protocol

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
data class BaseResp<out T> constructor(val status: Int, val message: String, val data: T) {
}