package com.itguigu.order.client.hystrix;

import com.itguigu.order.client.EduClient;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguihu.commonutils.orderVo.EduCourseOrder;
import org.springframework.stereotype.Component;

@Component
public class EduHystrix implements EduClient {
    @Override
    public EduCourseOrder getCourseFrontOrder(String courseId) {
        throw new GuliException(20001,"查询用户信息失败");
    }
}
