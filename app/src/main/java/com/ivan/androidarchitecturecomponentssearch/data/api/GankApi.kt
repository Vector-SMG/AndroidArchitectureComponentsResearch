package com.ivan.androidarchitecturecomponentssearch.data.api

import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.BaseResultBean
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.AllGankResponse
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: 干货集中营API
*/
interface GankApi {

    @GET("data/{category}/{count}/{page}")
    fun getCategoryData(@Path("category") category:String,@Path("count") count:Int,@Path("page") page:Int):Call<BaseResultBean<List<GankItem>>>

}