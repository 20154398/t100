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
 * 任务积分流水表
 * </p>
 *
 * @author tang
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TaskIntegral对象", description = "任务积分流水表")
public class TaskIntegral implements Serializable {


    @ApiModelProperty(value = "创建时间")
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @ApiModelProperty(value = "任务完成者id")
    /**
     * 任务完成者id
     */
    private String userId;

    @ApiModelProperty(value = "任务id")
    /**
     * 任务id
     */
    private String taskId;

    @ApiModelProperty(value = "积分流水")
    /**
     * 积分流水
     */
    private Integer integral;


}
