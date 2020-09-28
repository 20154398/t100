package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.TaskIntegral;
import com.ty.t100.service.CommodityIntegralService;
import com.ty.t100.service.TaskIntegralService;
import com.ty.t100.vo.IntegralVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 任务积分流水表 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-27
 */

@RestController
@RequestMapping("/integral")
@Api(value = "API - IntegralController", description = "积分流水")
public class IntegralController {

    private final TaskIntegralService taskIntegralService;

    private final CommodityIntegralService commodityIntegralService;

    public IntegralController(TaskIntegralService taskIntegralService,
                              CommodityIntegralService commodityIntegralService) {
        this.taskIntegralService = taskIntegralService;
        this.commodityIntegralService = commodityIntegralService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("task")
    public Page<IntegralVo> taskIntegral(String userId, Integer page, Integer pageSize) {
        return taskIntegralService.listPageById(userId, page, pageSize);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("commodity")
    public Page<IntegralVo> commodityIntegral(String userId, Integer page, Integer pageSize) {
        return commodityIntegralService.listPageById(userId, page, pageSize);
    }

}
