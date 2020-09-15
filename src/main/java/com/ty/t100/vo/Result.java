package com.ty.t100.vo;

import lombok.Data;

@Data
public class Result {
    private Integer code;

    private Object data;

    private String message;

    public Result(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static Result success(){
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(200, data, "success");
    }

    public static Result fail(String message) {
        return new Result(500, null, message);
    }
}
