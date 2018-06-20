package com.ivan.androidarchitecturecomponentssearch.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.ivan.androidarchitecturecomponentssearch.MyApplication
import com.ivan.androidarchitecturecomponentssearch.data.api.ApiClient
import com.ivan.androidarchitecturecomponentssearch.data.api.GankApi
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.BaseResultBean
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem
import com.ivan.androidarchitecturecomponentssearch.data.db.dao.GankDao
import com.ivan.androidarchitecturecomponentssearch.data.resource.IResoponseResource
import com.ivan.androidarchitecturecomponentssearch.data.resource.NetworkBoundResource
import com.ivan.androidarchitecturecomponentssearch.data.resource.Resource
import com.ivan.androidarchitecturecomponentssearch.data.resource.Result
import com.ivan.androidarchitecturecomponentssearch.ext.execute
import com.ivan.androidarchitecturecomponentssearch.http.observer.CallbackObserver
import com.ivan.androidarchitecturecomponentssearch.http.RetrofitFactory
import com.ivan.androidarchitecturecomponentssearch.utils.AppExecutors
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.Response

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description:
*/
class GankRepository() {


    var gankDao: GankDao? = null
    var appExecutors: AppExecutors? = null

    init {
        gankDao = MyApplication.getDatabase().gankDao()
        appExecutors = AppExecutors()
    }

    /**
     * 获取所有干货
     * */
    fun getAllGank(category: String, count: Int, page: Int): LiveData<Resource<List<GankItem>>> {
        return object : NetworkBoundResource<List<GankItem>, List<GankItem>>() {
            //存储干货
            override fun saveCallResult(item: List<GankItem>) {
                gankDao!!.save(item)
            }

            //是否要重新fetch
            override fun shouldFetch(data: List<GankItem>): Boolean {
                return true
            }

            //加载数据
            override fun loadFromDb(): LiveData<List<GankItem>> {
                return gankDao!!.load()
            }

            //创建请求
            override suspend fun createCall(): LiveData<Result<BaseResultBean<List<GankItem>>>> = withContext(appExecutors!!.networkContext) {
                ApiClient.getGankData(category, count, page)
            }

            override fun onFetchFailed() {

            }

        }.getAsLiveData()
    }
}