package com.ty.t100.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TaskVo {

    private String id;

    private String title;

    private String context;

    private String audio;

    private Integer isVideo;

    private String publisherId;

    private LocalDateTime createTime;

    private Integer status;
}
