package com.sanyicloud.sanyi.common.core.exception;

import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * by zhaowenyuan create 2022/1/28 11:33
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResultException.class)
    public Result handlerResultException(ResultException e){
        return Result.restResult(e.getMessage(), e.getCode(), e.getMsg());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CheckedException.class)
    public Result handlerCheckedException(CheckedException e){
        return Result.failed(e.getMessage());
    }
}
