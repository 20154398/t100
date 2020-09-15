package com.ty.t100.service.impl;

import com.ty.t100.entity.TaskUser;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.mapper.TaskUserMapper;
import com.ty.t100.service.TaskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 任务用户关联表 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Service
@Slf4j
public class TaskUserServiceImpl extends ServiceImpl<TaskUserMapper, TaskUser> implements TaskUserService {

    @Override
    public void insertTask(String taskId, List<String> observer) {
        try {
            List<TaskUser> taskUserList = new ArrayList<>();
            observer.forEach(obs -> {
                taskUserList.add(new TaskUser().setTaskId(taskId).setUserId(obs));
            });
            saveBatch(taskUserList);
        } catch (Exception e) {
            log.error("插入接收者和任务表失败，", e);
            throw new BusinessException("插入接收者和任务表失败");
        }
    }
}
