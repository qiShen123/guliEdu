package com.itguigu.order.client;

import com.itguigu.order.client.hystrix.UCenterHystrix;
import com.itguihu.commonutils.orderVo.MemberOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component(value = "uCenterClient")
@FeignClient(value = "service-uCenter",fallback = UCenterHystrix.class)
public interface UCenterClient {
    @GetMapping("/uCenter/member/getUserInfoOrder/{userId}")
    MemberOrderVo getUserOrderInfo(@PathVariable("userId") String userId);
}
