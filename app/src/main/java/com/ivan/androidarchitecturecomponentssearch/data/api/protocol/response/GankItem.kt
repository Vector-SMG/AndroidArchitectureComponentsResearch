package com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import com.ivan.androidarchitecturecomponentssearch.data.db.converter.Converters

@Entity(tableName = "gank")
@TypeConverters(Converters::class)
data class GankItem(@ColumnInfo(name = "createdAt")
                       @SerializedName("createdAt")
                       val createdAt: String = "",
                    @ColumnInfo(name = "images")
                       @SerializedName("images")
                       val images: Array<String>?,
                    @ColumnInfo(name = "publishedAt")
                       @SerializedName("publishedAt")
                       val publishedAt: String = "",
                    @PrimaryKey
                       @ColumnInfo(name = "_id")
                       @SerializedName("_id")
                       val Id: String = "",
                    @ColumnInfo(name = "source")
                       @SerializedName("source")
                       val source: String = "",
                    @ColumnInfo(name = "used")
                       @SerializedName("used")
                       val used: Boolean = false,
                    @ColumnInfo(name = "type")
                       @SerializedName("type")
                       val type: String = "",
                    @ColumnInfo(name = "url")
                       @SerializedName("url")
                       val url: String = "",
                    @ColumnInfo(name = "desc")
                       @SerializedName("desc")
                       val desc: String = "",
                    @ColumnInfo(name = "who")
                       @SerializedName("who")
                       val who: String = "")