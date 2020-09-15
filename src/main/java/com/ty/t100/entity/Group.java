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
 * 组
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Group对象", description = "组")
public class Group implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "名称")
    /**
     * 名称
     */
    private String name;


}
