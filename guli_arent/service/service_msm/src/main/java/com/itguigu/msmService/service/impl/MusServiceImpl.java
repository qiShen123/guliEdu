package com.itguigu.msmService.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.itguigu.msmService.service.IMsmService;
import com.itguigu.msmService.utils.DefaultMsmClient;
import com.itguigu.msmService.utils.MsmInitializingBean;
import com.itguigu.msmService.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MusServiceImpl implements IMsmService {

    @Autowired
    RedisTemplate<String ,String > redisTemplate;


    @Override
    public String getCode(String phoneNum) {
        String code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean b = this.sendCode(phoneNum, param);
        if (b) {
            return code;
        } else {
            return null;
        }
    }

    public boolean sendCode(String phoneNum, Map<String, Object> param) {

        if (null == phoneNum) {
            return false;
        }
        //获取可操作客户端
        IAcsClient client = DefaultMsmClient.initVodClient(MsmInitializingBean.REGION_ID, MsmInitializingBean.ACCESS_KEY_ID,
                MsmInitializingBean.ACCESS_KEY_SECRET);

        //获取请求体
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "王大力的个人学习网站");
        request.putQueryParameter("TemplateCode", "SMS_194650306");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        Object code = param.get("code");
        redisTemplate.opsForValue().set(phoneNum, code.toString(), 5, TimeUnit.MINUTES);
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//
//            return true;
//        } catch (ServerException e) {
//            e.printStackTrace();
//            return false;
//        } catch (ClientException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

}
