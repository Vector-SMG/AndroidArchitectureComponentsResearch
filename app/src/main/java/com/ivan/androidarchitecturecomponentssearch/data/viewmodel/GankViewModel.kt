package com.ivan.androidarchitecturecomponentssearch.data.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem
import com.ivan.androidarchitecturecomponentssearch.data.repository.GankRepository
import com.ivan.androidarchitecturecomponentssearch.data.resource.Resource

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: 
*/
class GankViewModel(gankRepository: GankRepository) : ViewModel() {

    private var gank: LiveData<Resource<List<GankItem>>>? = null
    private val gankRepository = gankRepository

    fun init(category: String, count: Int, page: Int) {
        if (gank != null) {
            return
        }
        gank = gankRepository.getAllGank(category, count, page)
    }

    fun getGank(): LiveData<Resource<List<GankItem>>> {
        return gank!!
    }

}