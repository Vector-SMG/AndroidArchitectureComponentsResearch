package com.ivan.androidarchitecturecomponentssearch.http

import com.ivan.androidarchitecturecomponentssearch.data.api.ApiClient
import com.ivan.androidarchitecturecomponentssearch.data.api.Contants
import com.ivan.androidarchitecturecomponentssearch.data.api.GankApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: retrofit工厂类
*/
class RetrofitFactory private constructor() {

    companion object {
        val instance:RetrofitFactory by lazy {
            RetrofitFactory()
        }
    }

    private val interceptor:Interceptor
    private val retrofit:Retrofit


    init {
        interceptor= Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("charset", "utf-8")
                    .build()
            chain.proceed(request)
        }

        retrofit=Retrofit.Builder()
                .baseUrl(Contants.SERVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttpClientCreate())
                .build()
    }

    /**
     * 创建okhttp client
     * */
    private fun okhttpClientCreate():OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(logInterceptorCreate())
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build()
    }

    /**
     * 创建日志拦截器
     * */
    private fun logInterceptorCreate():Interceptor{
        val httpLogginInterceptor= HttpLoggingInterceptor()
        httpLogginInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLogginInterceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}