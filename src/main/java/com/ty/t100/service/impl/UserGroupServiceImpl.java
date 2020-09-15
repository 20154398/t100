package com.ty.t100.service.impl;

import com.ty.t100.entity.UserGroup;
import com.ty.t100.mapper.UserGroupMapper;
import com.ty.t100.service.UserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组用户关联表 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {

}
