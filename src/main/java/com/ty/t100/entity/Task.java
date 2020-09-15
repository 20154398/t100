package com.ty.t100.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class Task implements Serializable {


    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    private String context;

    /**
     * 视频或文件的url
     */
    private String audio;

    /**
     * 区分视频和文件
     */
    @TableField("isVideo")
    private Integer isVideo;

    /**
     * 任务发布者id
     */
    private String publisherId;


}
