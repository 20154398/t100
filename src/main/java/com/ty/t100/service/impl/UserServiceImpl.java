package com.ty.t100.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.t100.entity.User;
import com.ty.t100.mapper.UserMapper;
import com.ty.t100.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.t100.util.UUIDUtils;
import com.ty.t100.vo.Result;
import com.ty.t100.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-09-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result login(String code) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", code);
        User user = getOne(queryWrapper);
        if (Objects.nonNull(user)) {
            return Result.success(new UserVo(user));
        } else {
            try {
                String id = UUIDUtils.getInstance().getUUID();
                user = new User().setCode(code).setId(id).setPower(0);
                save(user);
                return Result.success(new UserVo(user));
            } catch (Exception e) {
                return Result.fail("保存用户失败");
            }
        }
    }
}
