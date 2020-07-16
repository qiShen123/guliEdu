package com.itguigu.order.service.impl;

import com.itguigu.order.client.EduClient;
import com.itguigu.order.client.UCenterClient;
import com.itguigu.order.entity.TOrder;
import com.itguigu.order.mapper.OrderMapper;
import com.itguigu.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguihu.commonutils.OrderNoUtil;
import com.itguihu.commonutils.orderVo.EduCourseOrder;
import com.itguihu.commonutils.orderVo.MemberOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TOrder> implements IOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UCenterClient uCenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        EduCourseOrder courseInfoOrder = eduClient.getCourseFrontOrder(courseId);
        MemberOrderVo userInfoOrder = uCenterClient.getUserOrderInfo(memberId);
        String orderNo = OrderNoUtil.getOrderNo();

        TOrder order = new TOrder();
        order.setOrderNo(orderNo);//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
