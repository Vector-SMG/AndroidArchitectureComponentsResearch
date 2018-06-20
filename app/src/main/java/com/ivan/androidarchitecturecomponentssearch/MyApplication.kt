package com.ivan.androidarchitecturecomponentssearch

import android.app.Application
import android.util.Log
import com.ivan.androidarchitecturecomponentssearch.data.db.MyDatabase

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: 
*/
class MyApplication :Application(){


    companion object {
        private var instance:Application?=null
        private var myDatabase:MyDatabase?=null

        fun getInstance()= instance!!
        fun getDatabase()= myDatabase!!
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        myDatabase=MyDatabase.getInstance(this)
    }
}