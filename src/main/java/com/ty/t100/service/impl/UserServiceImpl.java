package com.ty.t100.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.t100.bean.AppParameters;
import com.ty.t100.entity.User;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.mapper.UserMapper;
import com.ty.t100.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.t100.util.OkHttpCli;
import com.ty.t100.util.UUIDUtils;
import com.ty.t100.util.Utils;
import com.ty.t100.vo.GroupVO;
import com.ty.t100.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private OkHttpCli okHttpCli;

    @Resource
    private AppParameters appParameters;

    @Override
    public UserVo login(String code) {
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", appParameters.getAppid());    //开发者设置中的appId
        requestUrlParam.put("secret", appParameters.getAddSecret());    //开发者设置中的appSecret
        requestUrlParam.put("js_code", code);    //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");    //默认参数
        JSONObject jsonObject = JSON.parseObject(okHttpCli.doGet("https://api.weixin.qq.com/sns/jscode2session", requestUrlParam));
        if (Utils.getInstance().isNull(jsonObject)) {
            throw new BusinessException(String.format("获取jsonObject失败,Code:%s", code));
        }
        if (Utils.getInstance().isNull(jsonObject.get("openid"))) {
            throw new BusinessException(String.format("获取openId失败,errCode:%s,errMsg:%s", jsonObject.get("errcode"), jsonObject.get("errmsg")));
        }
        String openId = jsonObject.get("openid").toString();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", openId);
        User user = getOne(queryWrapper);
        String id = user.getId();
        if (Utils.getInstance().isNull(user)) {
            try {
                id = UUIDUtils.getInstance().getUUID();
                user = new User().setCode(openId).setId(id).setPower(0).setGroupId("1");
                save(user);
            } catch (Exception e) {
                throw new BusinessException("新增用户失败");
            }
        }
        log.info(String.format("登陆成功，code:%s,id:%s", code, id));
        return new UserVo(user);
    }

    @Override
    public List<GroupVO> selectAll() {
        return null;
    }
}
