package com.kotlin.usercenter.data.protocol

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
data class RegisterReq constructor(val mobile:String,val pwd:String,val verifyCode:String)