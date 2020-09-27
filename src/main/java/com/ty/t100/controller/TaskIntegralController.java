package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.TaskIntegral;
import com.ty.t100.service.TaskIntegralService;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * <p>
 * 任务积分流水表 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-27
 */

@RestController
@RequestMapping("/task-integral")
@Api(value = "API - TaskIntegralController", description = "任务积分流水表")
public class TaskIntegralController {

    private final TaskIntegralService taskIntegralService;

    public TaskIntegralController(TaskIntegralService taskIntegralService) {
        this.taskIntegralService = taskIntegralService;
    }
}
