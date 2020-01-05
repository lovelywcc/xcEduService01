package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangcc
 * @create 2020-01-05 1:48
 * 统一异常捕获类
 **/
@ControllerAdvice//控制器增强
@ResponseBody //注意响应前端json类型数据
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    //配置map,定义异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //定义map的builder对象，用来构建map
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();
    //捕获CustomException异常
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException){
        //记录日志
        LOGGER.error("catch exception:{}",customException.getResultCode().message());
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception exception){
        LOGGER.error("catch exception:{}",exception.getMessage());
        if (EXCEPTIONS==null){
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码相应给用户，如果找不到给用户响应99999
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode!=null){
            return new ResponseResult(resultCode);
        }else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }
    static {
        builder.put(HttpMessageNotReadableException.class,CommonCode.invalid_param);
        builder.put(NullPointerException.class,CommonCode.null_object);
    }
}
