package com.itguigu.ucenter.controller;


import com.itguigu.ucenter.entity.Member;
import com.itguigu.ucenter.entity.vo.RegisterVo;
import com.itguigu.ucenter.service.IMemberService;
import com.itguihu.commonutils.JwtUtils;
import com.itguihu.commonutils.R;
import com.itguihu.commonutils.orderVo.MemberOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-07-01
 */
@RestController
@RequestMapping("/uCenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private IMemberService memberService;

    //登录接口
    @PostMapping
    public R loginByPhoneNum(@RequestBody Member member){
        String token = memberService.loginByPhoneNum(member);
        if (null==token){
            return R.error().message("登录失败");
        }else {
            return R.ok().data("token",token);
        }
    }


    //注册接口
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //token登录
    @GetMapping
    public R loginWithToken(HttpServletRequest request){
        Member member = memberService.loginWithToken(request);
        return R.ok().data("member",member);
    }

    /**
     * 远程调用获取用户信息对象
     * @param userId
     * @return
     */
    @GetMapping("/getUserInfoOrder/{userId}")
    public MemberOrderVo getUserOrderInfo(@PathVariable("userId") String userId){
        Member member = memberService.getById(userId);
        MemberOrderVo memberOrderVo = new MemberOrderVo();
        BeanUtils.copyProperties(member,memberOrderVo);
        return memberOrderVo;
    }

    @GetMapping("/getUserNum/{date}")
    public Integer getUserNum(@PathVariable("date")String date){
        Integer num = memberService.getUserNum(date);
        return num;
    }
}
