package com.ivan.androidarchitecturecomponentssearch.data.resource

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/19
* description: 
*/
interface IResoponseResource<T> {

    fun isSuccessed():Boolean?

    fun getData():T?

    fun errorMessage():String?

}