package com.itguigu.ucenter.service;

import com.itguigu.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.ucenter.entity.vo.RegisterVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-01
 */
public interface IMemberService extends IService<Member> {

    String loginByPhoneNum(Member member);

    void register(RegisterVo registerVo);

    Member loginWithToken(HttpServletRequest request);

    Integer getUserNum(String date);
}
