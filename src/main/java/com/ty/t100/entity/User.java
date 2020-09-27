package com.ty.t100.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "微信code")
    /**
     * 微信code
     */
    private String code;

    @ApiModelProperty(value = "备注")
    /**
     * 名称
     */
    private String name;

    @ApiModelProperty(value = "名称")
    /**
     * 名称
     */
    private String nickname;

    @ApiModelProperty(value = "权限")
    /**
     * 权限
     */
    private Integer power;

    @ApiModelProperty(value = "组id")
    /**
     * 组id
     */
    private String groupId;

    @ApiModelProperty(value = "创建时间")
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @ApiModelProperty(value = "积分")
    /**
     * 积分
     */
    private Integer integral;


}
