package com.ty.t100.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Task;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.mapper.TaskMapper;
import com.ty.t100.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 任务 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Service
@Slf4j
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public void insertTask(Task task) throws BusinessException {
        try {
            save(task);
        } catch (Exception e) {
            log.error("插入任务表失败，", e);
            throw new BusinessException("插入任务表失败");
        }
    }

    @Override
    public Page<Task> list(String userId, Integer page, Integer pageSize) throws BusinessException {
        Page<Task> taskPage = new Page<>(page, pageSize);
        try {
            return taskMapper.listByUserId(taskPage, userId);
        } catch (Exception e) {
            log.error("根据userid查询任务表失败，", e);
            throw new BusinessException("根据userid查询任务表失败");
        }
    }

    @Override
    public Page<Task> listPublisher(String userId, Integer page, Integer pageSize) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper();
        queryWrapper.eq("publisher_id", userId);
        queryWrapper.orderByDesc("create_time");
        Page<Task> taskPage = new Page<>(page, pageSize);
        try {
            return (Page<Task>) page(taskPage, queryWrapper);
        } catch (Exception e) {
            log.error("根据userid查询任务表失败，", e);
            throw new BusinessException("根据userid查询任务表失败");
        }
    }
}
