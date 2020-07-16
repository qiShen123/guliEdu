package com.itguigu.order.service;

import com.itguigu.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-10
 */
public interface IOrderService extends IService<TOrder> {

    String createOrder(String courseId,String memberId);
}
