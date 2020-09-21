package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Task;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.service.TaskService;
import com.ty.t100.service.TaskUserService;
import com.ty.t100.util.UUIDUtils;
import com.ty.t100.util.Utils;
import com.ty.t100.vo.Result;
import freemarker.template.utility.StringUtil;
import io.swagger.models.auth.In;
import org.springframework.transaction.annotation.Transactional;
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
 * 任务 前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */

@RestController
@RequestMapping("/task")
@Api(value = "API - TaskController", description = "任务")
public class TaskController {

    private final TaskService taskService;

    private final TaskUserService taskUserService;

    public TaskController(TaskService taskService, TaskUserService taskUserService) {
        this.taskService = taskService;
        this.taskUserService = taskUserService;
    }

    @PostMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "context", value = "正文", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "audio", value = "音视频", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isVideo", value = "音频还是视频", required = false, paramType = "query", dataType = "Boolean"),
            @ApiImplicitParam(name = "publisher", value = "发布者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "observer", value = "接受者", required = true, paramType = "query", dataType = "String")
    })
    @Transactional
    public Result insertTask(String title, String context, String audio, Boolean isVideo, String publisher, @RequestParam(value = "observer") String observers) {
        if (Utils.getInstance().isNull(publisher)) {
            throw new BusinessException("没有发布者");
        }
        if (Utils.getInstance().isNull(observers)) {
            throw new BusinessException("没有接受者");
        }
        Task task = new Task();
        String taskId = UUIDUtils.getInstance().getUUID();
        task.setId(taskId).setTitle(title).setAudio(audio).setIsVideo(isVideo ? 1 : 0).setPublisherId(publisher).setContext(context);
        taskService.insertTask(task);
        taskUserService.insertTask(taskId, Stream.of(observers.split(",")).collect(Collectors.toList()));
        return Result.success();
    }

    @GetMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = false, paramType = "query", dataType = "int")
    })
    public List<Task> list(String userId, Integer page, Integer pageSize) {
        return taskService.list(userId, page, pageSize);
    }
}
