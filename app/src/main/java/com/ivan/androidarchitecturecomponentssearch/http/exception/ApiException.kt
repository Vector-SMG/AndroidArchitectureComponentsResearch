package com.chngalaxy.bussinesscomponent.api.exception

/*
 * @author liuwei
 * @email 13040839537@163.com
 * create at 2018/4/12
 * description: api异常
 */

open class ApiException(var code: Int, message: String) : Exception(message)
