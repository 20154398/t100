package com.ty.t100.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.CommodityIntegral;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.t100.vo.IntegralVo;

/**
 * <p>
 * 任务积分流水表 服务类
 * </p>
 *
 * @author tang
 * @since 2020-09-27
 */
public interface CommodityIntegralService extends IService<CommodityIntegral> {

    Page<IntegralVo> listPageById(String userId, Integer page, Integer pageSize);
}
