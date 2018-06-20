package com.ivan.androidarchitecturecomponentssearch.data.resource

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/20
* description: 
*/
 class Result<T> private constructor(var status:Int,var data:T?,var message:String?){


    companion object {

        const val SUCCEEDED_CODE=200

        fun <T> success(data:T):Result<T>{
            return Result(SUCCEEDED_CODE,data,null)
        }

        fun <T> error(status:Int,message: String?):Result<T>{
            return Result(status,null,message)
        }

    }

    fun isSucceeded():Boolean{
        return status==SUCCEEDED_CODE
    }
}