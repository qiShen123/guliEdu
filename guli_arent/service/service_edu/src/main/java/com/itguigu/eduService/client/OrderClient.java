package com.itguigu.eduService.client;

import com.itguigu.eduService.client.hystrix.OrderHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(name = "service-order",fallback = OrderHystrix.class)
public interface OrderClient {
    @GetMapping("/order/t-order/isBuy/{courseId}/{memberId}")
    boolean getIsBuy(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
