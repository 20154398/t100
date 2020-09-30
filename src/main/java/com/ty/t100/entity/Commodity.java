package com.ty.t100.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author tang
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Commodity对象", description = "商品表")
public class Commodity implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "积分")
    /**
     * 积分
     */
    private Integer integral;

    @ApiModelProperty(value = "商品名称")
    /**
     * 商品名称
     */
    private String name;

    @ApiModelProperty(value = "剩余数量")
    /**
     * 剩余数量
     */
    private Integer remain;

    @ApiModelProperty(value = "商品上传者")
    /**
     * 商品上传者
     */
    private String publisherId;

    @ApiModelProperty(value = "描述")
    /**
     * 描述
     */
    private String description;


}
