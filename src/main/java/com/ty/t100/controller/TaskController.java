package com.ty.t100.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ty.t100.entity.Task;
import com.ty.t100.entity.TaskIntegral;
import com.ty.t100.entity.TaskUser;
import com.ty.t100.entity.User;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.service.TaskIntegralService;
import com.ty.t100.service.TaskService;
import com.ty.t100.service.TaskUserService;
import com.ty.t100.service.UserService;
import com.ty.t100.util.OptionalHelp;
import com.ty.t100.util.UUIDUtils;
import com.ty.t100.util.Utils;
import com.ty.t100.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
@Slf4j
public class TaskController {

    private final TaskService taskService;

    private final TaskUserService taskUserService;

    private final UserService userService;

    private final TaskIntegralService taskIntegralService;

    public TaskController(TaskService taskService,
                          TaskUserService taskUserService,
                          UserService userService,
                          TaskIntegralService taskIntegralService) {
        this.taskService = taskService;
        this.taskUserService = taskUserService;
        this.userService = userService;
        this.taskIntegralService = taskIntegralService;
    }

    @PostMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "context", value = "正文", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "audio", value = "音视频", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isVideo", value = "音频还是视频", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "publisher", value = "发布者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "observer", value = "接受者", required = true, paramType = "query", dataType = "String")
    })
    @Transactional
    public Result insertTask(@RequestParam(value = "title") String title,
                             @RequestParam(value = "context") String context,
                             @RequestParam(value = "audio") String audio,
                             @RequestParam(value = "isVideo") Integer isVideo,
                             @RequestParam(value = "publisher") String publisher,
                             @RequestParam(value = "observer") String observers) {
        if (Utils.getInstance().isNull(publisher)) {
            throw new BusinessException("没有发布者");
        }
        if (Utils.getInstance().isNull(observers)) {
            throw new BusinessException("没有接受者");
        }
        Task task = new Task();
        String taskId = UUIDUtils.getInstance().getUUID();
        task.setId(taskId).setTitle(title).setAudio(audio).setIsVideo(isVideo).setPublisherId(publisher).setContext(context);
        taskService.insertTask(task);
        taskUserService.insertTask(taskId, Stream.of(observers.split(",")).collect(Collectors.toList()));
        return Result.success();
    }

    @GetMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "power", value = "权限", required = true, paramType = "query", dataType = "int")
    })
    public IPage<Task> list(String userId, Integer page, Integer pageSize, Integer power) {
        return power == 1 ? taskService.list(userId, page, pageSize) : taskService.listPublisher(userId, page, pageSize);
    }

    @DeleteMapping("/task/{id}")
    @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "String")
    public Result delete(@PathVariable("id") String id, HttpServletRequest request) {
        Task task = taskService.getById(id);
        if (Utils.getInstance().isNull(task)) {
            throw new BusinessException("没有该任务");
        }
        OptionalHelp.ofNullable(task.getAudio())
                .filter(url -> !Utils.getInstance().isNull(url))
                .ifPresent(url -> {
                    String path = Utils.getInstance().getFilePath(request, url);
                    File file = new File(path);
                    if (file.exists() && file.delete()) {
                        log.info("文件删除成功，文件地址:" + path);
                    }
                });
        taskService.removeById(id);
        return Result.success();
    }

    @PostMapping("/finishTask")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "taskId", value = "任务id", required = true, paramType = "query", dataType = "String"),
    })
    public Result finishTask(String userId, String taskId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, String> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("task_id", taskId);
        queryWrapper.allEq(param);
        TaskUser taskUser = taskUserService.getOne(queryWrapper);
        if (taskUser.getStatus() == 1) {
            throw new BusinessException("该任务已完成");
        }
        taskUser.setStatus(1);
        return taskUserService.update(taskUser, queryWrapper) ? Result.success() : Result.fail("更新任务状态失败");
    }

    @PostMapping("/scoring")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "submitterId", value = "任务接受者id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "taskId", value = "任务id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "integral", value = "获得积分", required = true, paramType = "query", dataType = "Integer")
    })
    @Transactional
    public Result scoring(String userId, String submitterId, String taskId, Integer integral) {
        User user = userService.getById(userId);
        if (user.getPower() != 0) {
            throw new BusinessException("没有权限打分");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, String> param = new HashMap<>();
        param.put("user_id", submitterId);
        param.put("task_id", taskId);
        queryWrapper.allEq(param);
        TaskUser taskUser = taskUserService.getOne(queryWrapper);
        if (taskUser.getStatus() == 0) {
            throw new BusinessException("任务未完成");
        }
        User observer = userService.getById(submitterId);
        observer.setIntegral(observer.getIntegral() + integral);
        taskUser.setStatus(2);
        TaskIntegral taskIntegral = new TaskIntegral().setIntegral(integral).setTaskId(taskId).setUserId(submitterId);
        return userService.updateById(observer) && taskUserService.update(taskUser, queryWrapper) && taskIntegralService.save(taskIntegral) ?
                Result.success() : Result.fail("打分失败");
    }
}
