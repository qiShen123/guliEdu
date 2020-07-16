package com.itguigu.statistics.client.hystrix;

import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguigu.statistics.client.UCenterClient;
import org.springframework.stereotype.Component;

@Component
public class UCenterHystrix implements UCenterClient {
    @Override
    public Integer getUserNum(String date) {
        throw new GuliException(20001,"请求超时");
//        return 0;
    }
}
