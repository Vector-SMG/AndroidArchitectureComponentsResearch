package com.ivan.androidarchitecturecomponentssearch.data.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.WorkerThread
import android.util.Log
import com.chngalaxy.bussinesscomponent.api.exception.ApiException
import com.chngalaxy.bussinesscomponent.api.exception.ExceptionManager
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.BaseResultBean
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem
import com.ivan.androidarchitecturecomponentssearch.data.resource.IResoponseResource
import com.ivan.androidarchitecturecomponentssearch.data.resource.Result
import com.ivan.androidarchitecturecomponentssearch.http.RetrofitFactory
import retrofit2.Response
import java.io.IOException
import java.util.logging.ErrorManager

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/19
* description: 
*/
class ApiClient {

    companion object {

        @WorkerThread
        fun getGankData(category: String, count: Int, page: Int): LiveData<Result<BaseResultBean<List<GankItem>>>> {
            var liveData = MediatorLiveData<Result<BaseResultBean<List<GankItem>>>>()
            var call = RetrofitFactory.instance.create(GankApi::class.java).getCategoryData(category, count, page)
            var response: Response<BaseResultBean<List<GankItem>>>?
            var errorMessage: ApiException?
            try {
                response = call.execute()
                if(response.isSuccessful){
                    response.body()?.let {
                        liveData.postValue(Result.success(it))
                    }
                }else{
                    liveData.postValue(Result.error(response.code(),response.message()))
                }
            } catch (e: Exception) {
                errorMessage = ExceptionManager.convertException(e)
                liveData.postValue(Result.error(errorMessage.code,errorMessage.message))
            }
            return liveData
        }
    }
}