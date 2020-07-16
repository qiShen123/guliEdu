package com.itguigu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.eduService.entity.EduAdmin;
import com.itguigu.eduService.mapper.EduAdminMapper;
import com.itguigu.eduService.service.IEduAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguihu.commonutils.JwtUtils;
import com.itguihu.commonutils.MD5;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-06
 */
@Service
public class
EduAdminServiceImpl extends ServiceImpl<EduAdminMapper, EduAdmin> implements IEduAdminService {

    @Override
    public String login(EduAdmin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        EduAdmin eduAdmin = baseMapper.selectOne(wrapper);
        if (null==username||null==password){
            throw new GuliException(20001,"账号或密码错误");
        }
        String mdPassword = MD5.encrypt(password);

        String dbPassword = eduAdmin.getPassword();
        String nickname = eduAdmin.getNickname();
        String id = eduAdmin.getId();
        if (!dbPassword.equals(mdPassword)){
            throw new GuliException(20001,"账号或密码错误");
        }else {
            String jwtToken = JwtUtils.getJwtToken(id, nickname);
            return jwtToken;
        }
    }
}
