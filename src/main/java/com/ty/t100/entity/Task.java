package com.ty.t100.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Task对象", description = "任务")
public class Task implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "标题")
    /**
     * 标题
     */
    private String title;

    @ApiModelProperty(value = "正文")
    /**
     * 正文
     */
    private String context;

    @ApiModelProperty(value = "视频或文件的url")
    /**
     * 视频或文件的url
     */
    private String audio;

    @ApiModelProperty(value = "区分视频和文件")
    /**
     * 区分视频和文件
     */
    @TableField("isVideo")
    private Integer isVideo;

    @ApiModelProperty(value = "任务发布者id")
    /**
     * 任务发布者id
     */
    private String publisherId;

    @ApiModelProperty(value = "创建时间")
    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
