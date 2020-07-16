package com.itguigu.order.service.impl;

import com.itguigu.order.entity.PayLog;
import com.itguigu.order.mapper.PayLogMapper;
import com.itguigu.order.service.IPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-10
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements IPayLogService {

}
