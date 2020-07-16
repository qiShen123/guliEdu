package com.itguigu.msmService.utils;

import com.aliyuncs.profile.DefaultProfile;

public class DefaultMsmClient {
    public static com.aliyuncs.DefaultAcsClient initVodClient(String regionId,String accessKeyId, String accessKeySecret)  {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        com.aliyuncs.DefaultAcsClient client = new com.aliyuncs.DefaultAcsClient(profile);
        return client;
    }
}
