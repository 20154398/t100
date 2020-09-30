package com.ty.t100.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品图片对应表
 * </p>
 *
 * @author tang
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CommodityImage对象", description = "商品图片对应表")
public class CommodityImage implements Serializable {


    @ApiModelProperty(value = "ID")
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "视频或文件的url")
    /**
     * 视频或文件的url
     */
    private String imageUrl;

    @ApiModelProperty(value = "商品ID")
    /**
     * 商品ID
     */
    private String commodityId;


}
