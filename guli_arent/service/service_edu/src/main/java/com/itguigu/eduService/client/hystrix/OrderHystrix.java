package com.itguigu.eduService.client.hystrix;

import com.itguigu.eduService.client.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderHystrix implements OrderClient {

    @Override
    public boolean getIsBuy(String courseId, String memberId) {
        return false;
    }
}
