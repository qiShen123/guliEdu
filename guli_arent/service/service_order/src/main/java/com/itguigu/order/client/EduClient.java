package com.itguigu.order.client;

import com.itguigu.order.client.hystrix.EduHystrix;
import com.itguigu.commonutils.orderVo.EduCourseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-edu",fallback = EduHystrix.class)
public interface EduClient {
    @GetMapping("/eduService/front/courseFront/getCourseInfoOrder/{courseId}")
    EduCourseOrder getCourseFrontOrder(@PathVariable("courseId") String courseId);
}
