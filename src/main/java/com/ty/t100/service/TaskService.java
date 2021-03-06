package com.ty.t100.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.t100.exception.BusinessException;

import java.util.List;

/**
 * <p>
 * 任务 服务类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
public interface TaskService extends IService<Task> {

    void insertTask(Task task) throws BusinessException;

    Page<Task> list(String userId, Integer page, Integer pageSize) throws BusinessException;

    Page<Task> listPublisher(String userId, Integer page, Integer pageSize);
}
