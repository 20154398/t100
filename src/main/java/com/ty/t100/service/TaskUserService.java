package com.ty.t100.service;

import com.ty.t100.entity.TaskUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.t100.exception.BusinessException;

import java.util.List;

/**
 * <p>
 * 任务用户关联表 服务类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
public interface TaskUserService extends IService<TaskUser> {

    void insertTask(String taskId, List<String> observer);
}
