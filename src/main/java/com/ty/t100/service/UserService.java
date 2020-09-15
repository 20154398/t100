package com.ty.t100.service;

import com.ty.t100.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.t100.vo.Result;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
public interface UserService extends IService<User> {

    Result login(String code);
}
