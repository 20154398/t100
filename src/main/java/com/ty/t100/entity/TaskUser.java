package com.ty.t100.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class TaskUser implements Serializable {


    /**
     * ID
     */
    private String taskId;

    /**
     * 任务接受者id
     */
    private String userId;

    /**
     * 任务完成情况，0-未完成，1-完成未打分，2-完成已打分
     */
    private Integer status;


}
