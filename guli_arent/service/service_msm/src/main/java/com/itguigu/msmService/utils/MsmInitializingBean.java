package com.itguigu.msmService.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsmInitializingBean implements InitializingBean {
    @Value("${aliyun.vod.file.regionId}")
    private String regionId;
    @Value("${aliyun.vod.file.keyId}")
    private String accessKeyId;
    @Value("${aliyun.vod.file.accessKeySecret}")
    private String accessKeySecret;

    public static String REGION_ID;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_ID = regionId;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
    }
}
