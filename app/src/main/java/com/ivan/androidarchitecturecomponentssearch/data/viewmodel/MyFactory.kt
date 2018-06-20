package com.ivan.androidarchitecturecomponentssearch.data.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ivan.androidarchitecturecomponentssearch.data.repository.GankRepository
import java.lang.reflect.InvocationTargetException

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/7
* description: 
*/
class MyFactory(gankRepository: GankRepository): ViewModelProvider.Factory {

     private val  gankRepository=gankRepository


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {

            try {
                return modelClass.getConstructor(GankRepository::class.java).newInstance(gankRepository)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }

        }
        try {
            return modelClass.newInstance()
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}