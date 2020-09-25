package com.ty.t100.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务 Mapper 接口
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
public interface TaskMapper extends BaseMapper<Task> {

    Page<Task> listByUserId(Page page, @Param("userId") String userId);
}
