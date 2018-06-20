package com.ivan.androidarchitecturecomponentssearch.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem
import com.ivan.androidarchitecturecomponentssearch.data.db.dao.GankDao

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: 
*/
@Database(entities = arrayOf(GankItem::class), version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun gankDao(): GankDao


    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "ganks").build()
    }

}