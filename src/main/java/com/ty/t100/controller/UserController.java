package com.ty.t100.controller;

import com.ty.t100.entity.User;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.service.UserService;
import com.ty.t100.util.Utils;
import com.ty.t100.vo.GroupVo;
import com.ty.t100.vo.Result;
import com.ty.t100.vo.UserVo;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "用户微信名", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("/login")
    public UserVo login(String code, String nickname) {
        return userService.login(code, nickname);
    }

    @ApiOperation(value = "用户查询", notes = "")
    @GetMapping("/selectAll")
    public List<GroupVo> selectAll() {
        return userService.selectAll();
    }

    @ApiOperation(value = "用户查询根据任务和状态", notes = "")
    @GetMapping("/selectUser")
    public List<GroupVo> selectUserByTaskIdAndStatus(Integer status, String taskId) {
        return userService.selectUserByTaskIdAndStatus(status, taskId);
    }

    @ApiOperation(value = "修改用户备注", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "管理员id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "selectId", value = "被改人ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "被改姓名", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/changeName")
    public Result changeName(String userId, String selectId, String name) {
        User user = userService.getById(userId);
        if (!Utils.getInstance().isNull(user) && user.getPower() != 0) {
            throw new BusinessException("没有权限打分");
        }
        User selectUser = userService.getById(selectId);
        if (Utils.getInstance().isNull(selectUser)) {
            throw new BusinessException("没有被修改人");
        }
        selectUser.setName(name);
        return userService.updateById(selectUser) ? Result.success() : Result.fail("修改备注失败");
    }
}
