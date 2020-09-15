package com.ty.t100.service.impl;

import com.ty.t100.entity.Task;
import com.ty.t100.mapper.TaskMapper;
import com.ty.t100.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
