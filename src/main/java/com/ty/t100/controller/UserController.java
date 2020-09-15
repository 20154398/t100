package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.User;
import com.ty.t100.service.UserService;
import com.ty.t100.util.UUIDUtils;
import com.ty.t100.vo.Result;
import com.ty.t100.vo.UserVo;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Objects;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */

@RestController
@RequestMapping("/user")
@Api(value = "API - UserController", description = "用户")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录", notes = "")
    @ApiImplicitParam(name="code", value="code", required=true, paramType="path", dataType="String")
    @PutMapping("/login/{code}")
    public Result login(@PathVariable("code") String code) {
        return userService.login(code);
    }
}
