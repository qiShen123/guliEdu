package com.itguigu.order.client.hystrix;

import com.itguigu.order.client.UCenterClient;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguigu.commonutils.orderVo.MemberOrderVo;
import org.springframework.stereotype.Component;

@Component
public class UCenterHystrix implements UCenterClient {

    @Override
    public MemberOrderVo getUserOrderInfo(String userId) {
        throw new GuliException(20001,"查询用户信息失败");
    }
}
