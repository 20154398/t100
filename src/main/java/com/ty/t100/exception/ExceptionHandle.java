package com.ty.t100.exception;

import com.ty.t100.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessExceptionHandle(BusinessException ex) {
        return Result.fail("Deal BusinessException:" + ex);
    }
}
