package com.kotlin.base.data.net

import com.kotlin.base.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jiangyuanyuan on 21/3/18.
 */
class RetrofitFactory private constructor() {
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    //请求拦截
    private val interceptor: Interceptor
    private val retrofit: Retrofit

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Content_Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .build()
    }

    //okhttp 客户端
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    //日志拦截
    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    //暴露方法
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}