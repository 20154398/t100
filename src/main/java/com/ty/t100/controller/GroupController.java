package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Group;
import com.ty.t100.service.GroupService;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * <p>
 * 组 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */

@RestController
@RequestMapping("/group")
@Api(value = "API - GroupController", description = "组")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
}
