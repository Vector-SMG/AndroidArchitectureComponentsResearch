package com.ivan.androidarchitecturecomponentssearch.data.api.protocol

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/8
* description: 
*/
data class BaseResultBean<out T>(private val error:Boolean,val results:T) {

    fun isSuccessed():Boolean{
        return !error
    }
}