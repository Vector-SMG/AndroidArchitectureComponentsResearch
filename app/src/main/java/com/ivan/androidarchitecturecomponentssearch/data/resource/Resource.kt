package com.ivan.androidarchitecturecomponentssearch.data.resource

class Resource<T> private constructor(val status: Int, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(1, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(2, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(3, data, null)
        }
    }
}