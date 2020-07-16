package com.itguigu.order.mapper;

import com.itguigu.order.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author shenqi
 * @since 2020-07-10
 */
@Mapper
public interface OrderMapper extends BaseMapper<TOrder> {

}
