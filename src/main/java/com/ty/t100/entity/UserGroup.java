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
 * 组用户关联表
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserGroup对象", description = "组用户关联表")
public class UserGroup implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    private String userId;

    @ApiModelProperty(value = "名称")
    /**
     * 名称
     */
    private String groupId;


}
