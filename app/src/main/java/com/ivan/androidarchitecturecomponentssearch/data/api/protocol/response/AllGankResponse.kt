package com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response

import com.google.gson.annotations.SerializedName

data class AllGankResponse(@SerializedName("error")
                           val error: Boolean = false,
                           @SerializedName("results")
                           val results: List<GankItem>?)