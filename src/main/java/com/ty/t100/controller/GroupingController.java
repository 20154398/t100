package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.t100.entity.Grouping;
import com.ty.t100.entity.User;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.service.GroupingService;
import com.ty.t100.service.UserService;
import com.ty.t100.util.Utils;
import com.ty.t100.vo.GroupVo;
import com.ty.t100.vo.Result;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <p>
 * 组 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-30
 */

@RestController
@RequestMapping("/group")
@Api(value = "API - GroupController", description = "组")
public class GroupingController {

    private final GroupingService groupingService;

    private final UserService userService;

    public GroupingController(GroupingService groupingService,
                           UserService userService) {
        this.groupingService = groupingService;
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "查询所有分组", notes = "")
    public List<GroupVo> selectAll() {
        List<Grouping> Groupings = groupingService.list();
        return Stream.of(Groupings.toArray())
                .map(Grouping.class::cast)
                .map(Grouping -> {
                    GroupVo groupingVo = new GroupVo();
                    groupingVo.setId(Grouping.getId());
                    groupingVo.setName(Grouping.getName());
                    return groupingVo;
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "新增分组", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupingName", value = "分组名称", required = true, paramType = "query", dataType = "String")
    })
    public Result insert(String userId, String groupingName) {
        User user = userService.getById(userId);
        if (!Utils.getInstance().isNull(user) && user.getPower() != 0) {
            throw new BusinessException("没有权限新增");
        }
        QueryWrapper<Grouping> queryWrapper = new QueryWrapper<Grouping>();
        queryWrapper.eq("name", groupingName);
        Grouping grouping = groupingService.getOne(queryWrapper);
        if (!Utils.getInstance().isNull(grouping)) {
            throw new BusinessException("已有分组");
        }
        return groupingService.save(new Grouping().setName(groupingName)) ? Result.success() : Result.fail("新增失败");
    }

    @DeleteMapping
    @ApiOperation(value = "删除分组", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupingId", value = "分组id", required = true, paramType = "query", dataType = "String")
    })
    public Result delete(String userId, String groupingId) {
        User user = userService.getById(userId);
        if (!Utils.getInstance().isNull(user) && user.getPower() != 0){
            throw new BusinessException("没有权限删除");
        }
        if ("1".equals(groupingId)) {
            throw new BusinessException("默认分组无法删除");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("grouping_id", groupingId);
        List<User> users = userService.list(queryWrapper);
        if (users.size() != 0) {
            throw new BusinessException("改分组还有用户，无法删除");
        }
        Grouping grouping = groupingService.getById(groupingId);
        if (!Utils.getInstance().isNull(grouping)) {
            throw new BusinessException("没有分组");
        }
        return groupingService.removeById(groupingId) ? Result.success() : Result.fail("删除失败");
    }

    @PutMapping
    @ApiOperation(value = "更新分组名称", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupingId", value = "分组id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "分组名称", required = true, paramType = "query", dataType = "String")
    })
    public Result updateName(String userId, String groupingId, String name) {
        User user = userService.getById(userId);
        if (!Utils.getInstance().isNull(user) && user.getPower() != 0) {
            throw new BusinessException("没有权限更新");
        }
        if ("1".equals(groupingId)) {
            throw new BusinessException("默认分组无法改名");
        }
        Grouping grouping = groupingService.getById(groupingId);
        if (Utils.getInstance().isNull(grouping)) {
            throw new BusinessException("没有分组");
        }
        grouping.setName(name);
        return groupingService.updateById(grouping) ? Result.success() : Result.fail("更新分组名称失败");
    }

    @PostMapping("/user")
    @ApiOperation(value = "更新用户分组", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupingId", value = "分组id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "selectId", value = "被修改者id", required = true, paramType = "query", dataType = "String")
    })
    public Result changeGrouping(String userId, String groupingId, String selectId) {
        User user = userService.getById(userId);
        if (!Utils.getInstance().isNull(user) && user.getPower() != 0) {
            throw new BusinessException("没有权限修改分组");
        }
        Grouping grouping = groupingService.getById(groupingId);
        if (Utils.getInstance().isNull(grouping)) {
            throw new BusinessException("没有分组");
        }
        User selectUser = userService.getById(selectId);
        if (Utils.getInstance().isNull(selectId)) {
            throw new BusinessException("没有被修改者");
        }
        selectUser.setGroupId(groupingId);
        return userService.updateById(selectUser) ? Result.success() : Result.fail("更新用户分组失败");
    }
}
