package com.ty.t100.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.CommodityIntegral;
import com.ty.t100.entity.Task;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.mapper.CommodityIntegralMapper;
import com.ty.t100.service.CommodityIntegralService;
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
public class CommodityIntegralServiceImpl extends ServiceImpl<CommodityIntegralMapper, CommodityIntegral> implements CommodityIntegralService {

    @Resource
    private CommodityIntegralMapper commodityIntegralMapper;

    @Override
    public Page<IntegralVo> listPageById(String userId, Integer page, Integer pageSize) {
        Page<IntegralVo> voPage = new Page<>(page, pageSize);
        try {
            return commodityIntegralMapper.listPageById(voPage, userId);
        } catch (Exception e) {
            log.error("根据userId查询商品流水失败，", e);
            throw new BusinessException("根据userId查询商品流水失败");
        }
    }
}
