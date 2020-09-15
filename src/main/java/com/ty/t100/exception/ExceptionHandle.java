package com.ty.t100.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public String businessExceptionHandle(BusinessException ex){
        return "Deal BusinessException:" + ex;
    }
}
