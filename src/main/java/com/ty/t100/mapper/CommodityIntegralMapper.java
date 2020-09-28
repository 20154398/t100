package com.ty.t100.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.t100.entity.CommodityIntegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ty.t100.vo.IntegralVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 任务积分流水表 Mapper 接口
 * </p>
 *
 * @author tang
 * @since 2020-09-27
 */
public interface CommodityIntegralMapper extends BaseMapper<CommodityIntegral> {

    Page<IntegralVo> listPageById(Page<IntegralVo> taskPage, @Param("userId") String userId);
}
