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
 * 任务用户关联表
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TaskUser对象", description = "任务用户关联表")
public class TaskUser implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    private String taskId;

    @ApiModelProperty(value = "任务接受者id")
    /**
     * 任务接受者id
     */
    private String userId;

    @ApiModelProperty(value = "任务完成情况，0-未完成，1-完成未打分，2-完成已打分")
    /**
     * 任务完成情况，0-未完成，1-完成未打分，2-完成已打分
     */
    private Integer status;


}
