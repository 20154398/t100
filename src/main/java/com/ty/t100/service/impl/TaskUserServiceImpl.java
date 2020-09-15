package com.ty.t100.service.impl;

import com.ty.t100.entity.TaskUser;
import com.ty.t100.mapper.TaskUserMapper;
import com.ty.t100.service.TaskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务用户关联表 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Service
public class TaskUserServiceImpl extends ServiceImpl<TaskUserMapper, TaskUser> implements TaskUserService {

}
