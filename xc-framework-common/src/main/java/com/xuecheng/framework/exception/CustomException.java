package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @author wangcc
 * @create 2020-01-05 1:30
 * 自定义异常需要继承RuntimeException
 **/
public class CustomException extends RuntimeException {

    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode=resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }
}
