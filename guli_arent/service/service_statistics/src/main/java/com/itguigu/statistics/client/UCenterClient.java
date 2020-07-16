package com.itguigu.statistics.client;

import com.itguigu.statistics.client.hystrix.UCenterHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "service-uCenter", fallback = UCenterHystrix.class)
@Component(value = "uCenterClient")
public interface UCenterClient {
    @GetMapping("/uCenter/member/getUserNum/{date}")
    Integer getUserNum(@PathVariable("date") String date);
}
