package com.ivan.androidarchitecturecomponentssearch.data.resource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.BaseResultBean
import com.ivan.androidarchitecturecomponentssearch.utils.AppExecutors
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/8
* description: 
*/
abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        var dbSource = loadFromDb()
        result.addSource(dbSource) {
            result.removeSource(dbSource)
            it?.let {
                if (shouldFetch(it)) {
                    fetchFromNetWork(dbSource)
                } else {
                    result.addSource(dbSource) {
                        result.value = Resource.success(it!!)
                    }
                }
            }
        }
    }

    private fun fetchFromNetWork(dbSource: LiveData<ResultType>) {
        launch(AppExecutors().uiContext) {
            var apiResponse = createCall()
            result.addSource(dbSource) { result.value = Resource.loading(it!!) }
            result.addSource(apiResponse) {
                result.removeSource(apiResponse)
                result.removeSource(dbSource)
                it?.let {
                    it ->
                    if (it.isSucceeded()) {
                        saveResultAndReInit(it.data!!)
                    } else {
                        onFetchFailed()
                        result.addSource(dbSource) {
                            it1 ->
                            result.value = Resource.error(it.message!!,it1)
                        }
                    }
                }
            }
        }
    }

    private  fun saveResultAndReInit(it: BaseResultBean<RequestType>) {
        launch(AppExecutors().ioContext) {
            var task = async(AppExecutors().ioContext) { saveCallResult(it.results) }
            var results = task.await()
            result.addSource(loadFromDb()
            ) { result.value = Resource.success(it!!) }
        }

    }

    /**
     * 保存API返回结果至数据库
     * */
    @WorkerThread
    abstract fun saveCallResult(item: RequestType)


    /**
     * 被调用判断是否应该从网络获取数据
     * */
    @MainThread
    abstract fun shouldFetch(data: ResultType): Boolean

    /**
     * 被调用从数据库获取缓存数据
     * */
    @MainThread
    abstract fun loadFromDb(): LiveData<ResultType>


    /**
     * 被调用创建API请求
     * */
    @MainThread
    abstract suspend fun createCall(): LiveData<Result<BaseResultBean<RequestType>>>


    /**
     * 当数据失败调用
     * */
    @MainThread
    abstract fun onFetchFailed()

    fun getAsLiveData(): LiveData<Resource<ResultType>> {
        return result
    }


}