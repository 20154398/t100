package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Commodity;
import com.ty.t100.service.CommodityService;
import com.ty.t100.vo.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-30
 */

@RestController
@RequestMapping("/commodity")
@Api(value = "API - CommodityController", description = "商品表")
public class CommodityController {

    private final CommodityService commodityService;

    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "integral", value = "积分", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remain", value = "剩余数量", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isVideo", value = "音频还是视频", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "publisher", value = "发布者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "observer", value = "接受者", required = true, paramType = "query", dataType = "String")
    })
    @Transactional
    public Result insert(){
        return Result.success();
    }
}
