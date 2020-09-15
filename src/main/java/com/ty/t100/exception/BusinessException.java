package com.ty.t100.exception;

public class BusinessException extends RuntimeException{
    private Integer code;

    public BusinessException(Integer code,String e) {
        super(e);
        this.code = code;
    }

    public BusinessException(String e) {
        super(e);
    }

    public String toString(){
        return String.format("code: %d,message: %s", code, getMessage());
    }
}
