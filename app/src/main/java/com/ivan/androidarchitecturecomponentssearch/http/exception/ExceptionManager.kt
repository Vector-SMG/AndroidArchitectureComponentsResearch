package com.chngalaxy.bussinesscomponent.api.exception

import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException

import org.json.JSONException

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

import retrofit2.HttpException

/*
 * @author liuwei
 * @email 13040839537@163.com
 * create at 2018/4/12
 * description: 异常管理
 */
object ExceptionManager {
    val TAG = "ExceptionManager"
    val NO_NETWORK_ERROR = "无网络连接!"
    val NETWORK_ERROR = "网络错误! "
    val PARSE_ERROR = "服务端异常,数据解析错误!"
    val CONNECT_ERROR = "连接失败! "
    val CONNECT_TIME_OUT = "连接超时! "
    val UNKNOWN_ERROR = "未知错误! "


    val NO_NETWORK_ERROR_CODE = 1
    val NETWORK_ERROR_CODE = 2
    val PARSE_ERROR_CODE = 3
    val CONNECT_ERROR_CODE = 4
    val CONNECT_TIME_OUT_CODE = 5
    val UNKNOWN_ERROR_CODE = 6

    fun convertException(e: Throwable): ApiException {
        val apiException: ApiException
        if (e is UnknownHostException) {
            apiException = ApiException(NO_NETWORK_ERROR_CODE, NO_NETWORK_ERROR)
        } else if (e is HttpException) {    //HTTP错误
            apiException = ApiException(NETWORK_ERROR_CODE, NETWORK_ERROR + " " + e.message)
        } else if (e is BusinessException) {    //服务器返回的错误
            apiException = ApiException(e.code, e.message!!)
        } else if (e is JsonParseException || e is JSONException
                || e is ParseException || e is MalformedJsonException) {  //解析数据错误
            apiException = ApiException(PARSE_ERROR_CODE, PARSE_ERROR)
        } else if (e is ConnectException) {//连接网络错误
            apiException = ApiException(CONNECT_ERROR_CODE, CONNECT_ERROR)
        } else if (e is SocketTimeoutException) {//网络超时
            apiException = ApiException(CONNECT_TIME_OUT_CODE, CONNECT_TIME_OUT)
        } else {  //未知错误
            apiException = ApiException(UNKNOWN_ERROR_CODE, UNKNOWN_ERROR)
        }
        return apiException
    }

}
