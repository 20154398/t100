package com.ty.t100.controller;

import com.ty.t100.service.UserService;
import com.ty.t100.vo.GroupVo;
import com.ty.t100.vo.UserVo;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;


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
    public UserVo login(@PathVariable("code") String code) {
        return userService.login(code);
    }

    @ApiOperation(value = "用户查询", notes = "")
    @GetMapping("/selectAll")
    public List<GroupVo> selectAll() {
        return userService.selectAll();
    }
}
