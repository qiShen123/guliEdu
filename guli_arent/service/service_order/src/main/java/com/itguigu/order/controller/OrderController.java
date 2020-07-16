package com.itguigu.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.order.entity.TOrder;
import com.itguigu.order.service.IOrderService;
import com.itguihu.commonutils.JwtUtils;
import com.itguihu.commonutils.R;
import com.netflix.client.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-07-10
 */
@RestController
@RequestMapping("/order/t-order")
@CrossOrigin
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("{courseId}")
    public R createOrder(@PathVariable("courseId") String courseId,
                         HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId, memberId);
        return R.ok().data("orderNo", orderNo);
    }


    @GetMapping("/getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable("orderNo")String orderNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_no", orderNo);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("order", order);
    }

    /**
     * 远程调用，查询课程是否购买
     * @param orderNo
     * @param memberId
     * @return
     */
    @GetMapping("/isBuy/{courseId}/{memberId}")
    public boolean getIsBuy(@PathVariable("courseId")String courseId,@PathVariable("memberId")String memberId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        return count>0;
    }
}
