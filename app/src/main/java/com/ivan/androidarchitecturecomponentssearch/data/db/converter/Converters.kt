package com.ivan.androidarchitecturecomponentssearch.data.db.converter

import android.arch.persistence.room.TypeConverter
import android.text.TextUtils

class Converters {
    @TypeConverter
    open fun arrayToString(array: Array<String>?): String {
        if (array == null || array.size === 0) {
            return ""
        }

        val builder = StringBuilder(array[0])
        for (i in 1..(array.size - 1)) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    open fun stringToArray(value: String?): Array<String>? {
        if (TextUtils.isEmpty(value)) {
            return null
        } else {
            return value!!.split(",").toTypedArray()
        }
    }
}