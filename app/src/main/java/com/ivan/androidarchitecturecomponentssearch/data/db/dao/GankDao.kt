package com.ivan.androidarchitecturecomponentssearch.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: 
*/
@Dao
interface GankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(ganks: List<GankItem>?)

    @Query(" SELECT * FROM gank")
    fun load():LiveData<List<GankItem>>
}