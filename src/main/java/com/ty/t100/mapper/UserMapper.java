package com.ty.t100.mapper;

import com.ty.t100.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ty.t100.vo.GroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
public interface UserMapper extends BaseMapper<User> {

    List<GroupVo> selectAll();

    List<GroupVo> selectUserByTaskIdAndStatus(@Param("status") Integer status, @Param("taskId") String taskId);
}
