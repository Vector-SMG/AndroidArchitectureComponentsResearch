package com.chngalaxy.bussinesscomponent.api.exception


/*
 * @author liuwei
 * @email 13040839537@163.com
 * create at 2018/4/12
 * description: api服务端业务异常
 */

class BusinessException(code: Int, message: String) : ApiException(code, message)
