package com.itguigu.vod.utils;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

public class DefaultAcsClient {
    public static com.aliyuncs.DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        com.aliyuncs.DefaultAcsClient client = new com.aliyuncs.DefaultAcsClient(profile);
        return client;
    }
}
