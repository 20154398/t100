package com.ty.t100.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Task;
import com.ty.t100.entity.TaskIntegral;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.mapper.TaskIntegralMapper;
import com.ty.t100.service.TaskIntegralService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.t100.vo.IntegralVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 任务积分流水表 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-27
 */
@Service
@Slf4j
public class TaskIntegralServiceImpl extends ServiceImpl<TaskIntegralMapper, TaskIntegral> implements TaskIntegralService {

    @Resource
    private TaskIntegralMapper taskIntegralMapper;

    @Override
    public Page<IntegralVo> listPageById(String userId, Integer page, Integer pageSize) {
        Page<IntegralVo> voPage = new Page<>(page, pageSize);
        try {
            return taskIntegralMapper.listPageById(voPage, userId);
        } catch (Exception e) {
            log.error("根据userId查询任务流水失败，", e);
            throw new BusinessException("根据userId查询任务流水失败");
        }
    }
}
