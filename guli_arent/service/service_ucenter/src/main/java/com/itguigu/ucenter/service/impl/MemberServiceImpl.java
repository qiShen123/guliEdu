package com.itguigu.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguigu.ucenter.entity.Member;
import com.itguigu.ucenter.entity.vo.RegisterVo;
import com.itguigu.ucenter.mapper.MemberMapper;
import com.itguigu.ucenter.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguihu.commonutils.JwtUtils;
import com.itguihu.commonutils.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 根据手机号登录
     *
     * @param member 登录信息
     * @return token
     */
    @Override
    public String loginByPhoneNum(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        String jwtToken = null;
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "手机号或密码错误");
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member dbMember = baseMapper.selectOne(queryWrapper);
        if (dbMember == null) {
            throw new GuliException(20001, "手机号或密码错误");
        }
        String memberPassword = dbMember.getPassword();
        if (!memberPassword.equals(MD5.encrypt(password))) {
            throw new GuliException(20001, "密码错误");
        }
        //判断是否禁用
        Boolean isDisabled = dbMember.getIsDisabled();
        if (isDisabled) {
            throw new GuliException(20001, "账号被封");
        }

        jwtToken = JwtUtils.getJwtToken(dbMember.getId(), dbMember.getNickname());

//        redisTemplate.opsForValue().set(mobile, jwtToken, 5, TimeUnit.MINUTES);
        return jwtToken;

    }

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        String coke = registerVo.getCode();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickname) || StringUtils.isEmpty(coke)) {
            throw new GuliException(20001, "手机号或密码错误");
        }
        String dbCode = redisTemplate.opsForValue().get(mobile);
        if (!coke.equals(dbCode)) {
            throw new GuliException(20001, "验证码失效");
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer integer = baseMapper.selectCount(queryWrapper);
        if (0 < integer) {
            throw new GuliException(20001, "手机号码已被注册");
        }
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        baseMapper.insert(member);

    }

    @Override
    public Member loginWithToken(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        Session session = new Session();
        QueryWrapper<Member> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        Member member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer getUserNum(String date) {
        Integer num = baseMapper.getUserNum(date);
        return num;
    }
}
