package com.ty.t100.vo;

import com.ty.t100.entity.TaskIntegral;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IntegralVo {

    private LocalDateTime createTime;

    private String integralId;

    private Integer integral;

    private String name;

    /**
     * 0是任务，1是商品
     */
    private Integer integralType;
}
